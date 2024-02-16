package com.example.app_user

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {
    private lateinit var btemail: Button
    private lateinit var btcall:Button
    private lateinit var btmsn:Button
    private lateinit var bteditar:Button
    private lateinit var back_login2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        btemail=findViewById(R.id.btemail)
        btcall=findViewById(R.id.btcall)
        btmsn=findViewById(R.id.btmsn)
        bteditar=findViewById(R.id.bteditardatos)
        back_login2=findViewById(R.id.back_login2)
        val intent=intent
        val user=intent.getSerializableExtra("user") as User
btemail.setOnClickListener{
    val intent= Intent(Intent.ACTION_SENDTO)
    intent.data= Uri.parse("mailto:${user.email}")
    intent.putExtra(Intent.EXTRA_SUBJECT,"asunto del correo")
    intent.putExtra(Intent.EXTRA_TEXT,"cuerpo del correo")
    startActivity(intent)
}
     btcall.setOnClickListener{
         val intent= Intent(Intent.ACTION_DIAL)
         intent.data= Uri.parse("tel:${user.telefono}")
         startActivity(intent)
     }
        btmsn.setOnClickListener{
            val intent= Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("sms:${user.telefono}")
            intent.putExtra("sms_body","mensaje de ${user.names}")

            startActivity(intent)
        }
bteditar.setOnClickListener {
    val intent=Intent(this,UpdateProfileActivity::class.java)
    intent.putExtra("user",user)
    startActivity(intent)
}
        back_login2.setOnClickListener{
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}