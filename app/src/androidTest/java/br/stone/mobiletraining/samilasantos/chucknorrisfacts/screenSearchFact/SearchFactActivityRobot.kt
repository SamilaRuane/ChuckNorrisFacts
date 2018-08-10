package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition

object SearchFactActivityRobot {

    private const val SETTINGS = "settings"
    private const val RETRY = "Retry"

    fun type(word: String) {
        onView(withId(R.id.editSearchQuery)).perform(
            typeText(
                word
            )
        ).perform(ViewActions.closeSoftKeyboard())
    }

    fun clickOnSearchButton() {
        clickOn(R.id.imageIconSearch)
    }

    fun checkIfListIsVisible() {
        assertDisplayed(R.id.recyclerFactsList)
    }

    fun checkIfErrorLayoutIsVisible() {
        assertDisplayed(R.id.imageChuckNorris)
        assertDisplayed(R.id.textErrorInfo)
    }

    fun checkIfInitialMessageIsVisible() {
        assertDisplayed(R.id.textWaitingForInput)
    }

    fun checkIfSearchBarIsVisible() {
        assertDisplayed(R.id.editSearchQuery)
        assertDisplayed(R.id.imageIconSearch)
    }

    fun checkIfRetryButtonIsVisible() {
        scrollListToPosition(R.id.recyclerFactsList, 2)
        assertDisplayed(RETRY)
    }

    fun checkIfSettingsButtonIsVisible() {
        scrollListToPosition(R.id.recyclerFactsList, 2)
        assertDisplayed(SETTINGS)
    }

    fun verifyIfTextMatches(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }
}