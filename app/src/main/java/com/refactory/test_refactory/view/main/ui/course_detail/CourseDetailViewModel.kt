package com.refactory.test_refactory.view.main.ui.course_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.refactory.test_refactory.model.AlumniReportModel
import com.refactory.test_refactory.model.DetailCourse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class CourseDetailViewModel : ViewModel() {
    val _courseDetail = MutableLiveData<DetailCourse>().apply {
        GlobalScope.launch {
            val apiResponse = URL("https://raw.githubusercontent.com/cahyo-refactory/RSP-DataSet-SkilTest-FE/main/detail-course.json").readText()
            val detailCourse = Gson().fromJson<DetailCourse>(apiResponse, DetailCourse::class.java)

            postValue(detailCourse)
        }
    }

    val courseDetail: LiveData<DetailCourse> = _courseDetail
}