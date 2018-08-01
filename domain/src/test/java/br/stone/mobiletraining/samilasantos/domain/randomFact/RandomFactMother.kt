package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationErrors
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import io.reactivex.Observable
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object RandomFactMother {
    val repository: RandomFactRepository = mock(RandomFactRepository::class.java)

    fun withASuccessScenario(func: () -> Unit) {
        `when`(repository.getFact())
            .thenReturn(
                Observable.just(Fact(id = "AFEG7878DD",
                    description = "Chuck Norris is Extreme Go Horse Father",
                    url = "")
                ))
        func()
    }

    fun WithANoNetworkError(func: () -> Unit) {
        `when`(repository.getFact())
            .thenReturn(Observable.error(NetworkIssues.NoNetwork))
        func()
    }

    fun WithATimeoutError(func: () -> Unit) {
        `when`(repository.getFact())
            .thenReturn(Observable.error(NetworkIssues.Timeout))
        func()
    }

    fun WithAFactNotFoundError(func: () -> Unit) {
        `when`(repository.getFact())
            .thenReturn(Observable.error(IntegrationErrors.FactNotFound))
        func()
    }

    fun WithAUnavailableProviderError(func: () -> Unit) {
        `when`(repository.getFact())
            .thenReturn(Observable.error(IntegrationErrors.UnavailableProvider))
        func()
    }

    fun WithAUnexpectedDataError(func: () -> Unit) {
        `when`(repository.getFact())
            .thenReturn(Observable.error(IntegrationErrors.UnexpectedData))
        func()
    }
}