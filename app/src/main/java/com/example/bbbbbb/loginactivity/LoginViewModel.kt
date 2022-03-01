package com.example.bbbbbb.loginactivity

import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor() {

    private var _username: String = ""
    private var _password: String = ""

    fun setUsername(username: String) {
        // can be launched in a separate asynchronous job
        _username = username
    }

    fun setPassword(password: String) {
        // can be launched in a separate asynchronous job
        _password = password
    }
}