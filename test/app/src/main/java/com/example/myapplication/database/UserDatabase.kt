package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.dao.WordDao
import com.example.myapplication.database.model.User
import com.example.myapplication.database.model.Word

@Database(entities = [User::class, Word::class],version = 2,exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val wordDao: WordDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}