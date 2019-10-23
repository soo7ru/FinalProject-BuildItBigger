
package com.udacity.gradle.builditbigger;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import androidx.test.AndroidTestCase;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class EndpointsAsyncTaskTestUnit extends AndroidTestCase{
    String TAG = EndpointsAsyncTask.class.getSimpleName();
   @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);



   @Test
   public void testJokeIsNotEmpty() throws Exception {

       EndpointsAsyncTask aTest =  new EndpointsAsyncTask();
       aTest.execute(InstrumentationRegistry.getContext());
       String joke = aTest.get(5, TimeUnit.SECONDS);
       Assert.assertTrue(!joke.equals(""));
   }

    @Test
    public void testVerifyResponse() {


        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.jokes_text_view)).check(matches(isDisplayed()));
    }

}

