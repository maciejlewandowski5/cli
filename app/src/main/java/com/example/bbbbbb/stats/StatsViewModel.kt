package com.example.bbbbbb.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bbbbbb.repository.Repository
import com.example.bbbbbb.model.CreatePost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class StatsViewModel @Inject constructor(private val userRepository: Repository) {

    private var _username: String = ""
    private var _password: String = ""

    private val _state: MutableLiveData<Result<StatsState?>> =
        MutableLiveData(Result.failure(Throwable(LOADING)))
    val state: LiveData<Result<StatsState?>> = _state

    fun setUsername(username: String) {
        _username = username
    }

    fun setPassword(password: String) {
        _password = password
    }

    fun onButtonClick(authToken: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val post = async { userRepository.post(examplePost(), authToken) }
            val metrics = async { userRepository.metrics(_username, _password) }

            _state.postValue(StatsState.from(post.await(), metrics.await()))
        }
    }

    private fun examplePost() = CreatePost(
        EXAMPLE_TEXT,
        IMAGE,
        LIKES,
        listOf(TAG),
        OWNER
    )

    companion object {
        private const val LOADING = "Loading"
        private const val EXAMPLE_TEXT = "Example text"
        private const val IMAGE = "Image"
        private const val TAG = "tag"
        private const val OWNER = "60d0fe4f5311236168a109ca"
        private const val LIKES = 10
    }
}
