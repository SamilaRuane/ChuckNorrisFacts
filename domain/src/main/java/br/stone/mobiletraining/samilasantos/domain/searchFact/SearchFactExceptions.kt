package br.stone.mobiletraining.samilasantos.domain.searchFact

sealed class SearchFactExceptions {
    object QueryNotMatchException : SearchFactExceptions()
}