package com.example.sqlmcqapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: AppDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            var instance = INSTANCE
            synchronized(this){
                if (instance == null){
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "Question")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
            }
            return instance!!
        }
    }
}