package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

class CalculateFactDescriptionFontSize {
    fun using(descriptionSize: Int): Size =
        if (descriptionSize > 50) Size.REGULAR else Size.LARGER
}

enum class Size {
    LARGER,
    REGULAR
}