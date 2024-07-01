package com.jubaya.myarchive.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.jubaya.myarchive.model.ArchiveDatabase
import com.jubaya.myarchive.model.PDetail
import com.jubaya.myarchive.model.Planet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val planetLD = MutableLiveData<Planet?>()
    val detailsLD = MutableLiveData<List<PDetail>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val db = Room.databaseBuilder(
        getApplication(),
        ArchiveDatabase::class.java, "newplanetdb"
    ).build()

    fun refresh(id:String){
        launch{
            try{
                val planet = db.planetDao().selectPlanet(id.toInt())

                if(planet != null){
                    planetLD.postValue(planet)
                    val details = db.pdetailDAO().selectDetailsByPlanetId(planet.id)
                    detailsLD.postValue(details)
//                    details.observeForever{ detailList ->
//                        detailsLD.postValue(detailList)
//                    }
                }else{
                    planetLD.postValue(null)
                    detailsLD.postValue(emptyList())
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}