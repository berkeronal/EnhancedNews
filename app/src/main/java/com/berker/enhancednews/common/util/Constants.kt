package com.berker.enhancednews.common.util

object Constants {
    const val COUNTRY = "ca"
    const val API_KEY = "73b3dab6d7064f30afc8b23686c796a8"
    const val PAGE_SIZE = "20"
    const val DB_NAME = "ENHANCED_NEWS_DATABASE"
    const val DB_VERSION = 2

    const val MAIN_CATEGORY = "sports"

    enum class Categories(val value: String) {
        BUSINESS("business"),
        SPORTS("sports"),
        HEALTH("health"),
        TECHNOLOGY("technology"),
    }

}