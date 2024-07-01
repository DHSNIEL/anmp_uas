package com.jubaya.myarchive.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jubaya.myarchive.model.PDetail
import com.jubaya.myarchive.model.Planet

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val planetLD = MutableLiveData<Planet>()
    val detailsLD = MutableLiveData<ArrayList<PDetail>>()

//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    fun refresh(id:String){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://anmpprojects.000webhostapp.com/get_detail.php"
//
//        val stringRequest = object : StringRequest(
//            Request.Method.POST, url,
//            {
//                Log.d("show_volley ", it)
//
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    //val data = obj.getJSONArray("data")
//                    val jsonData = obj.getJSONObject("data")
//
//                    val main = jsonData.getJSONArray("main")
//                    val jsonObject = main.getJSONObject(0)
//                    val pType = object : TypeToken<Planet>() {}.type
//                    val pResult = Gson().fromJson<Planet>(jsonObject.toString(), pType)
//
//                    planetLD.value = pResult as Planet
//
//                    val detailArray = jsonData.getJSONArray("detail")
//                    val dType = object : TypeToken<List<PDetail>>() { }.type
//                    val dResult = Gson().fromJson<List<PDetail>>(detailArray.toString(), dType)
//
//                    detailsLD.value = dResult as ArrayList<PDetail>
//
//                }
//            }, {
//                Log.e("show_volley", it.toString())
//            }){
//                override fun getParams(): MutableMap<String, String>? {
//                    val params = HashMap<String, String>()
//                    params["id"] = id
//                    return params
//            }
//        }
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}