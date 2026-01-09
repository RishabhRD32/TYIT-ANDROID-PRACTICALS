package com.example.practical9b

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context) : SQLiteOpenHelper(context, "UserDB.db", null, 1) {

    // Define Table and Columns
    companion object {
        private const val TABLE_NAME = "users"
        private const val COL_ID = "userid"
        private const val COL_NAME = "name"
        private const val COL_AGE = "age"
    }

    // Create Table
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_AGE + " TEXT)")
        db.execSQL(createTable)
    }

    // Upgrade Table
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // INSERT User
    fun insertUser(userid: String, name: String, age: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, userid)
        contentValues.put(COL_NAME, name)
        contentValues.put(COL_AGE, age)

        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    // DELETE User
    fun deleteUser(userid: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COL_ID = ?", arrayOf(userid))
    }

    // READ All Users
    fun readAllUsers(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}