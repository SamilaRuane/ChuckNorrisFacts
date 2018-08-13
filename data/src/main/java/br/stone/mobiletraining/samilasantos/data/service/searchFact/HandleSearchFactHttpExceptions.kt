package br.stone.mobiletraining.samilasantos.data.service.searchFact

import br.stone.mobiletraining.samilasantos.data.service.common.HttpExceptionsHandler
import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactExceptions

class HandleSearchFactHttpExceptions : HttpExceptionsHandler<List<Fact>>() {

    override fun mapErrorWith(code: Int) = when (code) {
        404 -> IntegrationExceptions.InfoNotFound
        400 -> SearchFactExceptions.MalFormedQuery
        in 500..511 -> IntegrationExceptions.UnavailableProvider
        else -> IntegrationExceptions.InfoNotFound
    }
}