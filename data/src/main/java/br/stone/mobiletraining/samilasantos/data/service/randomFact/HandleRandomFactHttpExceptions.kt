package br.stone.mobiletraining.samilasantos.data.service.randomFact

import br.stone.mobiletraining.samilasantos.data.service.common.HttpExceptionsHandler
import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions

class HandleRandomFactHttpExceptions : HttpExceptionsHandler<Fact>() {

    override fun mapErrorWith(errorCode: Int) = when (errorCode) {
        404 -> IntegrationExceptions.InfoNotFound
        in 500..511 -> IntegrationExceptions.UnavailableProvider
        else -> IntegrationExceptions.InfoNotFound
    }
}