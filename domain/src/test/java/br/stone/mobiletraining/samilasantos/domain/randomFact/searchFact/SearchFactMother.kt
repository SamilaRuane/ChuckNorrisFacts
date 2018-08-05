package br.stone.mobiletraining.samilasantos.domain.randomFact.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactExceptions
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single

object SearchFactMother {
    val repository: SearchFactRepository = mock()
    private val list = arrayListOf(
        Fact(
            id = "AFEG7878DD",
            description = "Chuck Norris is Extreme Go Horse Father",
            url = "", category = "Dev"
        ),
        Fact(
            id = "A565KDJSKJKDS878DD",
            description = "Chuck Norris is Extreme Go Horse Father",
            url = "", category = "Dev"
        )
    )

    fun withASuccessScenario(func: SearchFactMother.() -> Unit) {
        whenever(repository.getFactsThatContains("dev"))
            .thenReturn(
                Single.just(list)
            )
        func()
    }

    fun withANoNetworkException(func: SearchFactMother.() -> Unit) {
        whenever(repository.getFactsThatContains("dev"))
            .thenReturn(Single.error(NetworkIssues.NoNetwork))
        func()
    }

    fun withATimeoutException(func: SearchFactMother.() -> Unit) {
        whenever(repository.getFactsThatContains("dev"))
            .thenReturn(Single.error(NetworkIssues.Timeout))
        func()
    }

    fun withAQueryNotMatchException(func: SearchFactMother.() -> Unit) {
        whenever(repository.getFactsThatContains("dev"))
            .thenReturn(Single.error(SearchFactExceptions.QueryNotMatchException))
        func()
    }

    fun withAUnavailableProviderException(func: SearchFactMother.() -> Unit) {
        whenever(repository.getFactsThatContains("dev"))
            .thenReturn(Single.error(IntegrationExceptions.UnavailableProvider))
        func()
    }

    fun withAUnexpectedDataException(func: SearchFactMother.() -> Unit) {
        whenever(repository.getFactsThatContains("dev"))
            .thenReturn(Single.error(IntegrationExceptions.UnexpectedData))
        func()
    }
}
