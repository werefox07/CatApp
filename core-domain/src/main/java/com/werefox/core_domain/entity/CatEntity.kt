package com.werefox.core_domain.entity

data class CatEntity(
    val breeds: List<String>?,
    val categories: List<String>?,
    val id: String,
    val url: String
)