package com.sachinrana.gitgram.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinrana.gitgram.ContextProviders
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity
import com.sachinrana.gitgram.data.repository.GetRepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: GetRepoRepository,
    val contextProviders: ContextProviders
) : ViewModel() {

    val coroutineScope = CoroutineScope(contextProviders.IO)
    var mutabeleLiveRepoRepository = MutableLiveData<List<RepositoryEntity>>()

    open fun fetchRepositories() {
        Log.d(TAG, "before coroutine scope- ${Thread.currentThread().name}")
        viewModelScope.launch {
            Log.d(TAG, "in coroutine scope- ${Thread.currentThread().name}")

                repository.getFreshLatestRepositories(page = "", sort = "", order = "")
            Log.d(TAG, "in after coroutine scope- ${Thread.currentThread().name}")
        }
        Log.d(TAG, "outside coroutine scope- ${Thread.currentThread().name}")

    }
}