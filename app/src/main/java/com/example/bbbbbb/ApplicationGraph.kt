package com.example.bbbbbb

import com.example.bbbbbb.stats.LoginComponent
import com.example.bbbbbb.stats.SubcomponentsModule
import com.example.bbbbbb.main.MainActivity
import com.example.bbbbbb.main.MainComponent
import com.example.bbbbbb.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class, MainModule::class])
interface ApplicationGraph {
    fun repository(): Repository
    fun inject(activity: MainActivity)
    fun loginComponent(): LoginComponent.Factory
    fun mainComponent(): MainComponent.Factory
}

