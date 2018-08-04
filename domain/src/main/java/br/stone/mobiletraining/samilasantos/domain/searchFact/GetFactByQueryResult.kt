package br.stone.mobiletraining.samilasantos.domain.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact

sealed class GetFactByQueryResult {
    data class Success(val facts: List<Fact>)
    data class Error(val error: Throwable)
}