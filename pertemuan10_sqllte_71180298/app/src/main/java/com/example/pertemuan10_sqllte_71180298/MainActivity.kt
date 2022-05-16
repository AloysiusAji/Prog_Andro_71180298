package com.example.pertemuan10_sqllte_71180298

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {

    var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this).writableDatabase

        val editNama = findViewById<EditText>(R.id.editNama)
        val editUsia = findViewById<EditText>(R.id.editUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnCari = findViewById<Button>(R.id.btnCari)

        btnSimpan.setOnClickListener{
            val values = ContentValues().apply {
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, editNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, editUsia.text.toString())

            }
            db?.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)

            editNama.setText("")
            editUsia.setText("")
            refreshData()
        }
        btnHapus.setOnClickListener{
            val selection = "nama = ? OR usia = ?"
            val selectionArgs = arrayOf(editNama.text.toString(), editUsia.text.toString())

            db?.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)

            editNama.setText("")
            editUsia.setText("")
            refreshData()
        }
        btnCari.setOnClickListener{
            val values = ContentValues().apply{
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, editNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, editUsia.text.toString())
            }
            db?.update(DatabaseContract.Penduduk.TABLE_NAME, values, BaseColumns._ID +"?", arrayOf(editNama.text.toString()))
            db?.update(DatabaseContract.Penduduk.TABLE_NAME, values, BaseColumns._ID +"?", arrayOf(editUsia.text.toString()))
            editNama.setText("")
            editUsia.setText("")
            searchData()
            refreshData()
        }
        refreshData()
    }
    fun refreshData(){
        val viewHasil= findViewById<TextView>(R.id.viewHasil)
        val columns = arrayOf(BaseColumns._ID, DatabaseContract.Penduduk.COLUMN_NAME_NAMA, DatabaseContract.Penduduk.COLUMN_NAME_USIA)
        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        var result = ""
        with(cursor){
            while (this!!.moveToNext()){
                result += "${this!!.getString(1)}---${this!!.getString(2)}--tahun\n"
            }
        }
        viewHasil.text = result
    }

    fun searchData(){
        val viewHasil= findViewById<TextView>(R.id.viewHasil)
        val columns = arrayOf(BaseColumns._ID, DatabaseContract.Penduduk.COLUMN_NAME_NAMA, DatabaseContract.Penduduk.COLUMN_NAME_USIA)
        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            "name = ? OR usia = ?",
            arrayOf("aloysius", "10"),
            null,
            null,
            null
        )
        var result = ""
        with(cursor){
            while (this!!.moveToNext()){
                result += "${this!!.getString(1)}---${this!!.getString(2)}--tahun\n"
            }
        }
        viewHasil.text = result
    }
}