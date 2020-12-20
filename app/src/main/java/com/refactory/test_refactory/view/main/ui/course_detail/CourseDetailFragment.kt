package com.refactory.test_refactory.view.main.ui.course_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.refactory.test_refactory.R
import com.refactory.test_refactory.databinding.CourseDetailFragmentBinding

class CourseDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CourseDetailFragment()
    }

    private lateinit var viewModel: CourseDetailViewModel
    private lateinit var binding: CourseDetailFragmentBinding
    private lateinit var adapter: AdapterDetailCourse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CourseDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseDetailViewModel::class.java)
        adapter = AdapterDetailCourse()

        binding.rvMateriCourse.adapter = adapter

        viewModel.courseDetail.observe(viewLifecycleOwner, Observer {
            binding.aboutCourse.text = it.shortDescription
            adapter.updateList(it.materiCourse)

            Glide.with(this).load(it.quistionPhoto).into(binding.imageHtml)
            binding.question.text = it.quistion
            binding.answer.text = it.answer
        })
    }

}