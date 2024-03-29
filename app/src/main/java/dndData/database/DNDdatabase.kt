package dndData.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dndData.dao.*
import dndData.entities.*

@Database(entities = arrayOf(
        Campagna::class,
        Scheda::class,
        Notes::class,
        Pedina::class,
        Mappa::class,
        Incantesimi::class,
        Oggetti::class
    ),
    version = 1,
        exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class DNDdatabase: RoomDatabase() {


    abstract fun getCampagnaDAO(): CampagnaDAO
    abstract fun getNotesDAO(): NotesDAO
    abstract fun getSchedaDAO(): SchedaDAO
    abstract fun getMappaDAO(): MappaDAO
    abstract fun getPedinaDAO(): PedinaDAO

    companion object{
        @Volatile
        private var INSTANCE: DNDdatabase?= null

        fun getDatabase(context: Context): DNDdatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DNDdatabase::class.java,
                    "DND_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}