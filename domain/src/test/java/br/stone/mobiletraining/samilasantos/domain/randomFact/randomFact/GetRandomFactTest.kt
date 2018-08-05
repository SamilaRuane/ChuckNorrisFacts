package br.stone.mobiletraining.samilasantos.domain.randomFact.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactResult
import br.stone.mobiletraining.samilasantos.domain.randomFact.randomFact.RandomFactMother.repository
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import org.junit.Test

class GetRandomFactTest {

    @Test
    fun `given a no network error, GetRandomFact should return an error`() {
        RandomFactMother.withANoNetworkException {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == NetworkIssues.NoNetwork }
        }
    }

    @Test
    fun `given a timeout error, GetRandomFact should return an error`() {
        RandomFactMother.withATimeoutException {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == NetworkIssues.Timeout }
        }
    }

    @Test
    fun `given a fact not found error, GetRandomFact should return an error`() {
        RandomFactMother.withAFactNotFoundException {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationExceptions.InfoNotFound }
        }
    }

    @Test
    fun `given an unexpected data error, GetRandomFact should return an error`() {
        RandomFactMother.withAUnexpectedDataException {
            val ts = GetRandomFact(repository)
                .execute().test()
            ts.awaitTerminalEvent()
            ts.assertValue { it is RandomFactResult.Error }
            ts.assertValue { (it as RandomFactResult.Error).error == IntegrationExceptions.UnexpectedData }
        }
    }

    @Test
    fun `given a unavailable provider error, GetRandomFact should return an error`() {
        RandomFactMother.withAUnavailableProviderException {
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