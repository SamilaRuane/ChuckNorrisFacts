package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.support.test.espresso.intent.Intents
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithError
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithLongTimeDurationRequest
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithNoNetwork
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.Scenarios.runWithSuccess
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact.SearchFactActivityRobot.launchActivity
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchFactActivityTest {

    @Before
    fun init() {
        Intents.init()
    }

    @Test
    fun once_activity_has_launched_should_present_the_initial_state() {
        launchActivity {
            checkIfSearchBarIsVisible()
            checkIfInitialMessageIsVisible()
        }
    }

    @Test
    fun given_a_timeout_error_then_verify_if_timeout_message_shown() {
        runWithLongTimeDurationRequest {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.timeoutFeedback())
                checkIfRetryButtonIsVisible()
                checkIfSettingsButtonIsVisible()
            }
        }
    }

    @Test
    fun given_a_sever_error_then_verify_if_unavailable_provider_message_is_shown() {
        runWithError(500) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.unavailableProviderFeedback())
            }
        }
    }

    @Test
    fun given_a_malformed_body_then_verify_if_unexpected_data_message_is_shown() {
        runWithSuccess(SearchFactActivityMother.malFormedBody) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.unexpectedDataFeedback())
            }
        }
    }

    @Test
    fun given_a_success_scenario_then_verify_if_success_state_is_shown() {
        runWithSuccess(SearchFactActivityMother.successBody) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfListIsVisible()
            }
        }
    }

    @Test
    fun given_an_empty_result_then_verify_if_error_view_is_shown() {
        runWithSuccess(SearchFactActivityMother.emptyBody) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.queryNotMatchFeedback())
            }
        }
    }

    @Test
    fun given_a_not_found_result_then_verify_if_error_view_is_shown() {
        runWithError(404) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.infoNotFoundFeedback())
            }
        }
    }

    @Test
    fun given_a_bad_request_result_then_verify_if_error_view_is_shown() {
        runWithError(400) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.malformedQueryFeedback())
            }
        }
    }

    @Test
    fun once_has_no_network_then_verify_if_error_view_is_shown() {
        runWithNoNetwork {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.noNetworkFeedback())
                checkIfRetryButtonIsVisible()
                checkIfSettingsButtonIsVisible()
            }
        }
    }

    @Test
    fun given_an_share_icon_click_then_should_open_chooser() {
        runWithSuccess(SearchFactActivityMother.successBody) {
            launchActivity {
                type("dev")
                clickOnSearchButton()
                clickOnShareButton()
                verifyIfActionSendIntentWasTriggered()
            }
        }
    }

    @After
    fun finish() {
        Intents.release()
    }
}