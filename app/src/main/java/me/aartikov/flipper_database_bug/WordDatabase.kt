package me.aartikov.flipper_database_bug

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "word_database"
    }

   abstract fun wordDao(): WordDao
}