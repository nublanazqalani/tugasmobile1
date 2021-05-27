package com.unhas.note.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.unhas.note.database.entity.NoteEntity
import com.unhas.note.database.room.NoteDao
import com.unhas.note.database.room.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> = mNotesDao.getAllNotes()

    fun insert(note: NoteEntity) {
        executorService.execute { mNotesDao.insert(note) }
    }

    fun delete(note: NoteEntity) {
        executorService.execute { mNotesDao.delete(note) }
    }

    fun update(note: NoteEntity) {
        executorService.execute { mNotesDao.update(note) }
    }
}