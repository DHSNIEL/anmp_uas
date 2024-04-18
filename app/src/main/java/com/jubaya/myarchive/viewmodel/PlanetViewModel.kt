package com.jubaya.myarchive.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jubaya.myarchive.model.Planet
import com.jubaya.myarchive.model.User
import org.json.JSONObject

class PlanetViewModel(application: Application): AndroidViewModel(application) {
    val planetsLD = MutableLiveData<ArrayList<Planet>>()
    val loadingLD = MutableLiveData<Boolean>()
    val planetLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        planetLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://anmpprojects.000webhostapp.com/get_planet.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = false
                Log.d("show_volley ", it)

                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object: TypeToken<List<Planet>>(){}.type
                    val result = Gson().fromJson<List<Planet>>(data.toString(), sType)

                    planetsLD.value = result as ArrayList<Planet>
                }
            }, {
                loadingLD.value = false
                planetLoadErrorLD.value = false
                Log.e("show_volley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}