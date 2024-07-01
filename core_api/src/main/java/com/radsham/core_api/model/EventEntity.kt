package com.radsham.core_api.model

data class EventEntity(
    val id: String = "",
    var imageUri: String = "",
    val name: String = "",
    val location: String = "",
    val category: String = "",
    val description: String = "",
    val contacts: String = "",
    val participants: String = "",
    var uid: String = ""
)