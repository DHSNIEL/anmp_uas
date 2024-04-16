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
import com.jubaya.myarchive.databinding.ActivityLoginBinding
import com.jubaya.myarchive.model.Global
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    var user_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtReg.setOnClickListener {
            val intent = Intent(it.context, RegisterActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            //Log.d("username", binding.txtUsername.text.toString())
            //Log.d("pass", binding.txtPass.text.toString())

            val q = Volley.newRequestQueue(this)
            val url = "https://anmpprojects.000webhostapp.com/login.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        user_id = obj.getInt("data")

                        Log.d("apiresult", user_id.toString())
                        Toast.makeText( this,"Welcome, " + binding.txtUsername.text, Toast.LENGTH_LONG).show()
                        updateList()
                        finish()
                    }
                    else{
                        Toast.makeText( this, obj.getString("message"), Toast.LENGTH_LONG).show()
                    }
                },
                {
                    Log.e("apierror", it.printStackTrace().toString())
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String, String>()
                    params["username"] = binding.txtUsername.text.toString()
                    params["password"] = binding.txtPass.text.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }
    }

    fun updateList() {
        Global.userid = user_id
        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra(LoginActivity.idUser, get_id)
        startActivity(intent)
    }
}