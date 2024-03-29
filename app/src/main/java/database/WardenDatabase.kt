package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import clases.Actor
import clases.Book
import clases.Chapter
import clases.Film
import clases.Review
import clases.Score
import clases.Season
import clases.Serie
import clases.Title
import clases.User
import clases.relations.FilmActorCrossRef
import clases.relations.SerieActorCrossRef
import clases.relations.UserTitleCrossRef
import converters.RoomConverters
import modelos.BookDao
import modelos.FilmDao
import modelos.SerieDao
import modelos.TitleDao
import modelos.UserDao

@Database(
    entities = [User::class, Title::class, Actor::class, Book::class, Chapter::class, Film::class, Review::class, Score::class, Season::class, Serie::class, FilmActorCrossRef::class, SerieActorCrossRef::class, UserTitleCrossRef::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class WardenDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun titleDao(): TitleDao
    abstract fun bookDao(): BookDao
    abstract fun filmDao(): FilmDao
    abstract fun serieDao(): SerieDao

    //Se implementa el patron Singleton
    companion object {
        @Volatile
        private var INSTANCE: WardenDatabase? = null

        fun getDatabase(context: Context): WardenDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WardenDatabase::class.java,
                    "warden_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}