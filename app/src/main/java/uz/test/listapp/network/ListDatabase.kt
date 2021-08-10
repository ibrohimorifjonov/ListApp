package uz.test.listapp.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.test.listapp.data.ListOfWork


@Database(entities = [ListOfWork::class], version = 1, exportSchema = false)
abstract class ListDatabase : RoomDatabase() {

    abstract fun listDao(): ListDao

    private class ListDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var listDao = database.listDao()

                    // Delete all content here.
               listDao.deleteAll()

                   
                   
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ListDatabase? = null

        fun getDatabase(
            context: Context
        ): ListDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListDatabase::class.java,
                    "list.db"
                )
                   // .createFromAsset("database/list.db")
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}