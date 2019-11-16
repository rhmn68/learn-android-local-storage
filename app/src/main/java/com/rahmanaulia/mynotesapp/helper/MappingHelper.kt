package com.rahmanaulia.mynotesapp.helper

import android.database.Cursor
import com.rahmanaulia.mynotesapp.db.DatabaseContract.NoteColumns.Companion.DATE
import com.rahmanaulia.mynotesapp.db.DatabaseContract.NoteColumns.Companion.DESCRIPTION
import com.rahmanaulia.mynotesapp.db.DatabaseContract.NoteColumns.Companion.TITLE
import com.rahmanaulia.mynotesapp.db.DatabaseContract.NoteColumns.Companion._ID
import com.rahmanaulia.mynotesapp.entity.Note

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor): ArrayList<Note> {
        val notesList = ArrayList<Note>()
        while (notesCursor.moveToNext()) {
            val id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID))
            val title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE))
            val description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DESCRIPTION))
            val date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DATE))
            notesList.add(Note(id, title, description, date))
        }
        return notesList
    }
}