package com.unhas.note.utils

import androidx.recyclerview.widget.DiffUtil
import com.unhas.note.database.entity.NoteEntity

class NoteDiffCallback(private val mOldNoteList: List<NoteEntity>, private val mNewNoteList: List<NoteEntity>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = mOldNoteList[oldItemPosition]
        val newNote = mNewNoteList[newItemPosition]
        return oldNote.title == newNote.title && oldNote.description == newNote.description
    }

}