package com.sachinrana.gitgram.utils

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.vo.Resource
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/*
 * Created by Rifqi Mulya Fahmi on 01/12/18.
 */

class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<Resource<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<Resource<R>> {
        return object : LiveData<Resource<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(Resource.success(response.body()))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(Resource.error("Error in api", null))
                        }
                    })
                }
            }
        }
    }
}