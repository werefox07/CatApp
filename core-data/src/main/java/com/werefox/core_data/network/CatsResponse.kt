package com.werefox.core_data.network

import com.google.gson.annotations.SerializedName
import com.werefox.core_data.model.Cat


data class CatsResponse(
    @SerializedName("cats")
    private val cats: List<Cat>? = null
)