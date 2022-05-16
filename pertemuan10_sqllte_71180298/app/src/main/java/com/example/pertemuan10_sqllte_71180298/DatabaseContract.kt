package com.example.pertemuan10_sqllte_71180298

import android.provider.BaseColumns

class DatabaseContract {

     object Penduduk{
         val TABLE_NAME = "penduduk"
         val COLUMN_NAME_NAMA = "nama"
         val COLUMN_NAME_USIA = "usia"

          val SQL_CREATE_TABLE = """CREATE TABLE ${TABLE_NAME}(
             ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
             ${COLUMN_NAME_NAMA} TEXT,
             ${COLUMN_NAME_USIA} INTERGER
             )""".trimMargin()

         val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
    }


}