package com.example.app_user.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_user.User
import com.example.app_user.data.model.UserModel
import com.example.app_user.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean>
        get() = _userSaved



    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList



    private val _userForUid=MutableLiveData<UserModel>()

    val userForUid:LiveData<UserModel>
        get()=_userForUid

    private val _updateUser = MutableLiveData<Int>()

    val updateUser:LiveData<Int>
        get()=_updateUser

    fun validateLogin(email: String, password: String) {
        viewModelScope.launch {
            val isValidLogin = userRepository.validateLogin(email, password)
            _loginResult.value = isValidLogin
        }
    }

    fun insertUser(new_user:UserModel) {
        viewModelScope.launch {
            val user = userRepository.insertUser(new_user)
            _userSaved.value = user
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            val users = userRepository.getUsers()
            _userList.value = users
        }
    }

    fun getUserForIud(uid:Long){
        viewModelScope.launch {
            val user=userRepository.getUserForUid(uid)
            _userForUid.value=user
        }
    }

    fun updateUser(    correo: String,
                       password: String,
                       nombres: String,
                       telefono: String,
                       uid: Long){
        viewModelScope.launch {
            val user=UserModel(uid,correo,password,nombres,telefono)
            val iduserupdate=userRepository.updateUser(user)
            Log.d("userviewModel","data $iduserupdate")
            _updateUser.value=iduserupdate
        }
    }


}