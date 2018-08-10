package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.support.test.espresso.intent.Intents
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
        SearchFactActivityScenarios.launchActivity {
            checkIfSearchBarIsVisible()
            checkIfInitialMessageIsVisible()
        }
    }

    @Test
    fun given_a_timeout_error_then_verify_if_timeout_message_shown() {
        SearchFactActivityScenarios.runWithLongTimeDurationRequest {
            SearchFactActivityScenarios.launchActivity {
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
        SearchFactActivityScenarios.runWithError(500) {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.unavailableProviderFeedback())
            }
        }
    }

    @Test
    fun given_a_malformed_body_then_verify_if_unexpected_data_message_is_shown() {
        SearchFactActivityScenarios.runWithSuccess(SearchFactActivityMother.malFormedBody) {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.unexpectedDataFeedback())
            }
        }
    }

    @Test
    fun given_a_success_scenario_then_verify_if_success_state_is_shown() {
        SearchFactActivityScenarios.runWithSuccess(SearchFactActivityMother.successBody) {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfListIsVisible()
            }
        }
    }

    @Test
    fun given_an_empty_result_then_verify_if_error_view_is_shown() {
        SearchFactActivityScenarios.runWithSuccess(SearchFactActivityMother.emptyBody) {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.queryNotMatchFeedback())
            }
        }
    }

    @Test
    fun given_a_not_found_result_then_verify_if_error_view_is_shown() {
        SearchFactActivityScenarios.runWithError(404) {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.infoNotFoundFeedback())
            }
        }
    }

    @Test
    fun given_a_bad_request_result_then_verify_if_error_view_is_shown() {
        SearchFactActivityScenarios.runWithError(400) {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.malformedQueryFeedback())
            }
        }
    }

    @Test
    fun once_has_no_network_then_verify_if_error_view_is_shown() {
        SearchFactActivityScenarios.runWithNoNetwork {
            SearchFactActivityScenarios.launchActivity {
                type("dev")
                clickOnSearchButton()
                checkIfErrorLayoutIsVisible()
                verifyIfTextMatches(SearchFactActivityMother.noNetworkFeedback())
                checkIfRetryButtonIsVisible()
                checkIfSettingsButtonIsVisible()
            }
        }
    }

    @After
    fun finish() {
        Intents.release()
    }
}