package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.database.model.User

@Dao
interface UserDao{

    @Insert
    public fun insert(user:User):Long

    @Update
    public fun update(user:User)

    @Query("select * from user where id = :key")
    public fun get(key: Long): User?

    @Query("select * from user where username=:username")
    public fun get(username:String):User?

    @Query("SELECT * FROM user ")
    public fun getAllUser(): LiveData<List<User>>
}