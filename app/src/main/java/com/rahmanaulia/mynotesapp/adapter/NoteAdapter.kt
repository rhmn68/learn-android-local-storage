package com.rahmanaulia.mynotesapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahmanaulia.mynotesapp.CustomOnItemClickListener
import com.rahmanaulia.mynotesapp.NoteAddUpdateActivity
import com.rahmanaulia.mynotesapp.R
import com.rahmanaulia.mynotesapp.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val activity: Activity, val listener:(Note, Int) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    var listNotes = ArrayList<Note>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount(): Int = this.listNotes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position], listener)
    }

    fun addItem(note: Note){
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note){
        this.listNotes[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int){
        this.listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNotes.size)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            note: Note,
            listener: (Note, Int) -> Unit
        ){
            with(itemView){
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description
//                cv_item_note.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemCallback{
//                    override fun onItemClicked(view: View?, position: Int) {
//                        val intent = Intent(activity, NoteAddUpdateActivity::class.java)
//                        intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
//                        intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
//                        activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
//                    }
//
//                }))
                this.setOnClickListener {
                    listener(note, adapterPosition)
                }
            }
        }
    }
}