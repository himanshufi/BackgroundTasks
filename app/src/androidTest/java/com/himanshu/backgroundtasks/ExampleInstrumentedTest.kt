package com.himanshu.backgroundtasks

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {

        launchActivity<MainActivity>()

        onView(withId(R.id.startBtn)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.closeBtn)).perform(click())

        Thread.sleep(60000)

        launchActivity<MainActivity>()

        onView(withId(R.id.textView))
                .check(matches(withText(containsString("12"))))


    }
}