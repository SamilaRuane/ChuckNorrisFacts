package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

class ProcessCategoryBgColor {
    private val defaultCategory = "Uncategorized"
    fun using(category: String): CategoryBackgroundColor =
        if (category == defaultCategory) CategoryBackgroundColor.GRAY else CategoryBackgroundColor.BLUE
}

enum class CategoryBackgroundColor {
    BLUE,
    GRAY
}