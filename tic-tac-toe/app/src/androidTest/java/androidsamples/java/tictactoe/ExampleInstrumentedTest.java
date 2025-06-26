//package androidsamples.java.tictactoe;
//
//import android.content.Context;
//
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentedTest {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("androidsamples.java.tictactoe", appContext.getPackageName());
//    }
//}


package androidsamples.java.tictactoe;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test Case 1: Login Functionality
     * Ensures valid credentials navigate the user to the DashboardFragment.
     */
//    @Test
//    public void testValidLoginNavigatesToDashboard() {
//        // Enter email and password
//        onView(withId(R.id.edit_email)).perform(typeText("visputekshitij2@gmail.com"), closeSoftKeyboard());
//        onView(withId(R.id.edit_password)).perform(typeText("byebyesdpd123"), closeSoftKeyboard());
//
//        // Click the login button
//        onView(withId(R.id.btn_log_in)).perform(click());
//
//        // Verify navigation to DashboardFragment
//        onView(withId(R.id.fab_new_game)).check(matches(isDisplayed()));
//    }

    /**
     * Test Case 2: Game Creation Dialog
     * Verifies the "New Game" button shows the dialog and navigates to the GameFragment.
     */
    @Test
    public void testGameCreationDialog() {
        // Navigate to the dashboard
        onView(withId(R.id.fab_new_game)).perform(click());

        // Verify the dialog is displayed
        onView(withText(R.string.new_game_dialog_message)).check(matches(isDisplayed()));

        // Select "Two-Player Game" option
        onView(withText(R.string.two_player)).perform(click());

        // Verify navigation to GameFragment
        onView(withId(R.id.display_tv)).check(matches(isDisplayed()));
    }

    /**
     * Test Case 3: Single-Player Gameplay
     * Validates that single-player mode allows user moves and computer responses.
     */
    @Test
    public void testSinglePlayerGamePlay() {
        // Start single-player game
        onView(withId(R.id.fab_new_game)).perform(click());
        onView(withText(R.string.one_player)).perform(click());

        // Simulate a user move
        onView(withId(R.id.button0)).perform(click()).check(matches(withText("X")));

        // Verify computer move
        onView(withText("O")).check(matches(isDisplayed()));
    }

    /**
     * Test Case 4: Multiplayer Turn Handling
     * Ensures correct turn alternation between two players in multiplayer mode.
     */
    @Test
    public void testMultiplayerTurnHandling() {
        // Start a two-player game
        onView(withId(R.id.fab_new_game)).perform(click());
        onView(withText(R.string.two_player)).perform(click());

        // Player 1 move
        onView(withId(R.id.button0)).perform(click()).check(matches(withText("X")));

        // Verify Player 2's turn
        onView(withId(R.id.display_tv)).check(matches(withText(R.string.waiting)));
    }

    /**
     * Test Case 5: Forfeit Game
     * Ensures forfeiting increments the loss count and navigates back to the dashboard.
     */
    @Test
    public void testGameForfeit() {
        // Start a game
        onView(withId(R.id.fab_new_game)).perform(click());
        onView(withText(R.string.one_player)).perform(click());

        // Press the back button
        androidx.test.espresso.Espresso.pressBack();

        // Confirm forfeit
        onView(withText(R.string.forfeit_game_dialog_message)).check(matches(isDisplayed()));
        onView(withText(R.string.yes)).perform(click());

        // Verify navigation back to dashboard
        onView(withId(R.id.fab_new_game)).check(matches(isDisplayed()));
    }


    @Test
    public void testQuitGameButton() {
        // Start a single-player game (or two-player game)
        onView(withId(R.id.fab_new_game)).perform(click());
        onView(withText(R.string.one_player)).perform(click());

        // Simulate a few moves to start the game
        onView(withId(R.id.button0)).perform(click()); // Player's move
        onView(withId(R.id.button3)).perform(click()); // Computer's move
        onView(withId(R.id.button1)).perform(click()); // Player's move

        // Verify that the game is in progress and "Quit Game" button is visible
        onView(withId(R.id.back_btn)).check(matches(isDisplayed()));

        // Click the "Quit Game" button
        onView(withId(R.id.back_btn)).perform(click());

        // Verify that the confirmation dialog for quitting the game appears
        onView(withText(R.string.forfeit_game_dialog_message)).check(matches(isDisplayed()));

        // Confirm quitting the game
        onView(withText(R.string.yes)).perform(click());

        // Verify navigation back to the dashboard
        onView(withId(R.id.fab_new_game)).check(matches(isDisplayed()));
    }


}