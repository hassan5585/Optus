package tech.mujtaba.network.model.internal


import com.google.gson.annotations.SerializedName

internal data class Address(
        @SerializedName("city")
    val city: String,
        @SerializedName("geo")
    val geo: Geo,
        @SerializedName("street")
    val street: String,
        @SerializedName("suite")
    val suite: String,
        @SerializedName("zipcode")
    val zipcode: String
)