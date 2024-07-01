package com.jubaya.myarchive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubaya.myarchive.databinding.ActivityRegisterBinding
import com.jubaya.myarchive.model.User
import com.jubaya.myarchive.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "My Archive"

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnReg.setOnClickListener {
            if (binding.txtUsername.text.toString().isNotEmpty() && binding.txtPass.text.toString().isNotEmpty() &&
                binding.txtFirst.text.toString().isNotEmpty() && binding.txtLast.text.toString().isNotEmpty() &&
                binding.txtEmail.text.toString().isNotEmpty()) {

                val user = User(
                    id = 0,  // ID akan di-generate otomatis oleh Room
                    username = binding.txtUsername.text.toString(),
                    firstname = binding.txtFirst.text.toString(),
                    lastname = binding.txtLast.text.toString(),
                    email = binding.txtEmail.text.toString(),
                    password = binding.txtPass.text.toString(),
                    img_url = "",  // tambahkan logika untuk mengambil img_url jika diperlukan
                    posts = 0  // inisialisasi dengan 0 atau nilai default lainnya
                )

                viewModel.register(user)
                observeViewModel()
            } else {
                Toast.makeText(this, "Please Fill All The Fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.registerSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                navigateToLogin()
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}