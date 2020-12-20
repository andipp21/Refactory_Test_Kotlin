package com.refactory.test_refactory.view.main.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refactory.test_refactory.R
import kotlinx.android.synthetic.main.item_as_seen.view.*

class AdapterAsSeen (private val listImage: MutableList<String> = mutableListOf()): RecyclerView.Adapter<AdapterAsSeen.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(image: String){
            Glide.with(itemView.context)
                    .load(image)
                    .into(itemView.imageAsSeen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_as_seen, parent, false)

        return ViewHolder(
                view
        )
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("image", listImage.toString())

        holder.bind(listImage[position])
    }

    fun updateList(list : List<String>){
        listImage.clear()

        listImage.addAll(list)

        notifyDataSetChanged()
    }


}