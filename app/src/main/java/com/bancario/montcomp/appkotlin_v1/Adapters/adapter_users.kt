package com.bancario.montcomp.appkotlin_v1.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bancario.montcomp.appkotlin_v1.R
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_users.view.*

class RecyclerAdapter(private val data: ArrayList<User>) :  RecyclerView.Adapter<RecyclerAdapter.FeedHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.FeedHolder {
        val inflatedView = parent.inflate(R.layout.list_users, false)
        return FeedHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.FeedHolder, position: Int) {
        val feed : User = this.data[position]

        holder.itemView.tv_name.text = feed.name
        holder.itemView.tv_dni.text = feed.dni
        holder.itemView.tv_address.text = feed.address

        //GLIDE O PICASSO PARA IMAGEVIEW
        Picasso.get().load(feed.image).resize(100,100).centerCrop().into(holder.itemView.iv_prueba)
        //Glide.with(holder.itemView.context).load(feed.image).centerCrop().into(holder.itemView.iv_prueba)//si funciona
}


    override fun getItemCount(): Int {
        return data.size
    }

    class FeedHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var feed : User? = null

        init {
            v.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }

    }
}






