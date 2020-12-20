package com.refactory.test_refactory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.refactory.test_refactory.dao.UserDao
import com.refactory.test_refactory.model.User

@Database(entities = [User::class], version = 1)
abstract class DatabaseUser: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: DatabaseUser? = null

        fun getInstance(context: Context): DatabaseUser? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context,
                        DatabaseUser::class.java,
                        "user_db").build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}