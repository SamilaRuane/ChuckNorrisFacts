package br.stone.mobiletraining.samilasantos.data.service.common

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.adapter.rxjava2.HttpException

abstract class HttpExceptionsHandler<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T> =
        upstream.onErrorResumeNext(::handleIfRestError)

    private fun handleIfRestError(incoming: Throwable): Single<T> =
        if (incoming is HttpException) handleInfrastructureError(incoming)
        else Single.error(incoming)

    private fun handleInfrastructureError(error: HttpException): Single<T> {
        val infraError = mapErrorWith(error.code())
        return Single.error(infraError)
    }

    abstract fun mapErrorWith(errorCode: Int): Throwable
}