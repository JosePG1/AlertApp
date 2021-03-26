package ipvc.estg.git

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.git.adapters.NotesAdapter
import ipvc.estg.git.entities.PersonalNotes
import ipvc.estg.git.viewModel.NotesViewModel

class Notas : AppCompatActivity(), CellClickListener {



    private lateinit var notesViewModel: NotesViewModel
    private val newWordActivityRequestCode = 1
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NotesAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // view model
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        notesViewModel.allNotes.observe(this, Observer{ note ->
            note?.let { adapter.setNotes(it)}
        })

        // Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@Notas, AddNotas::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onCellClickListener(position: Int) {
        Toast.makeText(this,"Cell clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@Notas, EditNotes::class.java)
        intent.putExtra("id",adapter.getNotaPosition(position).id)
        intent.putExtra("title",adapter.getNotaPosition(position).title)
        intent.putExtra("body",adapter.getNotaPosition(position).body)
        startActivity(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
           val Ptitle = data?.getStringExtra(AddNotas.EXTRA_REPLY1)
            val Pbody = data?.getStringExtra(AddNotas.EXTRA_REPLY2)


            val nota = PersonalNotes(title=Ptitle.toString(), body=Pbody.toString())
            notesViewModel.insert(nota)

        } else {
            Toast.makeText(
                applicationContext,
                "Nota Vazia: nao inserida",
                Toast.LENGTH_LONG).show()

        }
    }
}

interface CellClickListener {

    fun onCellClickListener(position: Int)
}
