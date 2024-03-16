package com.example.app_user.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_user.data.model.UserModel

@Dao
interface UserDao {
    @Query("Select * from user where correo=:correo and password=:password")
    fun getUserByCredentials(correo: String, password: String): UserModel?
    @Query("Select * from user")
    fun getUsers(): List<UserModel>

    @Query("select * from user where uid=:uid")
    fun getUserForUid(uid:Long):UserModel

    @Insert
    fun insert(user: UserModel):Long
    @Delete
    fun delete(user: UserModel)

@Update
    fun update(user:UserModel):Int


}