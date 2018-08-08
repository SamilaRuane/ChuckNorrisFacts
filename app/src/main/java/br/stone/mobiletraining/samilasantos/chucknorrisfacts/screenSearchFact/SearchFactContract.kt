package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.CategoryBackgroundColor
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.FactDescriptionFontSize

class SearchFactContract {
    data class Item(
        val id: String = "",
        val description: String = "",
        val url: String = "",
        val category: String = "",
        val categoryBgColor: CategoryBackgroundColor = CategoryBackgroundColor.GRAY,
        val fontSize: FactDescriptionFontSize = FactDescriptionFontSize.REGULAR
    )

    sealed class ViewState {
        data class Success(val facts: List<Item>) : ViewState()
        object Loading : ViewState()
        object NoMatchQuery : ViewState()
        object UnavailableProvider : ViewState()
        object NoNetworkIssue : ViewState()
        object Timeout : ViewState()
        object UnexpectedData : ViewState()
        object MalformedQuery : ViewState()
    }
}