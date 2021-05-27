package com.unhas.note.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.unhas.note.database.NoteRepository
import com.unhas.note.database.entity.NoteEntity

class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<NoteEntity>> = mNoteRepository.getAllNotes()
}