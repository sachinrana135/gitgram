package com.sachinrana.gitgram.data.remote

import com.sachinrana.gitgram.data.local.entity.RepositoryEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repositories")
    suspend fun fetchRepositories(
        @Query("language") sort: String,
        @Query("since") order: String,
        @Query("spoken_language_code") page: String
    ): List<RepositoryEntity>

}