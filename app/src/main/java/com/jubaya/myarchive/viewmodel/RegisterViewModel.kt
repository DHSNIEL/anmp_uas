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

class RegisterViewModel(application: Application): AndroidViewModel(application) {
    val registerMsgLD = MutableLiveData<String>()

    val TAG = "volleyTag"
    private var q: RequestQueue? = null

    fun register(username:String, firstname:String, lastname:String, email:String, password:String, img_url:String){
        q = Volley.newRequestQueue(getApplication())
        val url = "https://anmpprojects.000webhostapp.com/register.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->

                val jsonObject = JSONObject(response)
                Log.d("RES", jsonObject.getString("result"))
                if (jsonObject.getString("result") == "OK") {

                    registerMsgLD.value = "Registration is Successful"

                } else {

                    registerMsgLD.value = "Registration Failed. Please Try Again"

                }
            },
            { error ->
                // Handle error
                Log.e("RegistrationError", error.printStackTrace().toString())
            }) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = username
                    params["firstname"] = firstname
                    params["lastname"] = lastname
                    params["email"] = email
                    params["password"] = password
                    params["img_url"] = img_url
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