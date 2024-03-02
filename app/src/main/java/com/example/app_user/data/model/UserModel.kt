package com.example.app_user.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")

data class UserModel(

    @PrimaryKey(autoGenerate = true)
    val uid: Long? = null,
    @ColumnInfo(name = "correo")
    val correo: String?,
    @ColumnInfo(name = "password")
    val password: String?,
    @ColumnInfo(name = "nombres")
    val nombres: String?,

    @ColumnInfo(name = "telefono")
    val telefono: String?




)
