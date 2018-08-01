package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationErrors
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactMother.repository
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import org.junit.Test

class RandomFactTest {

    @Test
    fun shouldReturnANoNetworkError() {
        RandomFactMother.WithANoNetworkError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == NetworkIssues.NoNetwork }
        }
    }

    @Test
    fun shouldReturnATimeoutError() {
        RandomFactMother.WithATimeoutError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == NetworkIssues.Timeout }
        }
    }

    @Test
    fun shouldReturnAFactNotFoundError() {
        RandomFactMother.WithAFactNotFoundError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationErrors.FactNotFound }
        }
    }

    @Test
    fun shouldReturnAUnexpectedDataError() {
        RandomFactMother.WithAUnexpectedDataError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationErrors.UnexpectedData }
        }
    }

    @Test
    fun shouldReturnAUnavailableProviderError() {
        RandomFactMother.WithAUnavailableProviderError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationErrors.UnavailableProvider }
        }
    }

    @Test
    fun shouldReturnSuccess() {
        RandomFactMother.withASuccessScenario {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Success }
        }
    }
}