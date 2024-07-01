package com.jubaya.myarchive.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jubaya.myarchive.model.User
import com.jubaya.myarchive.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val loginLD = MutableLiveData<User?>()
    val loginLoadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val loginSuccess = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username: String, password: String) {
        loadingLD.value = true
        loginLoadingErrorLD.value = false
        Log.d("LoginViewModel", "Starting login for username: $username")

        launch {
            try {
                val db = buildDb(getApplication())
                val user = db.userDao().check(username, password)
                if (user != null) {
                    Log.d("LoginViewModel", "Login successful for username: $username")
                    loginSuccess.postValue(true)
                    loginLD.postValue(user)
                } else {
                    Log.d("LoginViewModel", "Login failed: user not found for username: $username")
                    loginSuccess.postValue(false)
                    loginLoadingErrorLD.postValue(true)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error during login for username: $username", e)
                loginSuccess.postValue(false)
                loginLoadingErrorLD.postValue(true)
            } finally {
                loadingLD.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()  // Membatalkan semua coroutine ketika ViewModel dihancurkan
    }
}