package com.refactory.test_refactory.model


import com.google.gson.annotations.SerializedName

data class AsSeenOn(
        @SerializedName("data")
        var `data`: List<Data>
) {
    data class Data(
            @SerializedName("id")
            var id: Int,
            @SerializedName("link_url")
            var linkUrl: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("photo_url")
            var photoUrl: String
    )
}