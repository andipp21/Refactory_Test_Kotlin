package com.refactory.test_refactory.model


import com.google.gson.annotations.SerializedName

data class DetailCourse(
    @SerializedName("answer")
    var answer: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("materi course")
    var materiCourse: List<MateriCourse>,
    @SerializedName("quistion")
    var quistion: String,
    @SerializedName("quistion-photo")
    var quistionPhoto: String,
    @SerializedName("short-description")
    var shortDescription: String
) {
    data class MateriCourse(
        @SerializedName("data")
        var `data`: List<Data>,
        @SerializedName("id")
        var id: Int,
        @SerializedName("section")
        var section: String
    ) {
        data class Data(
            @SerializedName("id")
            var id: Int,
            @SerializedName("time-in")
            var timeIn: String,
            @SerializedName("title")
            var title: String,
            @SerializedName("url")
            var url: String
        )
    }
}