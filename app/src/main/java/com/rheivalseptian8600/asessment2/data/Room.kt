package com.rheivalseptian8600.asessment2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rheivalseptian8600.asessment2.model.Pengeluaran

@Database(entities = [Pengeluaran::class], version = 2, exportSchema = false)
abstract class PengeluaranDb : RoomDatabase() {
    abstract val dao: PengeluaranDao

    companion object {
        @Volatile
        private var INSTANCE: PengeluaranDb? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE pengeluaran ADD COLUMN isDeleted INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getInstance(context: Context): PengeluaranDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PengeluaranDb::class.java,
                        "pengeluaran.db"
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}