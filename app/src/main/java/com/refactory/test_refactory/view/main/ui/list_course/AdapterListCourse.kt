package com.refactory.test_refactory.view.main.ui.list_course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refactory.test_refactory.R
import com.refactory.test_refactory.model.Course
import kotlinx.android.synthetic.main.item_course.view.*

class AdapterListCourse(val listCourse: MutableList<Course.Data> = mutableListOf()) :
    RecyclerView.Adapter<AdapterListCourse.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(course: Course.Data){
            Glide.with(itemView.context).load(course.photoUrl).into(itemView.courseImage)

            itemView.courseTitle.text = course.title
            itemView.courseDescription.text = course.shortDescription

            Glide.with(itemView.context).load(course.user.photoUrl).circleCrop().into(itemView.mentorImage)
            itemView.mentorName.text = course.user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCourse[position])
    }

    override fun getItemCount(): Int {
        return listCourse.size-1
    }

    fun updateList(newList: List<Course.Data>){
        listCourse.clear()
        listCourse.addAll(newList)
        notifyDataSetChanged()
    }
}