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

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilteringWindowScreenElements {

    private final ViewInteraction filteringNameScreen = onView(withText("Filtering"));
    private final ViewInteraction checkBoxOpen = onView(withId(R.id.item_filter_open));
    private final ViewInteraction checkBoxInProgress = onView(withId(R.id.item_filter_in_progress));
    private final ViewInteraction checkBoxExecuted = onView(withId(R.id.item_filter_executed));
    private final ViewInteraction checkBoxCancelled = onView(withId(R.id.item_filter_cancelled));
    private final ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.claim_filter_cancel_material_button));

    private final ViewInteraction filteringButton = onView(
            allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(R.id.container_list_claim_include),
                                    0),
                            1),
                    isDisplayed()));

    private final ViewInteraction warningMessage = onView(
            allOf(withId(R.id.empty_claim_list_text_view), withText("There is nothing here yet�"),
                    withParent(allOf(withId(R.id.all_claims_cards_block_constraint_layout),
                            withParent(withId(R.id.container_list_claim_include))))));

    public ViewInteraction getFilteringButton() {
        return filteringButton;
    }

    public ViewInteraction getWarningMessage() {
        return warningMessage;
    }

    public ViewInteraction getCheckBoxOpen() {
        return checkBoxOpen;
    }

    public ViewInteraction getCheckBoxInProgress() {
        return checkBoxInProgress;
    }

    public ViewInteraction getCheckBoxExecuted() {
        return checkBoxExecuted;
    }

    public ViewInteraction getCheckBoxCancelled() {
        return checkBoxCancelled;
    }

    public ViewInteraction getOkButton() {
        return okButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getFilteringNameScreen() {
        return filteringNameScreen;
    }
}
