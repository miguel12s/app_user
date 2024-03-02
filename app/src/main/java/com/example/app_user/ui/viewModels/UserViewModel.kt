package com.example.app_user.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_user.data.model.UserModel
import com.example.app_user.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    private val userRepository=UserRepository(application)

    private val _loginResult=MutableLiveData<Boolean>()
    val loginResult:LiveData<Boolean>
        get()=_loginResult

    private val _userSaved=MutableLiveData<Boolean>()
    val userSaved:LiveData<Boolean>
        get()=_userSaved

    private val _userList=MutableLiveData<List<Boolean>>()
    val userList:LiveData<List<Boolean>>
        get()=_userList

    fun validateLogin(user:String,password:String){
        viewModelScope.launch {
            val isValidLogin=userRepository.validateLogin(user,password)
            _loginResult.value=isValidLogin
        }
    }

    fun saveUser(user:UserModel){

    }





}