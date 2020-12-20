package com.refactory.test_refactory.view.main.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.refactory.test_refactory.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: AdapterAsSeen

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = AdapterAsSeen()

        val carousel: ImageCarousel = binding.imgCarousel

        homeViewModel.carouselItem.observe(viewLifecycleOwner, Observer {
            carousel.addData(it)
        })

        homeViewModel.asSeenItem.observe(viewLifecycleOwner, Observer {
            Log.d("String List", it.toString())

            adapter.updateList(it)
        })

        binding.rvAsSeen.adapter = adapter

        Glide.with(this)
                .load("https://i1.wp.com/refactory.id/wp-content/uploads/2020/01/material_approval.png?fit=50%2C48&ssl=1")
                .into(binding.ivC1)

        Glide.with(this)
                .load("https://i2.wp.com/refactory.id/wp-content/uploads/2020/01/material_bolt.png?fit=28%2C48&ssl=1")
                .into(binding.ivC2)


        return binding.root
    }
}