package com.example.myapplication.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    var name:String="",
    var username:String="",
    var password:String="",
    var email:String="")