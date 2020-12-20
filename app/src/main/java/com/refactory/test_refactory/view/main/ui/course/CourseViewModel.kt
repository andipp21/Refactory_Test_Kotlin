package com.refactory.test_refactory.view.main.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.refactory.test_refactory.model.AlumniReportModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class CourseViewModel : ViewModel() {

    private val _alumniReport = MutableLiveData<List<AlumniReportModel.Data>>().apply {
        GlobalScope.launch {
            val list = mutableListOf<AlumniReportModel.Data>()
            val apiResponse = URL("https://raw.githubusercontent.com/cahyo-refactory/RSP-DataSet-SkilTest-FE/main/alumni-report.json").readText()
            val report = Gson().fromJson<AlumniReportModel>(apiResponse, AlumniReportModel::class.java)

            report.data.forEach{
                list.add(it)
            }

            postValue(list)
        }
    }

    val alumniReport: LiveData<List<AlumniReportModel.Data>> = _alumniReport
}