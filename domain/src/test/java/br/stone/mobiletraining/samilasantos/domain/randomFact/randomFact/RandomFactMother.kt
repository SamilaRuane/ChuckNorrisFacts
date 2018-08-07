package br.stone.mobiletraining.samilasantos.domain.randomFact.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single

object RandomFactMother {

    fun withASuccessScenario(func: (RandomFactRepository) -> Unit) {
        val repository: RandomFactRepository = mock()
        whenever(repository.getFact())
            .thenReturn(
                Single.just(
                    Fact(
                        id = "AFEG7878DD",
                        description = "Chuck Norris is Extreme Go Horse Father",
                        url = "", category = "Dev"
                    )
                )
            )
        func(repository)
    }

    fun withANoNetworkException(func: (RandomFactRepository) -> Unit) {
        val repository: RandomFactRepository = mock()
        whenever(repository.getFact())
            .thenReturn(Single.error(NetworkIssues.NoNetwork))
        func(repository)
    }

    fun withATimeoutException(func: (RandomFactRepository) -> Unit) {
        val repository: RandomFactRepository = mock()
        whenever(repository.getFact())
            .thenReturn(Single.error(NetworkIssues.Timeout))
        func(repository)
    }

    fun withAFactNotFoundException(func: (RandomFactRepository) -> Unit) {
        val repository: RandomFactRepository = mock()
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.InfoNotFound))
        func(repository)
    }

    fun withAUnavailableProviderException(func: (RandomFactRepository) -> Unit) {
        val repository: RandomFactRepository = mock()
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.UnavailableProvider))
        func(repository)
    }

    fun withAUnexpectedDataException(func: (RandomFactRepository) -> Unit) {
        val repository: RandomFactRepository = mock()
        whenever(repository.getFact())
            .thenReturn(Single.error(IntegrationExceptions.UnexpectedData))
        func(repository)
    }
}