package com.rathalove.kunapheapadmin.RoomData.UserLogIn

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities =  [UserData::class], exportSchema =  false, version = 1)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object{
        private const val DB__NAME = "db_user"
        private var userDatabase: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase{
            return userDatabase?: synchronized(this){
                val instance = Room.databaseBuilder(context, UserDatabase::class.java, DB__NAME).fallbackToDestructiveMigration().build()
                userDatabase = instance
                return instance
            }
        }
    }
}