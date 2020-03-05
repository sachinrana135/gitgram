package com.sachinrana.gitgram

import android.app.Application
import com.sachinrana.gitgram.di.AppComponent
import com.sachinrana.gitgram.di.AppModule
import com.sachinrana.gitgram.di.DaggerAppComponent

class GitApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: GitApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

}