package com.example.bbbbbb.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bbbbbb.MyApplication
import com.example.bbbbbb.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainComponent: MainComponent

    @Inject
    lateinit var loginMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (applicationContext as MyApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginMainViewModel.user.observe(this) {
            Log.i(TAG, it.toString())
        }

        loginMainViewModel.fetchUsers()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
