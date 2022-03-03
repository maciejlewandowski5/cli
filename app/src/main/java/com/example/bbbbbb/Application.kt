package com.example.bbbbbb

import android.app.Application
import com.example.bbbbbb.repository.Repository

class MyApplication : Application() {

    val appComponent: ApplicationGraph = DaggerApplicationGraph.create()

    override fun onCreate() {
        super.onCreate()

        val repository: Repository = appComponent.repository()
        val repository2: Repository = appComponent.repository()
        assert(repository == repository2)
    }
}