package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

class ProcessCategoryBgColor {
    fun using(category: String): Color =
        if (category == "Uncategorized") Color.GRAY else Color.BLUE
}

enum class Color {
    BLUE,
    GRAY
}