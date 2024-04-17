package com.jubaya.myarchive.model

import com.google.gson.annotations.SerializedName

data class Planet(
    var id:String?,
    var name:String?,
    var summary:String?,
    var img_url:String?,
    @SerializedName("username")
    var authorname:String?,
)
