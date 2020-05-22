package me.aartikov.flipper_database_bug

import android.app.Application
import androidx.room.Room
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.databases.impl.SqliteDatabaseDriver
import com.facebook.flipper.plugins.databases.impl.SqliteDatabaseProvider
import com.facebook.soloader.SoLoader
import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory
import java.io.File

class App : Application() {

    lateinit var database: WordDatabase

    override fun onCreate() {
        super.onCreate()
        initDatabase()
        initFlipper()
    }

    private fun initDatabase() {
        database = Room.databaseBuilder(this, WordDatabase::class.java, WordDatabase.DATABASE_NAME)
            .openHelperFactory(RequerySQLiteOpenHelperFactory())  // !!!
            .build()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this).apply {
            addDatabasePlugin()
        }
        client.start()
    }

    private fun FlipperClient.addDatabasePlugin() {
        val databaseProvider = SqliteDatabaseProvider {
            listOf(
                File(applicationInfo.dataDir, "/databases/${WordDatabase.DATABASE_NAME}")
            )
        }
        addPlugin(DatabasesFlipperPlugin(SqliteDatabaseDriver(this@App, databaseProvider)))
    }
}