package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ChuckNorrisService(private val api: ChuckNorrisApi) : RandomFactRepository {

    override fun getFact(): Single<Fact> = api.getRandomFact()
        .subscribeOn(Schedulers.io())
        .compose(HandleConnectionErrors())
        .compose(HandleParsingErrors())
        .compose(HandleHttpErrors())
        .map { fact -> Fact(fact.id, fact.value, fact.url, fact.category?.first() ?: "Uncategorized") }
}