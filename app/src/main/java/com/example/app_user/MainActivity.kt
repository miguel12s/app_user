package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit  var button: Button
    private lateinit  var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.btnregister)
        button2=findViewById(R.id.btnlogin)
    button.setOnClickListener{
        val intent=Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
        button2.setOnClickListener{
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}