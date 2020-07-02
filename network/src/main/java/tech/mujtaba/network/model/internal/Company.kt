package tech.mujtaba.network.model.internal


import com.google.gson.annotations.SerializedName

internal data class Company(
    @SerializedName("bs")
    val bs: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val name: String
)