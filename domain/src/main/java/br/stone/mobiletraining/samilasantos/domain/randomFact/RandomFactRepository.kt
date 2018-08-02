package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import io.reactivex.Single

interface RandomFactRepository {
    fun getFact(): Single<Fact>
}