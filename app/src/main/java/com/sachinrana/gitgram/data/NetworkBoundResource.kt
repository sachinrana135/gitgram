import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifqimfahmi.foorballapps.vo.Resource
import com.sachinrana.gitgram.ContextProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType>
constructor(private val contextProviders: ContextProviders) {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val coroutineScope = CoroutineScope(contextProviders.IO)

    suspend fun execute(): MutableLiveData<Resource<ResultType>> {

        withContext(contextProviders.Main) {
            result.value = Resource.loading(null)
        }

        if (shouldFetch()) {
            createCall().observeForever {
                processResponse(it)
            }

        } else {
            loadFromDb()
        }



        return result
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }


    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    abstract fun saveCallResult(item: RequestType)

    protected fun processResponse(response: Resource<RequestType>) = response.data

    abstract suspend fun createCall(): LiveData<Resource<RequestType>>

    protected abstract fun shouldFetch(): Boolean

    protected abstract suspend fun loadFromDb(): LiveData<ResultType>
}