package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.database.model.User
import com.example.myapplication.database.model.Word

@Dao
interface WordDao {
    @Insert
    public fun insert(word: Word):Long

    @Update
    public fun update(word: Word)

    @Query("select * from word where id = :key")
    public fun get(key: Long): Word?

    @Query("SELECT * FROM word ")
     fun getAllWord(): LiveData<List<Word>>
}