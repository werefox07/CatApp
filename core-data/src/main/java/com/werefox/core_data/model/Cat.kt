package com.werefox.core_data.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("breeds")
    val breeds: List<Breed>?,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)
