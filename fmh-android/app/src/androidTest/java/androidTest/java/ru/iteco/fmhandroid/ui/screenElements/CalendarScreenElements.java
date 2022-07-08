package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;

public class CalendarScreenElements {
    private final ViewInteraction okButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")),
                            childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    private final ViewInteraction buttonToCancelTheYearSetting = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")),
                            childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            2)));

    private final ViewInteraction nextMonthButton = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Next month"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.DayPickerView")),
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")), withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                            0)),
                            2)));

    private final ViewInteraction previousMonthButton = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Previous month"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.DayPickerView")),
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")), withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                            0)),
                            1)));

    private final ViewInteraction buttonOfTheYear = onView(
            allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText("2022"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    0),
                            0)));

    private final DataInteraction year = onData(anything())
            .inAdapterView(allOf(withClassName(is("android.widget.YearPickerView")),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                            1)));

    private final ViewInteraction calendarMonthView = onView(withClassName(is("android.widget.DatePicker")));

    public ViewInteraction getCalendarMonthView() {
        return calendarMonthView;
    }

    public ViewInteraction getButtonToCancelTheYearSetting() {
        return buttonToCancelTheYearSetting;
    }

    public DataInteraction getYear() {
        return year;
    }

    public ViewInteraction getButtonOfTheYear() {
        return buttonOfTheYear;
    }

    public ViewInteraction getNextMonthButton() {
        return nextMonthButton;
    }

    public ViewInteraction getPreviousMonthButton() {
        return previousMonthButton;
    }

    public ViewInteraction getOkButton() {
        return okButton;
    }

}
