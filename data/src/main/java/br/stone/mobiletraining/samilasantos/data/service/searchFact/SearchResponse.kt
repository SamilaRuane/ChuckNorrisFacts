package br.stone.mobiletraining.samilasantos.data.service.searchFact

import br.stone.mobiletraining.samilasantos.data.service.common.Fact

internal data class SearchResponse(
    val total: Int,
    val result: List<Fact>
)