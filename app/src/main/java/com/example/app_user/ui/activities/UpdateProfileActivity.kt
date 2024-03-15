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
import androidx.lifecycle.ViewModelProvider
import com.example.app_user.ui.viewModels.UserViewModel
import com.example.app_user.utils.Common

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var tvemail: TextView
    private lateinit var tvpassword: TextView
    private lateinit var tvnombres: TextView
    private lateinit var tvtelefono: TextView
    private lateinit var bteditar: Button
    private lateinit var back_profile: ImageView
    private lateinit var userViewModel: UserViewModel
    private  var uid:Long = 0
    private var tag:String="UpdateProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        tvemail = findViewById(R.id.email)
        tvpassword = findViewById(R.id.contraseña)
        tvnombres = findViewById(R.id.nombres)
        tvtelefono = findViewById(R.id.numero)
        bteditar = findViewById(R.id.btupdate)
        back_profile = findViewById(R.id.back_profile)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        intent.getStringExtra("id_user").let {
            Log.d(tag,"it $it")
            if (it.isNullOrEmpty()) {
                Log.d(tag,"it $it")
                Common.showToast(this, "ocurrio un error al mostrar los datos de dicho usuario")
            } else {
                uid = it.toLong()
                userViewModel.getUserForIud(uid)

            }

        }

        userViewModel.userForUid.observe(this) { user ->
            tvemail.text = user.correo
            tvpassword.text = user.password
            tvnombres.text = user.nombres
            tvtelefono.text = user.telefono.toString()

        }

        userViewModel.updateUser.observe(this){
            isUpdate->
                Log.d(tag,"isUpdate $isUpdate")

        }

        bteditar.setOnClickListener {

            val email = tvemail.text.toString()
            val password = tvpassword.text.toString()
            val names = tvnombres.text.toString()
            val phone = tvtelefono.text.toString()

            if (email.isEmpty() || password.isEmpty() || names.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
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
                userViewModel.updateUser(email,password,names,phone,uid)
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
        back_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }


}