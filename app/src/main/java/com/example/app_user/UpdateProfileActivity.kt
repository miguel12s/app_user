package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var tvemail: TextView
    private lateinit var tvpassword: TextView
    private lateinit var tvnombres: TextView
    private lateinit var tvtelefono: TextView
    private lateinit var bteditar:Button
    private lateinit var back_profile: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        tvemail=findViewById(R.id.email)
        tvpassword=findViewById(R.id.contraseña)
        tvnombres=findViewById(R.id.nombres)
        tvtelefono=findViewById(R.id.numero)
        bteditar=findViewById(R.id.btupdate)
        back_profile=findViewById(R.id.back_profile)


        val intent=intent
        val user=intent.getSerializableExtra("user") as User

        tvemail.text = user.email
        tvpassword.text = user.password
        tvnombres.text=user.names
        tvtelefono.text=user.telefono.toString()
        bteditar.setOnClickListener {
            val phoneNumber=tvtelefono.text.toString()

            val user2=User(tvemail.text.toString(),tvpassword.text.toString(),tvnombres.text.toString(),phoneNumber.toLong())

            val intent=Intent(this,ProfileActivity::class.java)
            intent.putExtra("user",user2)
            startActivity(intent)
        }
        back_profile.setOnClickListener{
            val intent=Intent(this,ProfileActivity::class.java)
            intent.putExtra("user",user)

            startActivity(intent)
        }
    }
}