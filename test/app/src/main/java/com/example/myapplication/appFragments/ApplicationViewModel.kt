package com.example.myapplication.appFragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.dao.WordDao
import com.example.myapplication.database.model.User
import com.example.myapplication.database.model.Word

class ApplicationViewModel (val database: WordDao ): ViewModel() {
    private  var wordFromDb=database.getAllWord()

    private lateinit var words:List<Word>

    init {
        setWords()
    }

    fun insert(word:Word){
        database.insert(word)
    }

    fun setWords(){
        //Log.d("m","meghibodik")

        wordFromDb.observeForever {
                x-> words=x
        }
      //  Log.e("test",wordFromDb.value.toString())
    }

    fun getWords():List<Word>{
        setWords()
        return words
    }
}
