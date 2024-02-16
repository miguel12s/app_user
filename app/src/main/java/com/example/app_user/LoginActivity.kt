package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var forgot:TextView
    private lateinit var email:TextView
    private lateinit var password:TextView
    private lateinit var tvmessage:TextView
    private lateinit var login:Button
    private lateinit var register:TextView
    private lateinit var img:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        forgot=findViewById(R.id.forgot)
        login=findViewById(R.id.btlogin)
        email=findViewById(R.id.tvcorreo)
        password=findViewById(R.id.tvpasswor)
        tvmessage=findViewById(R.id.tvmessage)
        register=findViewById(R.id.register2)
        img=findViewById(R.id.back_home)


        login.setOnClickListener {
            val intent=intent
            val user=intent.getSerializableExtra("user") as? User
            val email = email.text.toString()
            val password = password.text.toString()
            if (user?.email == email && user?.password == password) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("user",user)
                startActivity(intent)
            } else {
                tvmessage.text = "la contraseña o el correo son incorrectos"
            }
        }
            forgot.setOnClickListener {

                val intent = Intent(this, ForgotActivity::class.java)
                startActivity(intent)
            }

        register.setOnClickListener {

            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        img.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        }
}