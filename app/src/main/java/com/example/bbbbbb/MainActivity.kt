package com.example.bbbbbb

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bbbbbb.loginactivity.*
import com.example.bbbbbb.loginactivity.ui.login.LoginUserNameFragment
import dagger.Component
import dagger.Subcomponent
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {
    suspend fun getUsers(page: String): Result<List<User>?> {
        return remoteDataSource.getUsers(page)
    }
}

class UserLocalDataSource @Inject constructor() {}
class UserRemoteDataSource @Inject constructor(
    private val loginService: RetrofitService
) {
    suspend fun getUsers(page: String): Result<List<User>?> {
        return try {
            val response = loginService.doGetUserList(page)?.awaitResponse()
            if (response?.isSuccessful == true) {
                Result.success(response.body()?.users)
            } else {
                Result.failure(Throwable("Error getting users, response code: ${response?.code()}"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}

@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class, MainModule::class])
interface ApplicationGraph {
    fun repository(): UserRepository
    fun inject(activity: MainActivity)
    fun loginComponent(): LoginComponent.Factory
    fun mainComponent(): MainComponent.Factory
}


class MyApplication : Application() {

    val appComponent: ApplicationGraph = DaggerApplicationGraph.create()

    override fun onCreate() {
        super.onCreate()


        val userRepository: UserRepository = appComponent.repository()
        val userRepository2: UserRepository = appComponent.repository()
        assert(userRepository == userRepository2)

    }
}

class MainActivity : AppCompatActivity() {
    lateinit var mainComponent: MainComponent

    @Inject
    lateinit var loginViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (applicationContext as MyApplication)
            .appComponent.mainComponent().create()


        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).appComponent.inject(this)

        loginViewModel.user.observe(this) {
            Log.i("TAG", it.toString())
        }

        loginViewModel.fetchUsers()
    }
}

@ActivityScope
@Subcomponent
interface MainComponent {


    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mFragment: MFragment)
}