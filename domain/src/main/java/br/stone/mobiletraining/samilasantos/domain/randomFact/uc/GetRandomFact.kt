package br.stone.mobiletraining.samilasantos.domain.randomFact.uc

import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactResult
import io.reactivex.Observable

class GetRandomFact(private val repository: RandomFactRepository) {
    fun execute(): Observable<RandomFactResult> = repository.getFact()
        .map { RandomFactResult.Success(it) as RandomFactResult }
        .onErrorReturn { RandomFactResult.Error(it) }
}