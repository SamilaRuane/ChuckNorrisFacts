package br.stone.mobiletraining.samilasantos.domain.common

sealed class IntegrationExceptions : Throwable() {
    object UnavailableProvider : IntegrationExceptions()
    object UnexpectedData : IntegrationExceptions()
}