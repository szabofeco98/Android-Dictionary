package com.example.myapplication.userFragments.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.model.User
import kotlinx.coroutines.*

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
        }else{
            uiScope.launch {
                insert(_user.value!!)
            }
        }

        return stringUser.value
    }

    private suspend fun insert(user: User){
        withContext(Dispatchers.IO){
             database.insert(user)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
