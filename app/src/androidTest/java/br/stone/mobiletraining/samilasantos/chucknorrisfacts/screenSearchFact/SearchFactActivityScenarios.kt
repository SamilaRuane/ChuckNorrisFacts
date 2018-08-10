package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.support.test.rule.ActivityTestRule
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import org.junit.Rule

object SearchFactActivityScenarios : Scenarios() {

    @Rule
    @JvmField
    val activityRule =
        ActivityTestRule<SearchFactActivity>(
            SearchFactActivity::class.java, false, false
        )

    fun launchActivity(func: SearchFactActivityRobot.() -> Unit) {
        activityRule.launchActivity(null)
        func.invoke(SearchFactActivityRobot)
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
                SearchFactActivityMother.MockRepository
            }
        }
    }
}