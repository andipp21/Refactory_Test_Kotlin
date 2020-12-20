package com.refactory.test_refactory.view.main.ui.list_course

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import com.refactory.test_refactory.R
import com.refactory.test_refactory.databinding.ListCourseFragmentBinding

class ListCourseFragment : Fragment() {

    companion object {
        fun newInstance() = ListCourseFragment()
    }

    private lateinit var viewModel: ListCourseViewModel
    private lateinit var binding: ListCourseFragmentBinding
    private lateinit var adapter: AdapterListCourse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListCourseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = AdapterListCourse()
        viewModel = ViewModelProvider(this).get(ListCourseViewModel::class.java)

        binding.rvCourse.adapter = adapter
        viewModel.listCourse.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        val spinner: Spinner = binding.dropdownList
// Create an ArrayAdapter using the string array and a default spinner layout
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.list_course_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }


    }

}