package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R

object RandomFactActivityRobot {

    fun isFactVisible() {
        onView(withId(R.id.viewSeparator)).check(matches(isDisplayed()))
        onView(withId(R.id.textFact)).check(matches(isDisplayed()))
    }

    fun isHeaderVisible() {
        onView(withId(R.id.linearHeader)).check(matches(isDisplayed()))
        onView(withId(R.id.imageChuck)).check(matches(isDisplayed()))
        onView(withId(R.id.textChuckNorrisName)).check(matches(isDisplayed()))
        onView(withId(R.id.quoteLeft)).check(matches(isDisplayed()))
        onView(withId(R.id.quoteRight)).check(matches(isDisplayed()))
    }

    fun isUpdateIconVisible() {
        onView(withId(R.id.iconSearch)).check(matches(isDisplayed()))
    }

    fun isSearchIconVisible() {
        onView(withId(R.id.iconSearch)).check(matches(isDisplayed()))
    }

    fun isShareIconVisible() {
        onView(withId(R.id.iconShare)).check(matches(isDisplayed()))
    }

    fun clickOnUpdateButton() {
        onView(withId(R.id.iconUpdate)).perform(ViewActions.click())
    }

    fun verifyIfTextMatches(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun closeDialog() {
        onView(withId(android.R.id.button1)).perform(click())
    }
}
