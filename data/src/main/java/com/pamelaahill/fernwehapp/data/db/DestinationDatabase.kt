package com.pamelaahill.fernwehapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Destination::class, DestinationImage::class], version = 1)
abstract class DestinationDatabase : RoomDatabase() {

    abstract fun destinationDao(): DestinationDao

    companion object {
        private lateinit var instance: DestinationDatabase

        fun getInstance(context: Context): DestinationDatabase {
            if (!::instance.isInitialized) {
                instance =
                    Room.databaseBuilder(context, DestinationDatabase::class.java, "destinations")
                        .build()
            }

            return instance
        }
    }
}