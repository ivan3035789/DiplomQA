package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

public class WatchScreenElements {
    private final ViewInteraction okButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));


    private final ViewInteraction cancelButton = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            2)));

    private final ViewInteraction buttonToChangeTheTypeOfClock = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input."),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    4),
                            0),
                    isDisplayed()));
    private final ViewInteraction inputHour = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.RelativeLayout")),
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.TextInputTimePickerView")),
                                            1)),
                            0)));

    private final ViewInteraction inputMinute = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.RelativeLayout")),
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.TextInputTimePickerView")),
                                            1)),
                            3)));

    private final ViewInteraction textTime = onView(
            allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Set time"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.TimePicker.class)))));

    public ViewInteraction getInputHour() {
        return inputHour;
    }

    public ViewInteraction getInputMinute() {
        return inputMinute;
    }

    public ViewInteraction getTextTime() {
        return textTime;
    }

    public ViewInteraction getButtonToChangeTheTypeOfClock() {
        return buttonToChangeTheTypeOfClock;
    }

    public ViewInteraction getOkButton() {
        return okButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }
}
