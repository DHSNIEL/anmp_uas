package com.jubaya.myarchive.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jubaya.myarchive.model.User
import com.jubaya.myarchive.model.UserDao
import com.jubaya.myarchive.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val registerLD = MutableLiveData<List<User>>()
    val regLoadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()
    private val job = Job()

    override val coroutineContext = Dispatchers.IO + job

    private val userDao: UserDao by lazy {
        val db = buildDb(application)
        db.userDao()
    }

    fun register(user: User) {
        loadingLD.value = true
        regLoadingErrorLD.value = false

        launch {
            try {
                userDao.insertAll(user)
                registerSuccess.postValue(true)
                Log.d("RegisterViewModel", "User registered successfully: ${user.username}")
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Error during registration: ${e.message}", e)
                registerSuccess.postValue(false)
            } finally {
                loadingLD.postValue(false)
            }
        }
    }

    fun refresh() {
        loadingLD.value = true
        regLoadingErrorLD.value = false

        launch {
            try {
                val users = userDao.selectAllUser()
                registerLD.postValue(users)
            } catch (e: Exception) {
                regLoadingErrorLD.postValue(true)
                Log.e("RegisterViewModel", "Error during refresh: ${e.message}", e)
            } finally {
                loadingLD.postValue(false)
            }
        }
    }

    fun updateDone(user: User) {
        launch {
            try {
                userDao.updateUser(user)
                Log.d("RegisterViewModel", "User updated successfully: ${user.username}")
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Error during update: ${e.message}", e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}