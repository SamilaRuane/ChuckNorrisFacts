package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.RandomFactExceptions
import org.junit.Test

class RetrofitFactRepositoryTest {

    @Test
    fun `given a fact not found error, RetrofitFactRepository should return a FactNotFound object `() {
        CommonMother.runWithError(404) {
            val ts = getFact().test()
            ts.awaitTerminalEvent()
            ts.assertError(RandomFactExceptions.FactNotFound)
        }
    }

    @Test
    fun `given a server error, RetrofitFactRepository should return an UnavailableProvider object `() {
        CommonMother.runWithError(500) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnavailableProvider)
        }
    }

    @Test
    fun `given a timeout occurrence, RetrofitFactRepository should return a Timeout object`() {
        val retrofit = RetrofitManager.buildRetrofit("http://127.0.1.1:1413/")
        val service = RetrofitFactRepository(retrofit)
        val ts = service.getFact().test()
        ts.awaitTerminalEvent()
        ts.assertError(NetworkIssues.Timeout)
    }

    @Test
    fun `given an Ok response, RetrofitFactRepository should return Success`() {
        CommonMother.runWithSuccess(CommonMother.successbody) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertValue { it.id == CommonMother.succesBodyId }
        }
    }

    @Test
    fun `given a Ok response with a malformed body, RetrofitFactRepository should return an UnexpectedData object`() {
        CommonMother.runWithSuccess(CommonMother.malFormedBody) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnexpectedData)
        }
    }
}