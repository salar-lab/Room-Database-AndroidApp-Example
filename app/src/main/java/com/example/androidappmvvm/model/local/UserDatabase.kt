package com.example.androidappmvvm.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidappmvvm.model.entity.User


const val DATABASE_NAME = "user_database"

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    // Singleton
    companion object {

        // This instance is visiable to all classes
        @Volatile
        private var instance: UserDatabase? = null

        // this function returned the database when we habve object
        fun getInstance(context: Context): UserDatabase {
            // Only one Person can get in instance at the same time
            return instance ?: synchronized(Any()) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }

}