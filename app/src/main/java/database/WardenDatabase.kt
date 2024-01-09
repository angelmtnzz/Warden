package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import clases.Title
import clases.User
import modelos.UserDao

@Database(entities = [User::class, Title::class], version = 1, exportSchema = false)
abstract class WardenDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: WardenDatabase? = null

        fun getDatabase(context: Context): WardenDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
              return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WardenDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}