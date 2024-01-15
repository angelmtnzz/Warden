package converters

import androidx.room.TypeConverter
import java.util.*

class RoomConverters {

    @TypeConverter
    fun convertDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(timeLong: Long) : Date {
        return Date(timeLong)
    }
}