package com.rahmanaulia.mynotesapp.db

import android.net.Uri
import android.provider.BaseColumns
import com.rahmanaulia.mynotesapp.BuildConfig

object DatabaseContract {

    const val AUTHORITY = BuildConfig.APPLICATION_ID
    const val SCHEME = "content"

    internal class NoteColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"

            // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }

}