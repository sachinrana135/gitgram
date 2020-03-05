package com.sachinrana.gitgram.di

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity = activity


}