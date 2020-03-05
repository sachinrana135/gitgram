package com.sachinrana.gitgram.di



import com.sachinrana.gitgram.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ActivityModule::class,
        ApiModule::class,
        DbModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
