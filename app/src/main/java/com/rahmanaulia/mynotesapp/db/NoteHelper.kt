package com.rahmanaulia.mynotesapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rahmanaulia.mynotesapp.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.rahmanaulia.mynotesapp.db.DatabaseContract.NoteColumns.Companion._ID
import java.sql.SQLException

class NoteHelper(context: Context) {

    companion object{
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var dataBaseHelper : DatabaseHelper
        private var INSTANCE: NoteHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): NoteHelper{
            if (INSTANCE == null){
                synchronized(SQLiteOpenHelper::class){
                    if (INSTANCE == null){
                        INSTANCE = NoteHelper(context)
                    }
                }
            }
            return INSTANCE as NoteHelper
        }
    }

    init {
        dataBaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open(){
        database = dataBaseHelper.writableDatabase
    }

    fun close(){
        dataBaseHelper.close()

        if (database.isOpen){
            database.close()
        }
    }

    fun queryAll(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null)
    }

    fun queryById(id: String): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?): Long{
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int{
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int{
        return database.delete(TABLE_NAME, "$_ID = $id", null)
    }
}