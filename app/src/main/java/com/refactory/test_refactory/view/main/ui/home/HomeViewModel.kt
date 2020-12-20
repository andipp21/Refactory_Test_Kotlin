package com.refactory.test_refactory.view.main.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.refactory.test_refactory.model.AsSeenOn
import com.refactory.test_refactory.model.Partner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import java.net.URL


class HomeViewModel : ViewModel() {

    private val _carouselItem = MutableLiveData<List<CarouselItem>>().apply {
        GlobalScope.launch {
            val list = mutableListOf<CarouselItem>()

            val apiResponse = URL("https://raw.githubusercontent.com/cahyo-refactory/RSP-DataSet-SkilTest-FE/main/partner.json").readText()

            val partner = Gson().fromJson<Partner>(apiResponse, Partner::class.java)

            partner.data.forEach {
                list.add(
                    CarouselItem(
                        imageUrl = it.photoUrl,
                        caption = it.name
                    )
                )
            }

            postValue(list)
        }
    }
    val carouselItem: LiveData<List<CarouselItem>> = _carouselItem

    private val _asSeenItem = MutableLiveData<List<String>>().apply{
        GlobalScope.launch {
            val list = mutableListOf<String>()

            val apiResponse = URL("https://raw.githubusercontent.com/cahyo-refactory/RSP-DataSet-SkilTest-FE/main/seen_on.json").readText()

            val asSeen = Gson().fromJson<AsSeenOn>(apiResponse, AsSeenOn::class.java)

            asSeen.data.forEach {
                list.add(it.photoUrl)
            }

            postValue(list)
        }
    }

    val asSeenItem: LiveData<List<String>> = _asSeenItem
}

