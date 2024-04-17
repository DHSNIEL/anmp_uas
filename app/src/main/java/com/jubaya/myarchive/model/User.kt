package com.jubaya.myarchive.model

import com.google.gson.annotations.SerializedName

data class User(
    var id:String?,
    var username:String?,
    var firstname:String?,
    var lastname:String?,
    var email:String?,
    var img_url:String?
)
