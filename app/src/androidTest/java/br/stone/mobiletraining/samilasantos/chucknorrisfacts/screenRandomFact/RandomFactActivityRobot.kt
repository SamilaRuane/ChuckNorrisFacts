package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R

object RandomFactActivityRobot {

    fun isFactVisible() {
        onView(withId(R.id.view_separator)).check(matches(isDisplayed()))
        onView(withId(R.id.text_fact)).check(matches(isDisplayed()))
    }

    fun isHeaderVisible() = {
        onView(withId(R.id.linear_header)).check(matches(isDisplayed()))
        onView(withId(R.id.image_chuck)).check(matches(isDisplayed()))
        onView(withId(R.id.text_chuck_norris_name)).check(matches(isDisplayed()))
        onView(withId(R.id.quote_left)).check(matches(isDisplayed()))
        onView(withId(R.id.quote_right)).check(matches(isDisplayed()))
    }

    fun isUpdateIconVisible() {
        onView(withId(R.id.icon_search)).check(matches(isDisplayed()))
    }

    fun isSearchIconVisible() {
        onView(withId(R.id.icon_search)).check(matches(isDisplayed()))
    }

    fun isShareIconVisible() {
        onView(withId(R.id.icon_share)).check(matches(isDisplayed()))
    }

    fun clickOnUpdateButton() = onView(withId(R.id.icon_update)).perform(ViewActions.click())

    fun verifyIfTextMatches(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun closeDialog() {
        onView(withId(android.R.id.button1)).perform(click())
    }
}
