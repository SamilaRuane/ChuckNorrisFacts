package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HandleConnectionErrors<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext(this::handleIfNetworkError)
    }

    private fun handleIfNetworkError(error: Throwable): Single<T> =
        if (isNetworkError(error)) asNetworkError(error)
        else Single.error(error)

    private fun asNetworkError(error: Throwable) = Single.error<T>(mapToDomainError(error))

    private fun mapToDomainError(error: Throwable): NetworkIssues {
        if (isConnectionTimeout(error)) return NetworkIssues.Timeout
        if (noInternetAvailable(error)) return NetworkIssues.NoNetwork
        return NetworkIssues.NoNetwork
    }

    private fun isNetworkError(error: Throwable) =
        isConnectionTimeout(error) || noInternetAvailable(error)

    private fun isConnectionTimeout(error: Throwable) = error is SocketTimeoutException

    private fun noInternetAvailable(error: Throwable) = error is UnknownHostException
}