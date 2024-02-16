package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
private lateinit var iniciarSesion:TextView
    private lateinit var btnregister:Button
    private lateinit var tvemail:TextView
    private lateinit var tvpassword:TextView
    private lateinit var tvnames:TextView
    private lateinit var tvphone:TextView

    private lateinit var back_register:ImageView
private lateinit var phone:String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        back_register=findViewById(R.id.back_home)

            iniciarSesion=findViewById(R.id.iniciar_sesion)
        btnregister=findViewById(R.id.btregister)
        tvemail=findViewById(R.id.tvemail)
        tvpassword=findViewById(R.id.tvpassword)
        tvnames=findViewById(R.id.tvnames)
        tvphone=findViewById(R.id.tvphone)
        btnregister.setOnClickListener{
            try {
                val email = tvemail.text.toString()
                val password = tvpassword.text.toString()
                val names = tvnames.text.toString()
                phone = tvphone.text.toString()
                val phoneNumber=phone.toLong()
                if(email.isEmpty()||password.isEmpty()||names.isEmpty()){
                    throw Exception()
                }
                val usuario=User(email,password,names,phoneNumber)
                val intent=Intent(this,ProfileActivity::class.java)
                intent.putExtra("user",usuario)
                startActivity(intent)
            } catch (e:Exception){
                Toast.makeText(this, "los campos no deben estar vacios", Toast.LENGTH_SHORT).show();

            }





        }

        iniciarSesion.setOnClickListener{
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        back_register.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}