package com.sachinrana.gitgram.data.repository

import com.sachinrana.gitgram.ContextProviders
import com.sachinrana.gitgram.data.local.RepositoryDao
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity
import com.sachinrana.gitgram.data.remote.ApiService
import javax.inject.Inject

class GetRepoRepository @Inject constructor(
    private val repositoryDao: RepositoryDao,
    private val remote: ApiService,
    val contextProviders: ContextProviders
) {

   /* suspend fun getLatestRepositories(
        sort: String = "",
        order: String = "",
        page: String =""
    ): MutableLiveData<Resource<List<RepositoryEntity>>> {


    }*/

    suspend  fun getFreshLatestRepositories(sort: String = "",
                                            order: String = "",
                                            page: String =""): List<RepositoryEntity> {
        return remote.fetchRepositories(page = page, order = order, sort = sort)
    }

}
