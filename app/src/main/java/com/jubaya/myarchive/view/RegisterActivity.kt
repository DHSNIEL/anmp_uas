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
import com.jubaya.myarchive.databinding.ActivityRegisterBinding
import com.jubaya.myarchive.viewmodel.LoginViewModel
import com.jubaya.myarchive.viewmodel.RegisterViewModel
import org.json.JSONObject
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding. root)

        binding.txtLog.setOnClickListener {
            val intent = Intent(it.context, LoginActivity::class.java)
            it.context.startActivity(intent)
        }
        binding.btnReg.setOnClickListener {
            if(binding.txtUsername.text.toString() != "" && binding.txtFirst.text.toString() != "" &&
                binding.txtLast.text.toString() != "" && binding.txtEmail.text.toString() != "" &&
                binding.txtPass.text.toString() != "" && binding.txtConfPass.text.toString() != "" &&
                binding.txtURL.text.toString() != ""){

                if (binding.txtPass.text.toString() == binding.txtConfPass.text.toString()){

                    viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
                    viewModel.register(binding.txtUsername.text.toString(), binding.txtFirst.text.toString(),
                        binding.txtLast.text.toString(), binding.txtEmail.text.toString(),
                        binding.txtPass.text.toString(), binding.txtURL.text.toString())

                        observeViewModel()

                    //Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Password and Confirm Password is Not Matching", Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(this, "Please Fill All The Field", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun observeViewModel(){
        viewModel.registerMsgLD.observe(this, Observer {
            message = it

            Log.d("Msg", message)
            Toast.makeText( this, message, Toast.LENGTH_LONG).show()
        })
    }
}