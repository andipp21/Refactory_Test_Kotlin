package com.refactory.test_refactory.model


import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("data")
    var `data`: List<Data>
) {
    data class Data(
        @SerializedName("id")
        var id: Int,
        @SerializedName("link_url")
        var linkUrl: String,
        @SerializedName("photo_url")
        var photoUrl: String,
        @SerializedName("short_description")
        var shortDescription: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("user")
        var user: User
    ) {
        data class User(
            @SerializedName("name")
            var name: String,
            @SerializedName("photo_url")
            var photoUrl: String,
            @SerializedName("user_id")
            var userId: Int
        )
    }
}