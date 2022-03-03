package com.example.bbbbbb.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bbbbbb.repository.Repository
import com.example.bbbbbb.model.Organisation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) {

    private val _user: MutableLiveData<Result<List<Organisation>?>> =
        MutableLiveData(Result.success(emptyList()))
    val user: LiveData<Result<List<Organisation>?>> = _user

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            _user.postValue(repository.organisations())
        }
    }
}
