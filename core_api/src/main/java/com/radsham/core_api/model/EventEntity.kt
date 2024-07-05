package com.radsham.core_api.model

data class EventEntity(
    val id: String = "",
    var imageUri: String = "",
    val name: String = "",
    val location: String = "",
    val category: MutableMap<String, String> = mutableMapOf(),
    val description: String = "",
    val contacts: String = "",
    val participants: MutableMap<String, String> = mutableMapOf(),
    var uid: String = "",
)