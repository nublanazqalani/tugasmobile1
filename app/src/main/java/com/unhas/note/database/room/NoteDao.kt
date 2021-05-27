package com.unhas.note.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unhas.note.database.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("SELECT * from noteentity ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>
}