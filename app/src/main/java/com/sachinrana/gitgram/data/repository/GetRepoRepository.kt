package com.sachinrana.gitgram.data.repository

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.rifqimfahmi.foorballapps.vo.Resource
import com.sachinrana.gitgram.ContextProviders

import com.sachinrana.gitgram.data.local.RepositoryDao
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity
import com.sachinrana.gitgram.data.remote.ApiService
import com.sachinrana.gitgram.data.remote.model.getRepoApiResponse
import javax.inject.Inject

class GetRepoRepository @Inject constructor(
    private val repositoryDao: RepositoryDao,
    private val remote: ApiService,
    val contextProviders: ContextProviders
) {

    fun getLatestRepositories(
        sort: String = "",
        order: String = "",
        page: Long
    ): LiveData<Resource<List<RepositoryEntity>>> {

        return object : NetworkBoundResource<List<RepositoryEntity>, getRepoApiResponse>(
            contextProviders
        ) {

            override fun saveCallResult(item: getRepoApiResponse) {
                println(item)
            }

            override fun createCall(): LiveData<Resource<getRepoApiResponse>> {
                return remote.fetchRepositories(page = page, order = order, sort = sort)
            }

            override fun shouldFetch(data: List<RepositoryEntity>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<RepositoryEntity>> {
                return repositoryDao.getRepositoriesByPage(page)
            }
        }.asLiveData()
    }

}
