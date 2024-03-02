package com.example.app_user.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app_user.data.model.UserModel

@Dao
interface UserDao {
    @Query("Select * from user where correo=:correo and password=:password")
    fun getUserByCredentials(correo: String, password: String): UserModel?
    @Query("Select * from user")
    fun getUsers(): List<UserModel>
    @Insert
    fun insert(user: UserModel)
    @Delete
    fun delete(user: UserModel)

}