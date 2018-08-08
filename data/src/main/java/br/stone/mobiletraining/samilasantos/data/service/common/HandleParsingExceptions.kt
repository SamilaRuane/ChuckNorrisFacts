package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class HandleParsingExceptions<T> : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext(this::handleIfParsingError)
    }

    private fun handleIfParsingError(error: Throwable): Single<T> {
        val mapped = when (error) {
            is IllegalArgumentException -> IntegrationExceptions.UnexpectedData
            is JsonSyntaxException -> IntegrationExceptions.UnexpectedData
            is JsonParseException -> IntegrationExceptions.UnexpectedData
            is JsonIOException -> IntegrationExceptions.UnexpectedData
            is MalformedJsonException -> IntegrationExceptions.UnexpectedData
            else -> error
        }

        return Single.error(mapped)
    }
}