package com.example.app_user

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app_user.data.model.UserModel
import com.example.app_user.ui.viewModels.UserViewModel
import com.example.app_user.utils.Common

class ProfileActivity : AppCompatActivity() {
    private lateinit var btemail: Button
    private lateinit var btcall: Button
    private lateinit var btmsn: Button
    private lateinit var bteditar: Button
    private lateinit var btlogout: Button

    private lateinit var back_login2: ImageView
    private lateinit var userViewModel:UserViewModel
    private var user:UserModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        btemail = findViewById(R.id.btemail)
        btcall = findViewById(R.id.btcall)
        btmsn = findViewById(R.id.btmsn)
        bteditar = findViewById(R.id.bteditardatos)
        back_login2 = findViewById(R.id.back_login2)
        btlogout=findViewById(R.id.btlogout)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.userProfile.observe(this, Observer { userProfile->

            if (userProfile!=null){
                user=userProfile
            }else{
                Common.showToast(this,"hubo un error al obtener el usuario")
            }



        })
        btemail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user?.correo}")
            intent.putExtra(Intent.EXTRA_SUBJECT, "asunto del correo")
            intent.putExtra(Intent.EXTRA_TEXT, "cuerpo del correo")
            startActivity(intent)
        }
        btcall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user?.telefono}")
            startActivity(intent)
        }
        btmsn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:${user?.telefono}")
            intent.putExtra("sms_body", "mensaje de ${user?.nombres}")

            startActivity(intent)
        }
        bteditar.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            //intent.putExtra("user", user)
            startActivity(intent)
        }
        back_login2.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btlogout.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        closeSession()
        goLogin()
    }
    private fun goLogin(){
        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
    private fun closeSession(){
        val sp=getSharedPreferences("parcial_movil", MODE_PRIVATE)
        val edit=sp.edit()
        edit.remove("email")
        edit.remove("password")
        edit.remove("nombre")
        edit.remove("telefono")
        edit.apply()
    }
}