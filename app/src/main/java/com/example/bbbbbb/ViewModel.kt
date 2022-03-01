package com.example.bbbbbb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModel @Inject constructor(val userRepository: UserRepository) {
    private val _user: MutableLiveData<Result<List<User>?>> =
        MutableLiveData(Result.success(emptyList()))
    val user: LiveData<Result<List<User>?>> = _user

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            _user.postValue(userRepository.getUsers("0"))
        }
    }

}