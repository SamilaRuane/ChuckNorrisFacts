package br.stone.mobiletraining.samilasantos.data.service.common.mappers

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import java.net.ConnectException
import java.net.UnknownHostException

object RetrofitMappers {
    val mapToNoInternetException: (Throwable) -> Boolean = { it is UnknownHostException }
    val mapToConnectionTimeout: (Throwable) -> Boolean = { it is ConnectException }
    val mapToParsingException: (Throwable) -> Throwable = { error ->
        when (error) {
            is IllegalArgumentException -> IntegrationExceptions.UnexpectedData
            is JsonSyntaxException -> IntegrationExceptions.UnexpectedData
            is JsonParseException -> IntegrationExceptions.UnexpectedData
            is JsonIOException -> IntegrationExceptions.UnexpectedData
            is MalformedJsonException -> IntegrationExceptions.UnexpectedData
            else -> error
        }
    }
}