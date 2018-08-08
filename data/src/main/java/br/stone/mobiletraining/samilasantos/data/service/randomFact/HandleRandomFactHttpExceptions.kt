package br.stone.mobiletraining.samilasantos.data.service.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.adapter.rxjava2.HttpException

class HandleRandomFactHttpExceptions : SingleTransformer<Fact, Fact> {

    override fun apply(upstream: Single<Fact>): SingleSource<Fact> {
        return upstream.onErrorResumeNext(this::handleIfRestError)
    }

    private fun handleIfRestError(incoming: Throwable): Single<Fact> =
        if (incoming is HttpException) toInfrastructureError(incoming)
        else Single.error(incoming)

    private fun toInfrastructureError(restError: HttpException): Single<Fact> {
        val infraError = mapErrorWith(restError.code())
        return Single.error(infraError)
    }

    private fun mapErrorWith(code: Int) = when (code) {
        404 -> IntegrationExceptions.InfoNotFound
        in 500..511 -> IntegrationExceptions.UnavailableProvider
        else -> IntegrationExceptions.InfoNotFound
    }
}