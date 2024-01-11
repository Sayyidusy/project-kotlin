package com.org.proyektingkat2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.org.proyektingkat2.model.User


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "siagabanjir.db"
        private const val TABLE_USERS = "users"
        private const val KEY_ID = "id"
        private const val KEY_NAMA_LENGKAP = "username"
        private const val KEY_NO_TLP = "nomor_telepon"
        private const val KEY_ALAMAT = "alamat"
        private const val KEY_PASSWORD = "password"
        private const val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = "CREATE TABLE $TABLE_USERS ($KEY_ID INTEGER PRIMARY KEY,$KEY_EMAIL TEXT , $KEY_NAMA_LENGKAP TEXT, $KEY_NO_TLP TEXT, $KEY_ALAMAT TEXT,$KEY_PASSWORD TEXT)"
        db.execSQL(createUsersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: User): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_EMAIL, user.email)
        values.put(KEY_NAMA_LENGKAP, user.namaLengkap)
        values.put(KEY_NO_TLP, user.noTelepon)
        values.put(KEY_ALAMAT, user.alamat)
        values.put(KEY_PASSWORD, user.password)
        return db.insert(TABLE_USERS, null, values)
    }




    fun getUser(email: String): User? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_EMAIL = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(email))
        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
            val namaLengkap = cursor.getString(cursor.getColumnIndex(KEY_NAMA_LENGKAP))
            val noTlp = cursor.getString(cursor.getColumnIndex(KEY_NO_TLP))
            val alamat = cursor.getString(cursor.getColumnIndex(KEY_ALAMAT))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            User(id, email, namaLengkap, noTlp, alamat, password)
        } else {
            null
        }
    }


    fun updateUser(user: User): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_EMAIL, user.email)
        values.put(KEY_NAMA_LENGKAP, user.namaLengkap)
        values.put(KEY_NO_TLP, user.noTelepon)
        values.put(KEY_ALAMAT, user.alamat)
        values.put(KEY_PASSWORD, user.password)

        return db.update(TABLE_USERS, values, "$KEY_ID = ?", arrayOf(user.id.toString()))
    }

    fun deleteUser(user: User): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_USERS, "$KEY_ID = ?", arrayOf(user.id.toString()))
    }

    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                val namaLengkap = cursor.getString(cursor.getColumnIndex(KEY_NAMA_LENGKAP))
                val noTlp = cursor.getString(cursor.getColumnIndex(KEY_NO_TLP))
                val alamat = cursor.getString(cursor.getColumnIndex(KEY_ALAMAT))
                val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
                val user = User(id, email, namaLengkap, noTlp, alamat, password)
                userList.add(user)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList
    }

}
