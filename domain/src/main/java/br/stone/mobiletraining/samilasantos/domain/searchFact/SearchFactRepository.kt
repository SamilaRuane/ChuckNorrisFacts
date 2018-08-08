package br.stone.mobiletraining.samilasantos.domain.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import io.reactivex.Single

interface SearchFactRepository {
    fun getFactsThatContains(query: String): Single<List<Fact>>
}