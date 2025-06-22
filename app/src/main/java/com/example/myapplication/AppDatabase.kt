package com.example.myapplication
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Entities.Perro
import com.example.myapplication.Entities.PerroDAO

@Database(
    entities = [Perro::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun perroDao(): PerroDAO

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "perros_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}