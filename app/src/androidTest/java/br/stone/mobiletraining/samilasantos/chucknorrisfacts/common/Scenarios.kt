package br.stone.mobiletraining.samilasantos.chucknorrisfacts.common

import android.support.test.InstrumentationRegistry
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.App
import br.stone.mobiletraining.samilasantos.data.service.common.MockWebServerUtils
import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import io.reactivex.Single

object Scenarios {

    fun runWithError(errorCode: Int, test: () -> Unit) {
        MockWebServerUtils.runWithMockServerErrorResponse(errorCode) { baseUrl: String ->
            injector(
                baseUrl
            )

            test.invoke()
        }
    }

    fun runWithSuccess(body: String, test: () -> Unit) {
        MockWebServerUtils.runWithMockServerOkResponse(body) { baseUrl: String ->
            injector(
                baseUrl
            )
            test.invoke()
        }
    }

    fun runWithLongTimeDurationRequest(test: () -> Unit) {
        val randomIpAddress = "http://127.0.1.1:1413/"
        injector(randomIpAddress)
        test.invoke()
    }

    private fun injector(baseUrl: String) {
        val app =
            getApp()
        app.injector.reconfigureGraph {
            bind<String>(overrides = true) with provider {
                baseUrl
            }
        }
    }

    fun runWithNoNetwork(func: () -> Unit) {
        mockRepository()
        func()
    }

    private fun mockRepository() {
        val app =
            getApp()
        app.injector.reconfigureGraph {
            bind<SearchFactRepository>(overrides = true) with provider {
                MockRepository
            }
        }
    }

    private fun getApp() = InstrumentationRegistry.getTargetContext().applicationContext as App
}

object MockRepository : SearchFactRepository, RandomFactRepository {
    override fun getFactsThatContains(query: String): Single<List<Fact>> = Single.error(NetworkIssues.NoNetwork)

    override fun getFact(): Single<Fact> = Single.error(NetworkIssues.NoNetwork)
}