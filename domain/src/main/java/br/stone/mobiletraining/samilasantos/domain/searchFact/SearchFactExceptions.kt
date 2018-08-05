package br.stone.mobiletraining.samilasantos.domain.searchFact

sealed class SearchFactExceptions : Throwable() {
    object QueryNotMatchException : SearchFactExceptions()
    object MalFormedQuery : SearchFactExceptions()
}