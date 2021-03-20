package ipvc.estg.git.viewModel

import android.app.Application
import androidx.lifecycle.*
import ipvc.estg.git.db.NotesDB
import ipvc.estg.git.db.NotesRepository
import ipvc.estg.git.entities.PersonalNotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNotes: LiveData<List<PersonalNotes>>

    init {
        val notesDao = NotesDB.getDatabase(application, viewModelScope).personalNotesDao()
        repository = NotesRepository(notesDao)
        allNotes = repository.allNotes



    }



    fun insert(notes: PersonalNotes) = viewModelScope.launch {
        repository.insert(notes)
    }

}