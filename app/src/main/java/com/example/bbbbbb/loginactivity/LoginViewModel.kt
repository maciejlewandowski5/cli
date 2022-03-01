package com.example.bbbbbb.loginactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bbbbbb.Metrics
import com.example.bbbbbb.Post
import com.example.bbbbbb.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(val userRepository: UserRepository) {

    private var _username: String = ""
    private var _password: String = ""

    private val _metrics: MutableLiveData<Result<Metrics?>> =
        MutableLiveData(Result.failure(Throwable("")))
    val metrics: LiveData<Result<Metrics?>> = _metrics

    private val _post: MutableLiveData<Result<Post?>> =
        MutableLiveData(Result.failure(Throwable("")))
    val post: LiveData<Result<Post?>> = _post

    private val _nav: MutableLiveData<Pair<Boolean, Boolean>> =
        MutableLiveData(Pair(false, false))
    val nav: LiveData<Pair<Boolean, Boolean>> = _nav

    fun setUsername(username: String) {
        // can be launched in a separate asynchronous job
        _username = username
    }

    fun setPassword(password: String) {
        // can be launched in a separate asynchronous job
        _password = password
    }

    fun metrics() {
        CoroutineScope(Dispatchers.IO).launch {
            _metrics.postValue(userRepository.metrics(_username, _password))
            _nav.postValue(_nav.value?.copy(first = true))
        }
    }

    fun createPost(string: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _post.postValue(userRepository.post(string))
            _nav.postValue(_nav.value?.copy(second = true))
        }
    }
}