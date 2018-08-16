package br.stone.mobiletraining.samilasantos.domain.common

sealed class IntegrationExceptions : Throwable() {
    object InfoNotFound : IntegrationExceptions()
    object UnavailableProvider : IntegrationExceptions()
    object UnexpectedData : IntegrationExceptions()
}