package br.stone.mobiletraining.samilasantos.data.service.randomFact

import br.stone.mobiletraining.samilasantos.data.service.common.FactScenarios
import br.stone.mobiletraining.samilasantos.data.service.common.RetrofitFactRepository
import br.stone.mobiletraining.samilasantos.data.service.common.RetrofitManager
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import org.junit.Test

class RetrofitRandomFactRepositoryTest {

    @Test
    fun `given a fact not found error, RetrofitFactRepository should return a FactNotFound object `() {
        FactScenarios.runWithError(404) {
            val ts = getFact().test()
            ts.awaitTerminalEvent()
            ts.assertError(IntegrationExceptions.InfoNotFound)
        }
    }

    @Test
    fun `given a server error, RetrofitFactRepository should return an UnavailableProvider object `() {
        FactScenarios.runWithError(500) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnavailableProvider)
        }
    }

    @Test
    fun `given a timeout occurrence, RetrofitFactRepository should return a Timeout object`() {
        val retrofit =
            RetrofitManager.buildRetrofit("http://127.0.1.1:1413/")
        val service =
            RetrofitFactRepository(retrofit)
        val ts = service.getFact().test()
        ts.awaitTerminalEvent()
        ts.assertError(NetworkIssues.Timeout)
    }

    @Test
    fun `given an Ok response, RetrofitFactRepository should return Success`() {
        FactScenarios.runWithSuccess(
            RandomFactMother.successbody
        ) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertValue {
                it == RandomFactMother.successBodyObject
            }
        }
    }

    @Test
    fun `given a Ok response with a malformed body, RetrofitFactRepository should return an UnexpectedData object`() {
        FactScenarios.runWithSuccess(
            RandomFactMother.malFormedBody
        ) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnexpectedData)
        }
    }
}