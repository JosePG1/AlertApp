package ipvc.estg.git.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.git.R
import ipvc.estg.git.entities.PersonalNotes

class  NotesAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var note = emptyList<PersonalNotes>()

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent,false)
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = note[position]
        holder.noteItemView.text = current.notes
    }

    internal fun setNotes(note: List<PersonalNotes>) {
        this.note = note
        notifyDataSetChanged()
    }

    override fun getItemCount() = note.size
}