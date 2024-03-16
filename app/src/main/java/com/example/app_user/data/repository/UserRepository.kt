package com.example.app_user.data.repository

import android.content.Context
import com.example.app_user.data.db.AppDatabase
import com.example.app_user.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {

    private val database = AppDatabase.getInstance(context)

    suspend fun validateLogin(correo: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val userFromDb = database.userDao().getUserByCredentials(correo, password)
            userFromDb != null
        }

    }

    suspend fun updateUser(
       user:UserModel
    ):Int {
        return withContext(Dispatchers.IO) {
             database.userDao().update(
               user)
        }
    }

    suspend fun getUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            database.userDao().getUsers()
        }
    }

    suspend fun getUserForUid(uid: Long): UserModel {
        return withContext(Dispatchers.IO) {
            database.userDao().getUserForUid(uid)


        }
    }

    suspend fun insertUser(user: UserModel): Boolean {
        return withContext(Dispatchers.IO) {
            val userId = database.userDao().insert(user)
            userId.toInt() != 0

        }
    }
}