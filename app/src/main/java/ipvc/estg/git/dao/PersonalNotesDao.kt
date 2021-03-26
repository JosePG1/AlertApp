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

    @Query("UPDATE notes_table SET title=:title, body=:body WHERE id=:id")
    suspend fun update(id:Int, title:String, body:String)

    @Query("DELETE FROM notes_table WHERE id=:id")
    suspend fun delete(id:Int)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}