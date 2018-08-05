package br.stone.mobiletraining.samilasantos.domain.randomFact.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactExceptions
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
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

    fun withANoNetworkException(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(NetworkIssues.NoNetwork))
        func()
    }

    fun withATimeoutException(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(NetworkIssues.Timeout))
        func()
    }

    fun withAFactNotFoundException(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(RandomFactExceptions.FactNotFound))
        func()
    }

    fun withAUnavailableProviderException(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.UnavailableProvider))
        func()
    }

    fun withAUnexpectedDataException(func: () -> Unit) {
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.UnexpectedData))
        func()
    }
}