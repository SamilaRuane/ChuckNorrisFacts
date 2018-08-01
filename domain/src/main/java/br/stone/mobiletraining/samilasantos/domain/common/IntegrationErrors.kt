package br.stone.mobiletraining.samilasantos.domain.common

sealed class IntegrationErrors : Throwable() {
    object FactNotFound : IntegrationErrors()
    object UnavailableProvider : IntegrationErrors()
    object UnexpectedData : IntegrationErrors()
}