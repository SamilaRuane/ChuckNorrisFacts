package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import io.reactivex.Observable

interface RandomFactRepository {
    fun getFact(): Observable<Fact>
}