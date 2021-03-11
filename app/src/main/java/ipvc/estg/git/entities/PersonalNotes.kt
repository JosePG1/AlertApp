package ipvc.estg.git.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")

data class PersonalNotes  (

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "notes") val notes: String

)