package com.example.myapplication.userFragments.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.userFragments.viewmodel.LoginViewModel
import com.example.myapplication.userFragments.viewmodel.RegistrationViewModel

class RegistrationViewModellFactory(
    private val dataSource: UserDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}