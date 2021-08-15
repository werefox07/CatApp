package com.werefox.core_data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: String,
)