package com.sachinrana.gitgram.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sachinrana.gitgram.data.repository.GetRepoRepository
import com.sachinrana.gitgram.presentation.main.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(

    private val context: Context,
    private val gitRepository: GetRepoRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(gitRepository)
                else ->
                    error("Invalid View Model class")
            }

        } as T

    }
}