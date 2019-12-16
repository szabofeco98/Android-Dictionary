package com.example.myapplication.appFragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.dao.WordDao
import com.example.myapplication.database.model.User
import com.example.myapplication.database.model.Word

class ApplicationViewModel (val database: WordDao ): ViewModel() {
    var words=database.getAllWord()


    fun insert(word:Word){
        database.insert(word)
    }


}
