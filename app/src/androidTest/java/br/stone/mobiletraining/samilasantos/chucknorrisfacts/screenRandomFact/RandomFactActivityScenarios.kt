package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.App
import br.stone.mobiletraining.samilasantos.data.service.common.MockWebServerUtils
import br.stone.mobiletraining.samilasantos.data.service.common.RetrofitFactRepository
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import org.junit.Rule
import retrofit2.Retrofit

object RandomFactActivityScenarios {
    @Rule
    @JvmField
    val activityRule =
        ActivityTestRule<RandomFactActivity>(RandomFactActivity::class.java, false, false)

    private val retrofit = getApp().injector.configurableGraph.instance<Retrofit>()

    fun launchActivity(
        func: RandomFactActivityRobot.() -> Unit
    ) {
        activityRule.launchActivity(null)
        func.invoke(RandomFactActivityRobot)
    }

    fun runWithError(errorCode: Int, test: RetrofitFactRepository.() -> Unit) {
        MockWebServerUtils.runWithMockServerErrorResponse(errorCode) { baseUrl: String ->
            injector(baseUrl)

            test.invoke(RetrofitFactRepository(retrofit))
        }
    }

    fun runWithSuccess(body: String, test: RetrofitFactRepository.() -> Unit) {
        MockWebServerUtils.runWithMockServerOkResponse(body) { baseUrl: String ->
            injector(baseUrl)
            test.invoke(RetrofitFactRepository(retrofit))
        }
    }

    fun runWithLongTimeDurationRequest(test: RandomFactActivityRobot.() -> Unit) {
        injector("http://127.0.1.1:1413/")
        test.invoke(RandomFactActivityRobot)
    }

    private fun injector(baseUrl: String) {
        val app = getApp()
        app.injector.reconfigureGraph {
            bind<String>(overrides = true) with provider {
                baseUrl
            }
        }
    }

    private fun getApp() = InstrumentationRegistry.getTargetContext().applicationContext as App
}