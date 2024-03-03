package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app_user.ui.viewModels.UserViewModel
import com.example.app_user.utils.Common

class LoginActivity : AppCompatActivity() {
    private lateinit var forgot: TextView
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var tvmessage: TextView
    private lateinit var login: Button
    private lateinit var register: TextView
    private lateinit var img: ImageView
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        validateSession()
        forgot = findViewById(R.id.forgot)
        login = findViewById(R.id.btlogin)
        email = findViewById(R.id.tvcorreo)
        password = findViewById(R.id.tvpasswor)
        tvmessage = findViewById(R.id.tvmessage)
        register = findViewById(R.id.register2)
        img = findViewById(R.id.back_home)

        userViewModel.loginResult.observe(this, Observer { isValidLogin ->
            if (isValidLogin) {
                val email = email.text.toString()
                val password = password.text.toString()
                saveSession(email, password)
                goProfile()
            } else {
                Common.showToast(this, "Credenciales incorrectas")
            }
        })
        login.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
             if (email.isNotEmpty() && password.isNotEmpty()) {

                 userViewModel.validateLogin(email, password)

             } else {
                 tvmessage.text = "la contraseña o el correo son incorrectos"
             }

        }
        forgot.setOnClickListener {

            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }

        register.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        img.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveSession(email: String, password: String) {
        val sp = getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString("email", email)
        edit.putString("password", password)
        edit.apply()

    }

    private fun goProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun validateSession() {
        val sp = getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val email = sp.getString("email", "")
        val password = sp.getString("password", "")
       

        if (email != null && password != null) {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                goProfile()
            }
        }
    }
}