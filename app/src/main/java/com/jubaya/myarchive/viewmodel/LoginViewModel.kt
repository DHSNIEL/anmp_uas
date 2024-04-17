package com.jubaya.myarchive.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val loginidLD = MutableLiveData<Int>()

    val TAG = "volleyTag"
    private var q: RequestQueue? = null

    fun login(username:String, password:String){
        q = Volley.newRequestQueue(getApplication())
        val url = "https://anmpprojects.000webhostapp.com/login.php"

        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {

                    val data = obj.getInt("data")
                    loginidLD.value = data

                } else {
                    loginidLD.value = 0
                }
            },
            {
                Log.e("apierror", it.printStackTrace().toString())
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        stringRequest.tag = TAG
        q?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        q?.cancelAll(TAG)
    }
}