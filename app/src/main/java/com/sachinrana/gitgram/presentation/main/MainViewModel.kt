package com.sachinrana.gitgram.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifqimfahmi.foorballapps.vo.Resource
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity
import com.sachinrana.gitgram.data.repository.GetRepoRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository : GetRepoRepository) : ViewModel() {

    open fun fetchRepositories() : LiveData<Resource<List<RepositoryEntity>>> {
        return repository.getLatestRepositories(page=1)
    }
}