package ipvc.estg.git.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.git.dao.PersonalNotesDao
import ipvc.estg.git.entities.PersonalNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(PersonalNotes::class), version =1 , exportSchema = false)
public abstract class NotesDB : RoomDatabase() {

    abstract fun personalNotesDao(): PersonalNotesDao

    private class NotesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                database ->
                scope.launch {
                    var personalNotesDao = database.personalNotesDao()

                    // Delete all content here
                    personalNotesDao.deleteAll()

                    // And sample words
                    var notes = PersonalNotes(1, "Primeira Nota")
                    personalNotesDao.insert(notes)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotesDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "notes_database"
                )

                        //.fallbackToDestructiveMigration()
                    .addCallback(NotesDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}




