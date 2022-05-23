package com.example.pertemuan11_71180298

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mengaktifkan fitur cache agar data tetap dapat diakses meskipun kondisi perangkat sedang offline
        //db.firestoreSettings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

        val editNama = findViewById<EditText>(R.id.editNama)
        val editIpk = findViewById<EditText>(R.id.editIpk)
        val editNim = findViewById<EditText>(R.id.editNim)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnCari = findViewById<Button>(R.id.btnCari)

        btnSimpan.setOnClickListener{
        val p = Mahasiswa(editNama.text.toString(),editIpk.text.toString().toDouble(),editNim.text.toString())
        db.collection("mahasiswa").document(editNim.text.toString()).set(p)

            editNama.setText("")
            editNim.setText("")
            editIpk.setText("")
            refreshData()
        }
        btnHapus.setOnClickListener{
            db.collection("mahasiswa").document(editNim.text.toString()).delete()

            editNama.setText("")
            editNim.setText("")
            editIpk.setText("")
            refreshData()
        }
        btnCari.setOnClickListener{
            cari(editIpk.text.toString().toDouble())

            editNama.setText("")
            editNim.setText("")
            editIpk.setText("")

        }
        refreshData()
    }
    fun refreshData(){
        db.collection("mahasiswa")
            .get()
            .addOnSuccessListener {snapshot ->
            val viewHasil = findViewById<TextView>(R.id.viewHasil)
            var hasil = ""
            for(doc in snapshot){
                hasil += "Nama: ${doc.get("nama")}\nNIM: ${doc.get("nim")}--IPK: ${doc.get("ipk")}\n"

            }
            viewHasil.text = hasil
        }
    }
    fun cari(ipk: Double) {
        db.collection("mahasiswa")
            .whereEqualTo("ipk",ipk)
            .get()
            .addOnSuccessListener {snapshot ->
                val viewHasil = findViewById<TextView>(R.id.viewHasil)
                var hasil = ""
                for(doc in snapshot){
                    hasil += "Nama: ${doc.get("nama")}\nNIM: ${doc.get("nim")}--IPK: ${doc.get("ipk")}\n"

                }
                viewHasil.text = hasil
            }
    }
}