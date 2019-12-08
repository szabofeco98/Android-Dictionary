package com.example.myapplication.userFragments.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.model.User

class LoginViewModel( val database: UserDao, application: Application) : ViewModel() {

    private var users=database.getAllUser()

    private var _user= MutableLiveData<User>()
    val user:LiveData<User>
    get()=_user

    val stringUser=Transformations.map(user){
        user -> user.toString()
    }

    fun login(user: User?):Boolean{
        var valid=false
        _user.value=user
        users.observeForever {
            x->for(y:User in x){
                valid= (y.username ==_user.value?.username)&&(y.password== _user.value?.password)
                if(valid){
                    break
                }
            }
        }
        this._user.value=user
        return valid
    }

    fun test(){
        users.observeForever {
                x->Log.d("t",x.toString())
        }
    }

}
