package com.refactory.test_refactory.model


import com.google.gson.annotations.SerializedName

data class AlumniReportModel(
    @SerializedName("data")
    var `data`: List<Data>
) {
    data class Data(
        @SerializedName("description")
        var description: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("star")
        var star: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("user")
        var user: User
    ) {
        data class User(
            @SerializedName("from")
            var from: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("photo_url")
            var photoUrl: String,
            @SerializedName("user_id")
            var userId: Int
        )
    }
}