package com.refactory.test_refactory.view.main.ui.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refactory.test_refactory.R
import com.refactory.test_refactory.model.AlumniReportModel
import kotlinx.android.synthetic.main.item_review_alumni.view.*

class AdapterReview (private val listReview: MutableList<AlumniReportModel.Data> = mutableListOf()): RecyclerView.Adapter<AdapterReview.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(review: AlumniReportModel.Data){
            Glide.with(itemView.context).load(review.user.photoUrl).into(itemView.fotoAlumni)

            itemView.namaAlumni.text = review.user.name

            itemView.reviewFrom.text = review.user.from

            itemView.rating.rating = review.star.toFloat()

            itemView.reviewTitle.text = review.title

            itemView.reviewContent.text = review.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review_alumni, parent, false)

        return ViewHolder(
                view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listReview[position])
    }

    override fun getItemCount(): Int {
        return listReview.size
    }

    fun updateList(newList: List<AlumniReportModel.Data>){
        listReview.clear()
        listReview.addAll(newList)
        notifyDataSetChanged()
    }
}