package com.refactory.test_refactory.view.main.ui.course_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refactory.test_refactory.R
import com.refactory.test_refactory.model.DetailCourse
import kotlinx.android.synthetic.main.item_materi_detail.view.*

class AdapterDetailMateri(val listMateri: MutableList<DetailCourse.MateriCourse.Data> = mutableListOf()) :
    RecyclerView.Adapter<AdapterDetailMateri.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DetailCourse.MateriCourse.Data) {
            itemView.content.text = "${data.title} (${data.timeIn})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_materi_detail, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMateri[position])
    }

    override fun getItemCount(): Int {
        return listMateri.size
    }

    fun updateList(newList: List<DetailCourse.MateriCourse.Data>) {
        listMateri.clear()
        listMateri.addAll(newList)
        notifyDataSetChanged()
    }
}