package com.jubaya.myarchive.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jubaya.myarchive.model.User
import com.jubaya.myarchive.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application:Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val userLD = MutableLiveData<User>()
    val updateLD = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id:Int) {
        launch {
            userLD.postValue(buildDb(getApplication()).userDao().selectUser(id))
        }
    }

    fun update(user: User){
        launch {
            buildDb(getApplication()).userDao().updateUser(user)
        }
    }

    /*val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(id:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://anmpprojects.000webhostapp.com/get_profile.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("show_volley ", it)

                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val jsonObject = data.getJSONObject(0)
                    val sType = object : TypeToken<User>() {}.type
                    val result = Gson().fromJson<User>(jsonObject.toString(), sType)

                    userLD.value = result as User
                }

            }, {
                Log.e("show_volley", it.toString())
            }) { override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id"] = id
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    fun update(id:String, firstname:String, lastname:String, password:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://anmpprojects.000webhostapp.com/update_profile.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("show_volley ", it)

                val obj = JSONObject(it)
                updateLD.value = obj.getString("message")

            }, {
                Log.e("show_volley", it.toString())
            }) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id"] = id
                params["firstname"] = firstname
                params["lastname"] = lastname
                params["password"] = password
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }*/
}