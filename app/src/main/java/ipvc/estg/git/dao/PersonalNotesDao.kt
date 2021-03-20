package ipvc.estg.git.dao

import android.icu.text.CaseMap
import androidx.lifecycle.LiveData
import androidx.room.*
import ipvc.estg.git.entities.PersonalNotes

@Dao
interface   PersonalNotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAlphabetizedPersonalNote():  LiveData<List<PersonalNotes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: PersonalNotes)


    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}