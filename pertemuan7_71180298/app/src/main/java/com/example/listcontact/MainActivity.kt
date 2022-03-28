package com.example.listcontact

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactList = listOf<Contact>(
            Contact(
                R.drawable.jett,
                "Ayang1",
                "08xxxxxxxxxx",
                  "Mar 10,2022"
            ),
            Contact(
                R.drawable.ei,
                "Ayang2",
                "08xxxxxxxxxx",
                "Jan 07,2022"
            ),
            Contact(
                R.drawable.flame,
                "Ayang3",
                "08xxxxxxxxxx",
                "Feb 01,2022"
            ),
            Contact(
                R.drawable.cupang,
                "Ayang4",
                "08xxxxxxxxxx",
                "May 12, 2021"
            ),
            Contact(
                R.drawable.beni,
                "Beni",
                "08xxxxxxxxxx",
                "Mar 11, 2019"

            ),
            Contact(
                R.drawable.beni,
                "Benimaru",
                "08xxxxxxxxxx",
                "Feb 01, 2022"
            ),
            Contact(
                R.drawable.iru,
                "Iruma",
                "08xxxxxxxxxx",
                "Sep 01, 2021"
            ),
            Contact(
                R.drawable.iru,
                "Iruma cooler",
                "08xxxxxxxxxx",
                "Mar 03, 2022"
            ),
            Contact(
                R.drawable.kira,
                "Kira Plot armor",
                "08xxxxxxxxxx",
                "Oct 22, 2021"
            ),
            Contact(
                R.drawable.kira,
                "Lord Kira Yamato",
                "08xxxxxxxxxx",
                "Mar 10, 2022"
            )
        )
        val recyclerView = findViewById<RecyclerView>(R.id.rv_contact)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ContactAdapter(this, contactList) {
            val intent = Intent(this, ViewContact::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
    }
}