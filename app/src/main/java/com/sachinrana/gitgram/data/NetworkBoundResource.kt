
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.rifqimfahmi.foorballapps.vo.Resource
import com.sachinrana.gitgram.ContextProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType>
constructor(private val contextProviders: ContextProviders) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            GlobalScope.launch(contextProviders.IO) {
                saveCallResult(processResponse(response)!!)
                GlobalScope.launch(contextProviders.Main) {
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            }

        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    abstract fun saveCallResult(item: RequestType)

    protected fun processResponse(response: Resource<RequestType>) = response.data

    abstract fun createCall(): LiveData<Resource<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>
}