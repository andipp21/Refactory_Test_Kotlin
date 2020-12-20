package com.refactory.test_refactory.view.main.ui.course

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.refactory.test_refactory.R
import com.refactory.test_refactory.databinding.CourseFragmentBinding

class CourseFragment : Fragment() {

    companion object {
        fun newInstance() = CourseFragment()
    }

    private lateinit var viewModel: CourseViewModel
    private lateinit var binding: CourseFragmentBinding
    private lateinit var adapter: AdapterReview

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = CourseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
        adapter = AdapterReview()

        Glide.with(this).load("https://i0.wp.com/refactory.id/wp-content/uploads/2020/07/Frame.png?fit=839%2C481&ssl=1").into(binding.ivC1)

        Glide.with(this).load("https://i1.wp.com/refactory.id/wp-content/uploads/2020/01/IMG_1152-1.jpg?fit=690%2C800&ssl=1").into(binding.ivC2)

        Glide.with(this).load("https://i0.wp.com/refactory.id/wp-content/uploads/2020/07/Frame-3-1.png?fit=1024%2C199&ssl=1").into(binding.ivLangkah)

        binding.rvReview.adapter = adapter
        viewModel.alumniReport.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        binding.btnBelajar.setOnClickListener {

        }

    }

}