package com.example.kanji

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.*

class CopyDBHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
//    phuong thuc tinh

    companion object{
        private const val DB_NAME = "kanji2.db"
        private const val DB_VERSION = 1
    }
    private var db:SQLiteDatabase? = null

    init {
        val dbPath = context.getDatabasePath(DB_NAME).path
        // nếu database đã tồn tại, mở database:
        if (isDataBaseExists(dbPath)){
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
        }else{
            // nếu database chưa tồn tại, tạo mới database:
            try {
                copyDatabaseFromAsset()
                db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    private fun isDataBaseExists(dbPath: String): Boolean {
        val file = context.getDatabasePath(DB_NAME)
        return file.exists()
    }
    @Throws(IOException::class)
    private fun copyDatabaseFromAsset() {
        val inputStream: InputStream = context.assets.open(DB_NAME)
        val outputStream: OutputStream = FileOutputStream(context.getDatabasePath(DB_NAME))

        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }
    fun getAllKanji(): Cursor? {
        return db?.rawQuery("SELECT * FROM kanji", null)
    }
    fun getSaveKanji(): Cursor? {
        return db?.rawQuery("SELECT * FROM kanji Where save = 1", null)
    }
    fun getN4Kanji(): Cursor?{
        return db?.rawQuery("SELECT * FROM kanji Where jlpt = 4", null)
    }
    fun getN3Kanji(): Cursor?{
        return db?.rawQuery("SELECT * FROM kanji Where jlpt = 3", null)
    }
    fun getN2Kanji(): Cursor?{
        return db?.rawQuery("SELECT * FROM kanji Where jlpt = 2", null)
    }
    fun getN1Kanji(): Cursor?{
        return db?.rawQuery("SELECT * FROM kanji Where jlpt = 1", null)
    }
    fun getKanjiById(id: Int): Cursor? {
        val args = arrayOf(id.toString())
        return db?.rawQuery("SELECT * FROM kanji WHERE id = ?", args)
    }

    fun searchKanji(str : String):Cursor?{
        val args = arrayOf(str)
        return db?.rawQuery("SELECT * FROM kanji WHERE kanji = ?", args)
    }

    fun setSave(str : String): Cursor? {
        val args = arrayOf(str)
        return db?.rawQuery("UPDATE kanji SET save = 0 WHERE kanji = ?", args)
    }
    fun setSave1(str: String): Cursor? {
        val args = arrayOf(str)
        return db?.rawQuery("UPDATE kanji SET save = 1 WHERE kanji = ?", args)
    }


//    fun openDataBase(): SQLiteDatabase{
//        val dbFile = context.getDatabasePath(DB_NAME)
//        val file = File(dbFile.toString())
////        neu file da ton tai thi ko them db vao nua tranh mat du lieu
//        if (file.exists()){
//            Log.e("kanji", "Da ton tai!")
//        }else{
//            copyDatabase(dbFile)
//        }
//        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
//    }
//
//    private fun copyDatabase(dbFile: File?) {
//        val openDB = context.assets.open(DB_NAME)
//        val outputStream = FileOutputStream(dbFile)
////        chia doan nho de copy
//        val buffer = ByteArray(1024)
////        con du lieu la con doc
//        while (openDB.read(buffer)>0){
//            outputStream.write(buffer)
//            Log.wtf("DB", "writing")
//        }
//        outputStream.flush()
//        outputStream.close()
//        openDB.close()
//        Log.wtf("DB", "Copy complete")
//    }



}