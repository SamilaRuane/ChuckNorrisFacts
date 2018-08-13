package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.support.test.runner.AndroidJUnit4
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithError
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithNoNetwork
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithSuccess
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact.RandomFactActivityRobot.launchActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RandomFactActivityTest {

    @Test
    fun once_activity_has_launched_should_present_the_initial_state() {
        launchActivity {
            isHeaderVisible()
            isFactVisible()
            isSearchIconVisible()
            isShareIconVisible()
            isUpdateIconVisible()
        }
    }

    @Test
    fun given_a_timeout_error_then_verify_if_timeout_message_shown() {
        Scenarios.runWithLongTimeDurationRequest {
            launchActivity {
                clickOnUpdateButton()
                verifyIfTextMatches(RandomFactActivityMother.timeoutFeedback())
                closeDialog()
            }
        }
    }

    @Test
    fun given_a_sever_error_then_verify_if_unavailable_provider_message_is_shown() {
        runWithError(500) {
            launchActivity {
                clickOnUpdateButton()
                verifyIfTextMatches(RandomFactActivityMother.unavailableProviderFeedback())
                closeDialog()
            }
        }
    }

    @Test
    fun given_a_malformed_body_then_verify_if_unexpected_data_message_is_shown() {
        runWithSuccess(RandomFactActivityMother.malFormedBody) {
            launchActivity {
                clickOnUpdateButton()
                verifyIfTextMatches(RandomFactActivityMother.unexpectedDataFeedback())
                closeDialog()
            }
        }
    }

    @Test
    fun given_a_success_scenario_then_verify_if_success_state_is_shown() {
        runWithSuccess(RandomFactActivityMother.successBody) {
            launchActivity {
                clickOnUpdateButton()
                isFactVisible()
                verifyIfTextMatches(RandomFactActivityMother.fact)
            }
        }
    }

    @Test
    fun once_has_no_network_then_verify_if_no_network_exception_dialog_is_shown() {
        runWithNoNetwork {
            launchActivity {
                clickOnUpdateButton()
                verifyIfTextMatches(RandomFactActivityMother.noNetworkFeedback())
                closeDialog()
            }
        }
    }
}