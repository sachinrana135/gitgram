package com.sachinrana.gitgram.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sachinrana.gitgram.GitApplication
import com.sachinrana.gitgram.R
import com.sachinrana.gitgram.factory.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    lateinit var githubListViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as GitApplication).appComponent.inject(this)

        githubListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        githubListViewModel.fetchRepositories()

    }
}
