package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

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

            val email = tvemail.text.toString()
            val password = tvpassword.text.toString()
            val names = tvnombres.text.toString()
            val phone = tvtelefono.text.toString()
            val phoneNumber = phone.toLong()

            if (email.isEmpty() || password.isEmpty() || names.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            } else if (names.length < 9 || password.length < 9 || phone.length!=10) {
                Toast.makeText(
                    this,
                    "Los campos deben tener una longitud mayor a 8 y el telefono de 10",
                    Toast.LENGTH_SHORT
                ).show()


            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(
                    this,
                    "El correo es invalido",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val usuario = User(email, password, names, phoneNumber)
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("user", usuario)
                startActivity(intent)
            }
        }
        back_profile.setOnClickListener{
            val intent=Intent(this,ProfileActivity::class.java)
            intent.putExtra("user",user)

            startActivity(intent)
        }
    }
}