package tech.mujtaba.network.model.internal


import com.google.gson.annotations.SerializedName

internal data class Geo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)