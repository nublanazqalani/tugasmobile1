package com.unhas.note.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int = 0,

    @ColumnInfo
    var title: String? = null,

    @ColumnInfo
    var description: String? = null,

    @ColumnInfo
    var date: String? = null
): Parcelable
