package br.stone.mobiletraining.samilasantos.data.service.common

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class HandleParsingExceptions<T>(val mapper: (Throwable) -> Throwable) : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext(this::handleIfParsingError)
    }

    private fun handleIfParsingError(error: Throwable): Single<T> = Single.error(mapper(error))
}
