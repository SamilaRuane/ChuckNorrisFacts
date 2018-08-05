package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.RandomFactExceptions
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single

object RandomFactMother {
    val repository: RandomFactRepository = mock()

    fun withASuccessScenario(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(
                Single.just(Fact(id = "AFEG7878DD",
                    description = "Chuck Norris is Extreme Go Horse Father",
                    url = "", category = "Dev")
                ))
        func()
    }

    fun withANoNetworkError(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(NetworkIssues.NoNetwork))
        func()
    }

    fun withATimeoutError(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(NetworkIssues.Timeout))
        func()
    }

    fun withAFactNotFoundError(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(RandomFactExceptions.FactNotFound))
        func()
    }

    fun withAUnavailableProviderError(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.UnavailableProvider))
        func()
    }

    fun withAUnexpectedDataError(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.UnexpectedData))
        func()
    }
}