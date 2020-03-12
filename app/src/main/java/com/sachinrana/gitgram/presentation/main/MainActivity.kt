package com.sachinrana.gitgram.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sachinrana.gitgram.GitApplication
import com.sachinrana.gitgram.R
import com.sachinrana.gitgram.data.local.entity.RepositoryEntity
import com.sachinrana.gitgram.factory.ViewModelFactory
import javax.inject.Inject

const val TAG = "Gitgram"

class MainActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    lateinit var githubListViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as GitApplication).appComponent.inject(this)

        githubListViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        Log.d(TAG, "before calling the viewmodel function - ${Thread.currentThread().name}")
        githubListViewModel.fetchRepositories()
        Log.d(TAG, "after calling the viewmodel function- ${Thread.currentThread().name}")

        githubListViewModel.mutabeleLiveRepoRepository.observe(
            this,
            Observer<List<RepositoryEntity>> {
                println(it)
            })


    }
}
