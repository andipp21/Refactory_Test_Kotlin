package com.refactory.test_refactory.view.main.ui.list_course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.refactory.test_refactory.model.AlumniReportModel
import com.refactory.test_refactory.model.Course
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URI
import java.net.URL

class ListCourseViewModel : ViewModel() {
    private val _listCourse = MutableLiveData<List<Course.Data>>().apply {
        GlobalScope.launch {
            val list = mutableListOf<Course.Data>()
            val apiResponse = URL("https://raw.githubusercontent.com/cahyo-refactory/RSP-DataSet-SkilTest-FE/main/list-course.json").readText()
            val report = Gson().fromJson<Course>(apiResponse, Course::class.java)

            report.data.forEach{
                list.add(it)
            }

            postValue(list)
        }
    }

    val listCourse: LiveData<List<Course.Data>> = _listCourse
}