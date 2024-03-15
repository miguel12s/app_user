package com.example.app_user.ui.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_user.R
import com.example.app_user.UpdateProfileActivity
import com.example.app_user.data.model.UserModel
import kotlinx.coroutines.withContext

class UserAdapter(private val userList: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val tag = "UserAdapter"

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserModel) {
            itemView.findViewById<TextView>(R.id.nombresUser).text = user.nombres
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(tag, "getItemCount:${userList.size}")
        return userList.size

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        holder.itemView.findViewById<Button>(R.id.btneditarcard).setOnClickListener { v:View->
            val intent=Intent(v.context,UpdateProfileActivity::class.java)
            intent.putExtra("id_user",user.uid.toString())
            v.context.startActivity(intent)



        }

        holder.itemView.findViewById<Button>(R.id.btncallcard).setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user.telefono}")
            v.context.startActivity(intent)

        }
        holder.itemView.findViewById<Button>(R.id.btnmsncard).setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:${user.telefono}")
            intent.putExtra("sms_body", "mensaje de ${user.nombres}")
            v.context.startActivity(intent)

        }
        holder.itemView.findViewById<Button>(R.id.btnemailcard).setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user.correo}")
            intent.putExtra(Intent.EXTRA_SUBJECT, "asunto del correo")
            intent.putExtra(Intent.EXTRA_TEXT, "cuerpo del correo")
            v.context.startActivity(intent)
        }



    }
}