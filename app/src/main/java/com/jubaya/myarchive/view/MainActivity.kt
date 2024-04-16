package com.jubaya.myarchive.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jubaya.myarchive.R
import com.jubaya.myarchive.databinding.ActivityMainBinding
import com.jubaya.myarchive.model.Global

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtID.text = Global.userid.toString()
    }
}