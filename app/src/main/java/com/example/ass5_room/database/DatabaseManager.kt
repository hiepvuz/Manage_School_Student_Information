package com.example.ass5_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ass5_room.database.dao.SchoolDao
import com.example.ass5_room.database.entity.School
import com.example.ass5_room.database.entity.Student

@Database(
    entities = [
        Student::class,
        School::class,
    ],
    version = 1
)

abstract class DatabaseManager : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getDatabase(context: Context): DatabaseManager {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseManager::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}