package com.sachinrana.gitgram.di

import android.app.Application
import androidx.room.Room
import com.sachinrana.gitgram.data.local.GitgramDB
import com.sachinrana.gitgram.data.local.RepositoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): GitgramDB {
        return Room.databaseBuilder(
            application,
            GitgramDB::class.java, "Github.db"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGithubDao(appDatabase: GitgramDB): RepositoryDao {
        return appDatabase.RepositoryDao()
    }
}
