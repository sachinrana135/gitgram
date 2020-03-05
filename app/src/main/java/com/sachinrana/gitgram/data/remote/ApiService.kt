package com.sachinrana.gitgram.data.remote

import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.vo.Resource
import com.sachinrana.gitgram.data.remote.model.getRepoApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
     fun fetchRepositories(
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Long
    ): LiveData<Resource<getRepoApiResponse>>

}