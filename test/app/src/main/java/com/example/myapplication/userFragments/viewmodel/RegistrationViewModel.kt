package com.example.myapplication.userFragments.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class RegistrationViewModel(val database: UserDao, application: Application)  : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _user= MutableLiveData<User>()
    val user: LiveData<User>
        get()=_user

    val stringUser= Transformations.map(user){
            user -> user.toString()
    }


    fun registration(user:User?):String?{
        this._user.value=user

        if(_user.value==null){
            Log.e("bug","user error")
        }else Log.d("user",_user.value!!.toString())
        Log.d("insert",database.insert(_user.value!!).toString())

        return _user.value?.username
    }
}
