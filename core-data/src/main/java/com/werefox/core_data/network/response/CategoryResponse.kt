package com.werefox.core_data.network.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
)