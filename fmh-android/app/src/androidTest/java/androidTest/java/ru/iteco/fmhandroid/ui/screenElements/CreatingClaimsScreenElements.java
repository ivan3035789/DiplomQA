package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class CreatingClaimsScreenElements {

    private final ViewInteraction titleName = onView(withHint("Title"));
    private final ViewInteraction executorName = onView(withHint("Executor"));
    private final ViewInteraction dateName = onView(withHint("Date"));
    private final ViewInteraction timeName = onView(withHint("Time"));
    private final ViewInteraction descriptionName = onView(withHint("Description"));

    private final ViewInteraction titleClaimField = onView(withId(R.id.title_edit_text));
    private final ViewInteraction executorClaimField = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    private final ViewInteraction dateClaimField = onView(withId(R.id.date_in_plan_text_input_edit_text));
    private final ViewInteraction timeClaimField = onView(withId(R.id.time_in_plan_text_input_edit_text));
    private final ViewInteraction descriptionClaimField = onView(withId(R.id.description_edit_text));

    private final ViewInteraction creatingNameScreen = onView(
            allOf(withId(R.id.custom_app_bar_title_text_view), withText("Creating"),
                    withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_claim),
                            withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))))));

    private final ViewInteraction claimsNameScreen = onView(
            allOf(withId(R.id.custom_app_bar_sub_title_text_view), withText("Claims"),
                    withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_claim),
                            withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))))));

    private final ViewInteraction okButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    private final ViewInteraction saveButton = onView(allOf(withId(R.id.save_button), withText("SAVE")));

    private final ViewInteraction cancelButton = onView(
            allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("com.google.android.material.card.MaterialCardView")),
                                    0),
                            7)));

    private final ViewInteraction cancelButtonInWindow = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            2)));

    public ViewInteraction getCancelButtonInWindow() {
        return cancelButtonInWindow;
    }

    public ViewInteraction getTitleClaimField() {
        return titleClaimField;
    }

    public ViewInteraction getExecutorClaimField() {
        return executorClaimField;
    }

    public ViewInteraction getDateClaimField() {
        return dateClaimField;
    }

    public ViewInteraction getTimeClaimField() {
        return timeClaimField;
    }

    public ViewInteraction getDescriptionClaimField() {
        return descriptionClaimField;
    }

    public ViewInteraction getOkButton() {
        return okButton;
    }

    public ViewInteraction getSaveButton() {
        return saveButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getTitleName() {
        return titleName;
    }

    public ViewInteraction getExecutorName() {
        return executorName;
    }

    public ViewInteraction getDateName() {
        return dateName;
    }

    public ViewInteraction getTimeName() {
        return timeName;
    }

    public ViewInteraction getDescriptionName() {
        return descriptionName;
    }

    public ViewInteraction getCreatingNameScreen() {
        return creatingNameScreen;
    }

    public ViewInteraction getClaimsNameScreen() {
        return claimsNameScreen;
    }

}
