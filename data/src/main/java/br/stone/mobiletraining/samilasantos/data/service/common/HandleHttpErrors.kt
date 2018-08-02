package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.RandomFactExceptions
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.HttpException

class HandleHttpErrors<T> : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext(this::handleIfRestError)
    }

    private fun handleIfRestError(incoming: Throwable): Single<T> =
        if (incoming is HttpException) toInfrastructureError(incoming)
        else Single.error(incoming)


    private fun toInfrastructureError(restError: HttpException): Single<T> {
        val infraError = mapErrorWith(restError.code())
        return Single.error(infraError)
    }

    private fun mapErrorWith(code: Int) = when (code) {
        404 -> RandomFactExceptions.FactNotFound
        in 400..499 -> RandomFactExceptions.FactNotFound
        in 500..511 -> IntegrationExceptions.UnavailableProvider
        else -> RandomFactExceptions.FactNotFound
    }
}