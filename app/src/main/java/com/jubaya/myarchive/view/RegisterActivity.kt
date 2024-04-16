package com.jubaya.myarchive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jubaya.myarchive.R
import com.jubaya.myarchive.databinding.ActivityRegisterBinding
import org.json.JSONObject
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
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
                    val q = Volley.newRequestQueue(this)
                    val url = "https://anmpprojects.000webhostapp.com/register.php"

                    val stringRequest = object : StringRequest(
                        Request.Method.POST, url,
                        { response ->

                            val jsonObject = JSONObject(response)
                            Log.d("RES", jsonObject.getString("result"))
                            if (jsonObject.getString("result") == "OK") {
                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                                //finish()
                            } else {
                                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                            }
                        },
                        { error ->
                            // Handle error
                            Log.e("RegistrationError", error.printStackTrace().toString())
                            Toast.makeText(this, "Error occurred during registration", Toast.LENGTH_SHORT).show()
                        }) {

                        override fun getParams(): MutableMap<String, String> {
                            val params = HashMap<String, String>()
                            params["username"] = binding.txtUsername.text.toString()
                            params["firstname"] = binding.txtFirst.text.toString()
                            params["lastname"] = binding.txtLast.text.toString()
                            params["email"] = binding.txtEmail.text.toString()
                            params["password"] = binding.txtPass.text.toString()
                            params["img_url"] = binding.txtURL.text.toString()
                            return params
                        }
                    }

                    q.add(stringRequest)

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
}