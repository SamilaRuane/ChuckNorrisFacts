package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

import br.stone.mobiletraining.samilasantos.domain.searchFact.GetFactByQueryResult
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import io.reactivex.Single

class GetFactByQuery(private val repository: SearchFactRepository) {
    fun execute(query: String): Single<GetFactByQueryResult> =
        repository.getFactsThatContains(query)
            .map { GetFactByQueryResult.Success(it) as GetFactByQueryResult }
            .onErrorReturn { GetFactByQueryResult.Error(it) as GetFactByQueryResult }
}