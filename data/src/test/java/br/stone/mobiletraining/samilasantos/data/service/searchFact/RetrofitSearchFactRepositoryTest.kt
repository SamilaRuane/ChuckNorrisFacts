package br.stone.mobiletraining.samilasantos.data.service.searchFact

import br.stone.mobiletraining.samilasantos.data.service.common.FactScenarios
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactExceptions
import org.junit.Test

class RetrofitSearchFactRepositoryTest {
    @Test
    fun `given a info not found result, RetrofitFactRepository should return a InfoNotFound object `() {
        FactScenarios.runWithError(404) {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.InfoNotFound)
        }
    }

    @Test
    fun `given a bad request result, RetrofitFactRepository should return a MalFormedQuery object `() {
        FactScenarios.runWithError(400) {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertError(SearchFactExceptions.MalFormedQuery)
        }
    }

    @Test
    fun `given a empty result, RetrofitFactRepository should return a QueryNotMatch object `() {
        FactScenarios.runWithSuccess(SearchFactMother.emptyBody) {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertError(SearchFactExceptions.QueryNotMatchException)
        }
    }

    @Test
    fun `given a server exception, RetrofitFactRepository should return an UnavailableProvider object `() {
        FactScenarios.runWithError(500) {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnavailableProvider)
        }
    }

    @Test
    fun `given a timeout occurrence, RetrofitFactRepository should return a Timeout object`() {
        FactScenarios.runWithLongTimeDurationRequest {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertError(NetworkIssues.Timeout)
        }
    }

    @Test
    fun `given an Ok response, RetrofitFactRepository should return Success`() {
        FactScenarios.runWithSuccess(
            SearchFactMother.successBody
        ) {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertValue {
                it[0] == SearchFactMother.successBodyObject[0] &&
                    it[1] == SearchFactMother.successBodyObject[1]
            }
        }
    }

    @Test
    fun `given a Ok response with a malformed body, RetrofitFactRepository should return an UnexpectedData object`() {
        FactScenarios.runWithSuccess(
            SearchFactMother.malFormedBody
        ) {
            val ts = getFactsThatContains("dev").test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnexpectedData)
        }
    }
}