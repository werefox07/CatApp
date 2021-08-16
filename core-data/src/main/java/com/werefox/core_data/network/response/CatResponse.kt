package com.werefox.core_data.network.response

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("breeds")
    val breedResponses: List<BreedResponse>?,
    @SerializedName("categories")
    val categoryResponses: List<CategoryResponse>?,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)
