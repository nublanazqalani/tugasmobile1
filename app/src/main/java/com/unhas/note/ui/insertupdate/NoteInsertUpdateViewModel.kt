package com.unhas.note.ui.insertupdate

import android.app.Application
import androidx.lifecycle.ViewModel
import com.unhas.note.database.NoteRepository
import com.unhas.note.database.entity.NoteEntity

class NoteInsertUpdateViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: NoteEntity) {
        mNoteRepository.insert(note)
    }

    fun update(note: NoteEntity) {
        mNoteRepository.update(note)
    }

    fun delete(note: NoteEntity) {
        mNoteRepository.delete(note)
    }
}