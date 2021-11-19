package com.epsi.epsib3bookapp.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epsi.epsib3bookapp.R
import kotlinx.android.synthetic.main.item_book.view.*


class BookAdapter(val BookAfficher : Array<BookModel>,val listener: (BookModel) -> Unit)
    : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent, false) // item_book : chaque item de la recycler view
        return ViewHolder(ma_ligne)
    }

    override fun getItemCount(): Int = BookAfficher.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("XXX","onBindViewHolder")
        holder.bind(BookAfficher[position],listener)
    }
//
    fun setBookList(booklist : Array<BookModel>) {

    }
//
    class ViewHolder(val elementDeListe : View) : RecyclerView.ViewHolder(elementDeListe)
    {
        fun bind(book: BookModel, listener: (BookModel) -> Unit) = with(itemView)
        {
            Log.i("XXX","bind")

            itemView.tvTitle.text = book.title
            itemView.tvAuthor.text = book.author
            // itemView.tvDescription.text = book.description
            itemView.tvImage.text = book.image

            setOnClickListener { listener(book) }
        }
    }

    data class BookModel (val id: String, val title: String, val author: String, val description: String, val image: String) {

    }


    }

