package br.stone.mobiletraining.samilasantos.data.service.common

import org.junit.Test

class ChuckNorrisServiceTest {

    @Test
    fun `shouldReturnAFact`() {
        val ts = ChuckNorrisService(RetrofitManager.chuckNorrisFactsApi("")).getFact().test()
        ts.awaitTerminalEvent()
        ts.assertValue { it.id == "3AFG5656ADD" }
    }

    @Test
    fun `shouldReturnAnError`() {
        val ts = ChuckNorrisService(RetrofitManager.chuckNorrisFactsApi("")).getFact().test()
        ts.awaitTerminalEvent()
        ts.assertValue { it.id == "3AFG5656ADD" }
    }
}