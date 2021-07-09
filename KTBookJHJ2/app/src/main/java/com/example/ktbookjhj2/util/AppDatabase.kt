package com.example.ktbookjhj2.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ktbookjhj2.dao.HistoryDao
import com.example.ktbookjhj2.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}