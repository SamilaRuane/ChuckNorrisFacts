package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactMother.repository
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.RandomFactExceptions
import org.junit.Test

class RandomFactTest {

    @Test
    fun `given a no network error, GetRandomFact should return an error`() {
        RandomFactMother.withANoNetworkError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == NetworkIssues.NoNetwork }
        }
    }

    @Test
    fun `given a timeout error, GetRandomFact should return an error`() {
        RandomFactMother.withATimeoutError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == NetworkIssues.Timeout }
        }
    }

    @Test
    fun `given a fact not found error, GetRandomFact should return an error`() {
        RandomFactMother.withAFactNotFoundError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == RandomFactExceptions.FactNotFound }
        }
    }

    @Test
    fun `given an unexpected data error, GetRandomFact should return an error`() {
        RandomFactMother.withAUnexpectedDataError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationExceptions.UnexpectedData }
        }
    }

    @Test
    fun `given a unavailable provider error, GetRandomFact should return an error`() {
        RandomFactMother.withAUnavailableProviderError {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationExceptions.UnavailableProvider }
        }
    }

    @Test
    fun `given success scenario, GetRandomFact should return success`() {
        RandomFactMother.withASuccessScenario {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Success }
        }
    }
}