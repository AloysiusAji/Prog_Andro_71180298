package com.example.listcontact

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_contact)

        val contact = intent.getParcelableExtra<Contact>(MainActivity.INTENT_PARCELABLE)

        val imgContact = findViewById<ImageView>(R.id.img_item_photo)
        val nameContact = findViewById<TextView>(R.id.item_name)
        val detailContact = findViewById<TextView>(R.id.item_description)
        val callHistory = findViewById<TextView>(R.id.callH)

        imgContact.setImageResource(contact?.imgContact!!)
        nameContact.text = contact.nameContact
        detailContact.text = contact.detailContact
        callHistory.text = contact.callHistory
    }
}