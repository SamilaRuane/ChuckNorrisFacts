package br.stone.mobiletraining.samilasantos.data.service.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactExceptions
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class HandleEmptyFactsListResult : SingleTransformer<List<Fact>, List<Fact>> {
    override fun apply(upstream: Single<List<Fact>>): SingleSource<List<Fact>> {
        return upstream.flatMap { handleIfIsEmpty(it) }
    }

    private fun handleIfIsEmpty(incoming: List<Fact>): Single<List<Fact>> =
        if (incoming.isEmpty()) Single.error<List<Fact>>(SearchFactExceptions.QueryNotMatchException)
        else Single.just(incoming)
}