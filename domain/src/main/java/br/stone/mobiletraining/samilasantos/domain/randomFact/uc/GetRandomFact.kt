package br.stone.mobiletraining.samilasantos.domain.randomFact.uc

import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactResult
import io.reactivex.Single

class GetRandomFact(private val repository: RandomFactRepository) {
    fun execute(): Single<RandomFactResult> = repository.getFact()
        .map { RandomFactResult.Success(it) as RandomFactResult }
        .onErrorReturn { RandomFactResult.Error(it) }
}