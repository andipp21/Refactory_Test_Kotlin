package com.refactory.test_refactory.view.main.ui.course_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.refactory.test_refactory.R
import com.refactory.test_refactory.model.DetailCourse
import kotlinx.android.synthetic.main.item_materi_course.view.*

class AdapterDetailCourse(val listMateri: MutableList<DetailCourse.MateriCourse> = mutableListOf()):
    RecyclerView.Adapter<AdapterDetailCourse.ViewHolder>() {
    lateinit var adapter: AdapterDetailMateri

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(materi: DetailCourse.MateriCourse){
            itemView.titleCourse.text = materi.section
            itemView.rvDetailMateri.adapter = adapter

            adapter.updateList(materi.data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_materi_course, parent, false)
        adapter = AdapterDetailMateri()
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

    fun updateList(newList: List<DetailCourse.MateriCourse>){
        listMateri.clear()
        listMateri.addAll(newList)
        notifyDataSetChanged()
    }
}