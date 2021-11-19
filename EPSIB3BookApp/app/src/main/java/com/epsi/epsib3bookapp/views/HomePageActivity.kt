package com.epsi.epsib3bookapp.views

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epsi.epsib3bookapp.R
import com.epsi.epsib3bookapp.extensions.Extensions.toast
import com.epsi.epsib3bookapp.utils.BookAdapter
import com.epsi.epsib3bookapp.utils.BookAdapter.BookModel
import com.epsi.epsib3bookapp.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlin.reflect.typeOf

class HomePageActivity : AppCompatActivity() {

    lateinit var bookList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //afficher l id de l utilisateur
        //userid.text = intent.getStringExtra("userid")
        userid.text = "Your id: ${firebaseAuth.currentUser?.uid}" ?: "Error"
        intent.getStringExtra("userid")?.let { Log.d("User ID", it) }
        //

        // RecyclerView

        val  books = CreerMesLigne()
        val booklist = ArrayList<BookModel>()

        val db = Firebase.firestore

        //Nom de la collection à laquelle on se connecte / collection
        db.collection("Books")
            .get()
            .addOnSuccessListener { result ->
                for (book in result) {

                    var bookToAdd = BookModel(book.id,
                        book.data["title"].toString(),
                        book.data["author"].toString(),
                        book.data["description"].toString(),
                        book.data["image"].toString())

                    // Si le livre n'est pas dans la donnée du user, on l'ajoute avec un booléen false de base

                    //
                    booklist.add(bookToAdd)

                    Log.d(TAG, "${book.id} => ")
                Log.d(TAG, "${booklist.size}")

                }

            }
            .addOnFailureListener { exception ->
                toast("Make sure you have access to the internet")
                Log.w(TAG, "Error getting documents.", exception)
            }

        bookList = findViewById(R.id.bookList)
        bookList.layoutManager = LinearLayoutManager(this)
        bookList.adapter = BookAdapter(books.toTypedArray()) // pas BookModel
        //Fonction lambda

        {
            toast("You have selected ${it.title}")
            val intent = Intent(this@HomePageActivity, ItemDetailActivity::class.java)
            intent.putExtra("title", it.title)
            intent.putExtra("author", it.author)
            intent.putExtra("description", it.description)
            intent.putExtra("image", it.image)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }

        // RecyclerView

        imgbtnSignOut.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            toast("You are disconnected")
            finish()
        }
    }


    fun CreerMesLigne() :ArrayList<BookModel>
    {
        val books = ArrayList<BookModel>()
        books.add(BookModel("1","Fear Island","Teriann Fayers","Une description", "bookexample.jpg"))
        books.add(BookModel("2","Story of Me, The (O contador de histórias)","Chrissy Muehle","Une description", "bookexample.jpg"))
        books.add(BookModel("3","Slaves of New York","Lacey Fairchild","Une description", "bookexample.jpg"))
        books.add(BookModel("4","Dillinger","Elisabet Simeonov","Une description", "bookexample.jpg"))
        books.add(BookModel("5","Pawn","Madelina Downs","Une description", "bookexample.jpg"))
        books.add(BookModel("6","Fever Pitch","Arda Kernar","Une description ","bookexample.jpg"))
        books.add(BookModel("7","Lisztomania","Wylma Danilov","Une description", "bookexample.jpg"))
        books.add(BookModel("8","Seeking Asian Female","Tobie Croughan","Une description", "bookexample.jpg"))
        books.add(BookModel("9","Cesar Chavez","Uta Ruprich","Une description", "bookexample.jpg"))

        return books
    }
}