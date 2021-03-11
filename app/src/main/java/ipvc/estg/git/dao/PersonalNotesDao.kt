package ipvc.estg.git.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.git.entities.PersonalNotes

@Dao
interface PersonalNotesDao {

    @Query("SELECT * FROM notes_table ORDER BY notes ASC")
    fun getAlphabetizedPersonalNote():  LiveData<List<PersonalNotes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: PersonalNotes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}