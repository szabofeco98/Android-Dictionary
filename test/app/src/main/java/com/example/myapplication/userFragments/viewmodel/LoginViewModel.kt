package com.example.myapplication.userFragments.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.model.User
import kotlinx.coroutines.*


class LoginViewModel( val database: UserDao, application: Application) : ViewModel() {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private  var usersFromDb=database.getAllUser()

    private lateinit var users:List<User>

    private var _user= MutableLiveData<User>()
    val user:LiveData<User>
    get()=_user

    init {
       initUsers()
    }

    val stringUser=Transformations.map(user){
        user -> user.toString()
    }

    fun login(user: User?):Boolean{
        var valid=false
        
        _user.value=user
        Log.e("u",users.toString())
        for(u in users){
            valid=(u.username==user?.username)&&(u.password==user.password)
            if(valid){
                break
            }
        }
        this._user.value=user
        return valid
    }


    fun initUsers(){
        usersFromDb.observeForever {
                x->users = x
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
