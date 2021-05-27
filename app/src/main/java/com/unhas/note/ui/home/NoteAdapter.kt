package com.unhas.note.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unhas.note.database.entity.NoteEntity
import com.unhas.note.databinding.ItemNoteBinding
import com.unhas.note.ui.insertupdate.InsertUpdateActivity
import com.unhas.note.utils.NoteDiffCallback

class NoteAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<NoteEntity>()

    fun setListNotes(listNotes: List<NoteEntity>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }
    override fun getItemCount(): Int {
        return listNotes.size
    }
    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(activity, InsertUpdateActivity::class.java)
                    intent.putExtra(InsertUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(InsertUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, InsertUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }
}