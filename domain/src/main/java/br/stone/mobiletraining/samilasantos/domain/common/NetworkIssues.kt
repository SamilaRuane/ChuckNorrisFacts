package br.stone.mobiletraining.samilasantos.domain.common

sealed class NetworkIssues : Throwable() {
    object NoNetwork : NetworkIssues()
    object Timeout : NetworkIssues()
}