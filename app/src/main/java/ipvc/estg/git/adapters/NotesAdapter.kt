package ipvc.estg.git.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.git.CellClickListener
import ipvc.estg.git.Notas
import ipvc.estg.git.R
import ipvc.estg.git.entities.PersonalNotes

class  NotesAdapter internal constructor(
    context: Context,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var nota = emptyList<PersonalNotes>()


    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView = itemView.findViewById(R.id.textView)
        val titleItemView: TextView = itemView.findViewById(R.id.textViewTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent,false)
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = nota[position]
        holder.titleItemView.text = current.title
        holder.noteItemView.text = current.body

        holder.titleItemView.setOnClickListener {
            cellClickListener.onCellClickListener(position)
        }
    }

    internal fun setNotes(nota: List<PersonalNotes>) {
        this.nota = nota
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
       return nota.size
    }

    fun getNotaPosition(position: Int): PersonalNotes{
        return nota[position];
    }




}