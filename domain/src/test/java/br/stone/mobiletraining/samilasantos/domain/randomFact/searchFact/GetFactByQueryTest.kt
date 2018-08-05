package br.stone.mobiletraining.samilasantos.domain.randomFact.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.searchFact.GetFactByQueryResult
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactExceptions
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.GetFactByQuery
import org.junit.Test

class GetFactByQueryTest {

    @Test
    fun `given a no network error, GetFactByQuery should return an error`() {
        SearchFactMother.withANoNetworkException {
            val testStream = GetFactByQuery(repository)
                .using("dev").test()
            testStream.awaitTerminalEvent()
            testStream.assertValue { it is GetFactByQueryResult.Error }
            testStream.assertValue { (it as GetFactByQueryResult.Error).error == NetworkIssues.NoNetwork }
        }
    }

    @Test
    fun `given a timeout error, GetFactByQuery should return an error`() {
        SearchFactMother.withATimeoutException {
            val testStream = GetFactByQuery(repository)
                .using("dev").test()
            testStream.awaitTerminalEvent()
            testStream.assertValue { it is GetFactByQueryResult.Error }
            testStream.assertValue { (it as GetFactByQueryResult.Error).error == NetworkIssues.Timeout }
        }
    }

    @Test
    fun `given a fact not found error, GetFactByQuery should return an error`() {
        SearchFactMother.withAQueryNotMatchException {
            val testStream = GetFactByQuery(repository)
                .using("dev").test()
            testStream.awaitTerminalEvent()
            testStream.assertValue { it is GetFactByQueryResult.Error }
            testStream.assertValue { (it as GetFactByQueryResult.Error).error == SearchFactExceptions.QueryNotMatchException }
        }
    }

    @Test
    fun `given an unexpected data error, GetFactByQuery should return an error`() {
        SearchFactMother.withAUnexpectedDataException {
            val testStream = GetFactByQuery(repository)
                .using("dev").test()
            testStream.awaitTerminalEvent()
            testStream.assertValue { it is GetFactByQueryResult.Error }
            testStream.assertValue { (it as GetFactByQueryResult.Error).error == IntegrationExceptions.UnexpectedData }
        }
    }

    @Test
    fun `given a unavailable provider error, GetFactByQuery should return an error`() {
        SearchFactMother.withAUnavailableProviderException {
            val testStream = GetFactByQuery(repository)
                .using("dev").test()
            testStream.awaitTerminalEvent()
            testStream.assertValue { it is GetFactByQueryResult.Error }
            testStream.assertValue { (it as GetFactByQueryResult.Error).error == IntegrationExceptions.UnavailableProvider }
        }
    }

    @Test
    fun `given success scenario, GetFactByQuery should return success`() {
        SearchFactMother.withASuccessScenario {
            val testStream = GetFactByQuery(repository).using("dev").test()
            testStream.awaitTerminalEvent()
            testStream.assertValue { it is GetFactByQueryResult.Success }
        }
    }
}
