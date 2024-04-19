package com.jubaya.myarchive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jubaya.myarchive.R
import com.jubaya.myarchive.databinding.ActivityLoginBinding
import com.jubaya.myarchive.model.Global
import com.jubaya.myarchive.viewmodel.LoginViewModel
import com.jubaya.myarchive.viewmodel.ProfileViewModel
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    var user_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "My Archive"

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.txtReg.setOnClickListener {
            val intent = Intent(it.context, RegisterActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            //Log.d("username", binding.txtUsername.text.toString())
            //Log.d("pass", binding.txtPass.text.toString())

            if (binding.txtUsername.text.toString() != "" && binding.txtPass.text.toString() != ""){

                viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
                viewModel.login(binding.txtUsername.text.toString(), binding.txtPass.text.toString())

                observeViewModel()

            }
            else{
                Toast.makeText(this, "Please Fill All The Field", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun observeViewModel(){
        viewModel.loginidLD.observe(this, Observer {
            user_id = it

            Log.d("ID", user_id.toString())
            if (user_id != 0){
                Toast.makeText( this,"Welcome, " + binding.txtUsername.text, Toast.LENGTH_LONG).show()
                updateList()
            } else {
                Toast.makeText( this,"Login Failed. Username or Password is incorrect", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun updateList() {
        Global.userid = user_id
        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra(LoginActivity.idUser, get_id)
        startActivity(intent)
    }
}