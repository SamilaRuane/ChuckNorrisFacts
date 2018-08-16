package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

class CalculateFactDescriptionFontSize {
    private val maxLengthToRegularFontSize = 50
    fun using(descriptionSize: Int): FactDescriptionFontSize =
        if (descriptionSize > maxLengthToRegularFontSize) FactDescriptionFontSize.REGULAR else FactDescriptionFontSize.LARGER
}

enum class FactDescriptionFontSize {
    LARGER,
    REGULAR
}