package com.sachinrana.gitgram.di

import android.app.Application
import android.content.Context
import com.sachinrana.gitgram.ContextProviders
import com.sachinrana.gitgram.data.repository.GetRepoRepository
import com.sachinrana.gitgram.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    internal fun provideContextProviders()= ContextProviders.getInstance()

    @Provides
    internal fun provideViewModelFactory(gitRepo: GetRepoRepository, contextProviders: ContextProviders): ViewModelFactory {
        return ViewModelFactory(app, gitRepo, contextProviders)
    }


}