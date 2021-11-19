package com.epsi.epsib3bookapp.views

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.epsi.epsib3bookapp.R
import com.epsi.epsib3bookapp.utils.FirebaseUtils.firebaseAuth
import com.epsi.epsib3bookapp.utils.BookAdapter
import com.epsi.epsib3bookapp.utils.FirebaseUtils
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        itemTitle.text = intent.getStringExtra("title")
        itemAuthor.text = intent.getStringExtra("author")
        itemDescription.text = intent.getStringExtra("author")
        itemImage.text = intent.getStringExtra("image")



        // Quand on clique sur le livre, la base de donnée recoit qu'on l'a lu
        val db = Firebase.firestore

        val bookRead = hashMapOf(
            "${intent.getStringExtra("id")}" to true
        )

        db.collection("Users").document("${FirebaseUtils.firebaseAuth.currentUser?.uid}")
            .update(bookRead as Map<String, Any>)
        //

        // Si un livre à été lu, sa couleur devient verte


    }

}