package br.stone.mobiletraining.samilasantos.domain.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact

sealed class GetFactByQueryResult {
    data class Success(val facts: List<Fact>) : GetFactByQueryResult()
    data class Error(val error: Throwable) : GetFactByQueryResult()
}