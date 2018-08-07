package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import org.junit.Test

class RetrofitFactRepositoryTest {

    @Test
    fun `given a fact not found error, RetrofitFactRepository should return a InfoNotFound object `() {
        FactMother.runWithError(404) {
            val ts = getFact().test()
            ts.awaitTerminalEvent()
            ts.assertError(IntegrationExceptions.InfoNotFound)
        }
    }

    @Test
    fun `given a server error, RetrofitFactRepository should return an UnavailableProvider object `() {
        FactMother.runWithError(500) {
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
        FactMother.runWithSuccess(FactMother.successbody) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertValue {
                it.id == FactMother.successBodyObject.id &&
                    it.description == FactMother.successBodyObject.description &&
                    it.url == FactMother.successBodyObject.url &&
                    it.category == FactMother.successBodyObject.category
            }
        }
    }

    @Test
    fun `given a Ok response with a malformed body, RetrofitFactRepository should return an UnexpectedData object`() {
        FactMother.runWithSuccess(FactMother.malFormedBody) {
            val ts = getFact().test()

            ts.awaitTerminalEvent()

            ts.assertError(IntegrationExceptions.UnexpectedData)
        }
    }
}