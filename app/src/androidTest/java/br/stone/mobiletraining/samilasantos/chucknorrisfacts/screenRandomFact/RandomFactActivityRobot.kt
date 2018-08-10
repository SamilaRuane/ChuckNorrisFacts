package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn

object RandomFactActivityRobot {

    fun isFactVisible() {
        assertDisplayed(R.id.viewSeparator)
        assertDisplayed(R.id.textFactDescription)
    }

    fun isHeaderVisible() {
        assertDisplayed(R.id.linearHeader)
        assertDisplayed(R.id.imageChuck)
        assertDisplayed(R.id.textChuckNorrisName)
        assertDisplayed(R.id.quoteLeft)
        assertDisplayed(R.id.quoteRight)
    }

    fun isUpdateIconVisible() {
        assertDisplayed(R.id.iconUpdate)
    }

    fun isSearchIconVisible() {
        assertDisplayed(R.id.iconSearch)
    }

    fun isShareIconVisible() {
        assertDisplayed(R.id.iconShare)
    }

    fun clickOnUpdateButton() {
        clickOn(R.id.iconUpdate)
    }

    fun clickOnSearchButton() {
        clickOn(R.id.iconSearch)
    }

    fun verifyIfActivityWasLaunched(clazz: Class<*>) {
        intended(hasComponent(clazz.name))
    }

    fun verifyIfTextMatches(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun closeDialog() {
        clickOn(android.R.id.button1)
    }
}