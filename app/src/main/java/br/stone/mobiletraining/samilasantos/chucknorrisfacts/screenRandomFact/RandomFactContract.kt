package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

class RandomFactContract {

    sealed class ViewState {
        data class Success(val fact: String) : ViewState()
        data class Error(val error: Throwable) : ViewState()
        object Loading : ViewState()
    }
}