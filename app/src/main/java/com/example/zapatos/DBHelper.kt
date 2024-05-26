package com.example.zapatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class User(val id: Int, val username: String, val password: String)

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ShoesDB"
        private const val DATABASE_VERSION = 1

        // Tabla de zapatos
        private const val TABLE_SHOES = "shoes"
        private const val COLUMN_SHOE_ID = "id"
        private const val COLUMN_SHOE_NAME = "name"
        private const val COLUMN_SHOE_DESCRIPTION = "description"
        private const val COLUMN_SHOE_PRICE = "price"
        private const val COLUMN_SHOE_IMAGE_URL = "image_url"

        // Tabla de usuarios
        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_USERNAME = "username"
        private const val COLUMN_USER_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createShoesTableSQL = "CREATE TABLE $TABLE_SHOES (" +
                "$COLUMN_SHOE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_SHOE_NAME TEXT," +
                "$COLUMN_SHOE_DESCRIPTION TEXT," +
                "$COLUMN_SHOE_PRICE REAL," +
                "$COLUMN_SHOE_IMAGE_URL TEXT" +
                ")"

        val createUsersTableSQL = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USER_USERNAME TEXT," +
                "$COLUMN_USER_PASSWORD TEXT" +
                ")"

        db.execSQL(createShoesTableSQL)
        db.execSQL(createUsersTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SHOES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Operaciones de zapatos
    fun insertShoe(name: String, description: String, price: Double, imageUrl: String): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_SHOE_NAME, name)
            put(COLUMN_SHOE_DESCRIPTION, description)
            put(COLUMN_SHOE_PRICE, price)
            put(COLUMN_SHOE_IMAGE_URL, imageUrl)
        }
        return db.insert(TABLE_SHOES, null, contentValues)
    }

    fun updateShoe(id: Int, name: String, description: String, price: Double, imageUrl: String): Int {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_SHOE_NAME, name)
            put(COLUMN_SHOE_DESCRIPTION, description)
            put(COLUMN_SHOE_PRICE, price)
            put(COLUMN_SHOE_IMAGE_URL, imageUrl)
        }
        return db.update(TABLE_SHOES, contentValues, "$COLUMN_SHOE_ID=?", arrayOf(id.toString()))
    }

    fun deleteShoe(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_SHOES, "$COLUMN_SHOE_ID=?", arrayOf(id.toString()))
    }

    fun getAllShoes(): ArrayList<Shoe> {
        val shoes = ArrayList<Shoe>()
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_SHOES", null)
        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_SHOE_ID))
                    val name = it.getString(it.getColumnIndexOrThrow(COLUMN_SHOE_NAME))
                    val description = it.getString(it.getColumnIndexOrThrow(COLUMN_SHOE_DESCRIPTION))
                    val price = it.getDouble(it.getColumnIndexOrThrow(COLUMN_SHOE_PRICE))
                    val imageUrl = it.getString(it.getColumnIndexOrThrow(COLUMN_SHOE_IMAGE_URL))
                    shoes.add(Shoe(id, name, description, price, imageUrl))
                } while (it.moveToNext())
            }
            it.close()
        }
        return shoes
    }

    // Operaciones de usuarios
    fun addUser(username: String, password: String): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USER_USERNAME, username)
            put(COLUMN_USER_PASSWORD, password)
        }
        return db.insert(TABLE_USERS, null, contentValues)
    }

    fun getUser(username: String, password: String): User? {
        val db = readableDatabase
        val cursor: Cursor? = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USER_ID, COLUMN_USER_USERNAME, COLUMN_USER_PASSWORD),
            "$COLUMN_USER_USERNAME = ? AND $COLUMN_USER_PASSWORD = ?",
            arrayOf(username, password),
            null, null, null
        )
        return if (cursor?.moveToFirst() == true) {
            val user = User(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_USERNAME)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PASSWORD))
            )
            cursor.close()
            user
        } else {
            cursor?.close()
            null
        }
    }
}
