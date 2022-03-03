package com.example.bbbbbb.stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bbbbbb.MyApplication
import com.example.bbbbbb.R
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

class LoginActivity : AppCompatActivity() {
    lateinit var loginComponent: LoginComponent
    @Inject
    lateinit var statsViewModel: StatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (applicationContext as MyApplication)
            .appComponent.loginComponent().create()

        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
    }
}

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(loginActivity: LoginActivity)
    fun inject(usernameFragment: StatsUsernameFragment)
    fun inject(passwordFragment: StatsRepoFragment)
}