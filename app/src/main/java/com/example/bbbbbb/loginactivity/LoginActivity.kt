package com.example.bbbbbb.loginactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bbbbbb.LoginPasswordFragment
import com.example.bbbbbb.MyApplication
import com.example.bbbbbb.R
import com.example.bbbbbb.loginactivity.ui.login.LoginUserNameFragment
import dagger.Component
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope

// Definition of a custom scope called ActivityScope
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

class LoginActivity : AppCompatActivity() {
    lateinit var loginComponent: LoginComponent
    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creation of the login graph using the application graph
        loginComponent = (applicationContext as MyApplication)
            .appComponent.loginComponent().create()

        // Make Dagger instantiate @Inject fields in LoginActivity
        loginComponent.inject(this)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
    fun inject(usernameFragment: LoginUserNameFragment)
    fun inject(passwordFragment: LoginPasswordFragment)
}