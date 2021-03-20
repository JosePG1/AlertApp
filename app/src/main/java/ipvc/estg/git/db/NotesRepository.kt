package ipvc.estg.git.db

import android.icu.text.CaseMap
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ipvc.estg.git.dao.PersonalNotesDao
import ipvc.estg.git.entities.PersonalNotes

class NotesRepository(private val personalNotesDao: PersonalNotesDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotes: LiveData<List<PersonalNotes>> = personalNotesDao.getAlphabetizedPersonalNote()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(notes: PersonalNotes) {
        personalNotesDao.insert(notes)
    }

}