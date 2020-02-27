package com.example.bakingapp;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

    public static final String RECIPE_SERVING = "8";
    public static final String RECIPE_INGREDIENTS = "Ingredients:";
    public static final String RECIPE_DESCRIPTION = "Description:";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickStepItem_OpensRecipeActivity() {
        onView(withId(R.id.recipe_card)).perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tv_serving)).check(matches(withText(RECIPE_SERVING)));
        onView(withId(R.id.ingredients_label)).check(matches(withText(RECIPE_INGREDIENTS)));
        onView(withId(R.id.description_label)).check(matches(withText(RECIPE_DESCRIPTION)));
    }
}
