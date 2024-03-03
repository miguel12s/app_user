package com.example.app_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app_user.ui.viewModels.UserViewModel
import com.example.app_user.utils.Common
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var iniciarSesion: TextView
    private lateinit var btnregister: Button
    private lateinit var tvemail: TextView
    private lateinit var tvpassword: TextView
    private lateinit var tvnames: TextView
    private lateinit var tvphone: TextView

    private lateinit var back_register: ImageView
    private lateinit var phone: String
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        back_register = findViewById(R.id.back_home)

        iniciarSesion = findViewById(R.id.iniciar_sesion)
        btnregister = findViewById(R.id.btregister)
        tvemail = findViewById(R.id.tvemail)
        tvpassword = findViewById(R.id.tvpassword)
        tvnames = findViewById(R.id.tvnames)
        tvphone = findViewById(R.id.tvphone)
        userViewModel.userSaved.observe(this, Observer { isValidRegister ->
            if (isValidRegister) {

            } else {
                Common.showToast(this, "Hubo un error al registrar datos en la base de datos")

            }
        })
        btnregister.setOnClickListener {
            try {
                val email = tvemail.text.toString()
                val password = tvpassword.text.toString()
                val names = tvnames.text.toString()
                phone = tvphone.text.toString()
                if (email.isEmpty() || password.isEmpty() || names.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT)
                        .show()
                } else if (names.length < 9 || password.length < 9 || phone.length != 10) {
                    Toast.makeText(
                        this,
                        "Los campos deben tener una longitud mayor a 8 y el telefono de 10",
                        Toast.LENGTH_SHORT
                    ).show()


                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(
                        this,
                        "El correo es invalido",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    userViewModel.insertUser(email, password, names, phone)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }


            } catch (e: Exception) {
                Toast.makeText(this, "los campos no deben estar vacios", Toast.LENGTH_SHORT).show();

            }


        }

        iniciarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        back_register.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}