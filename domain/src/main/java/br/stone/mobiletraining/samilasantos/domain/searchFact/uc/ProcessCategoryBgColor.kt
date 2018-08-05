package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

import io.reactivex.Single

const val UNCATEGORIZED = "Uncategorized"

class ProcessCategoryBgColor {
    fun using(category: String): Single<String> =
        if (category == UNCATEGORIZED) Single.just(Color.GRAY.hex) else Single.just(Color.BLUE.hex)
}

enum class Color(val hex: String) {
    BLUE("#FF0099CC"),
    GRAY("#33CCCCCC")
}