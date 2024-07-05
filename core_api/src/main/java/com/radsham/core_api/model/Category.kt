package com.radsham.core_api.model

import kotlin.enums.EnumEntries

enum class Categories {
    SPORT,
    MEDICINE,
    EDUCATION,
    FUN,
    MUSIC,
    TRAVEL,
}

fun <T : Enum<T>> EnumEntries<T>.names() = this.map { it.name }
