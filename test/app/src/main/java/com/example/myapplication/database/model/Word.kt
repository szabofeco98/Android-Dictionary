package com.example.myapplication.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word (
   @PrimaryKey(autoGenerate = true)
   var id:Long=0L,

   var hun:String="",
   var eng:String="",
   var user:String="")