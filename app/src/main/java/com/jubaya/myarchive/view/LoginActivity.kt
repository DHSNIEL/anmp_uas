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
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "My Archive"

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.txtReg.setOnClickListener {
            val intent = Intent(it.context, RegisterActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            if (binding.txtUsername.text.toString().isNotEmpty() && binding.txtPass.text.toString().isNotEmpty()) {
                viewModel.login(binding.txtUsername.text.toString(), binding.txtPass.text.toString())
                observeViewModel()
            } else {
                Toast.makeText(this, "Please Fill All The Fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.loginSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Welcome, ${binding.txtUsername.text}", Toast.LENGTH_LONG).show()
                navigateToHome()
            } else {
                Toast.makeText(this, "Login Failed. Username or Password is incorrect", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.loadingLD.observe(this, Observer { isLoading ->
            // Show or hide loading indicator based on isLoading value
        })

        viewModel.loginLoadingErrorLD.observe(this, Observer { hasError ->
            // Show error message based on hasError value
        })
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}