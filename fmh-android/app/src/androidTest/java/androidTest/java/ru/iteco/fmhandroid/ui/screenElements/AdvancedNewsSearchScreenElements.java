package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AdvancedNewsSearchScreenElements {

    private final ViewInteraction namePage = onView(withId(R.id.filter_news_title_text_view));
    private final ViewInteraction categoryField = onView(
            allOf(withId(R.id.news_item_category_text_auto_complete_text_view),
                     childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(R.id.news_item_category_text_input_layout),
                                    0),
                            0),
                    isDisplayed()));
    private final ViewInteraction dateStartField = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction dateEndField = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction checkBoxActive = onView(withId(R.id.filter_news_active_material_check_box));
    private final ViewInteraction checkBoxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction fieldNameCategory = onView(withHint("Category"));
    private final ViewInteraction fieldNameStartDate = onView(
            allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text), withHint("DD.MM.YYYY"),
                    withParent(withParent(withId(R.id.news_item_publish_date_start_text_input_layout)))));
    private final ViewInteraction fieldNameEndDate = onView(
            allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withHint("DD.MM.YYYY"),
                    withParent(withParent(withId(R.id.news_item_publish_date_end_text_input_layout)))));

    public ViewInteraction getNamePage() {
        return namePage;
    }

    public ViewInteraction getCategoryField() {
        return categoryField;
    }

    public ViewInteraction getDateStartField() {
        return dateStartField;
    }

    public ViewInteraction getDateEndField() {
        return dateEndField;
    }

    public ViewInteraction getCheckBoxActive() {
        return checkBoxActive;
    }

    public ViewInteraction getCheckBoxNotActive() {
        return checkBoxNotActive;
    }

    public ViewInteraction getFilterButton() {
        return filterButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getFieldNameCategory() {
        return fieldNameCategory;
    }

    public ViewInteraction getFieldNameStartDate() {
        return fieldNameStartDate;
    }

    public ViewInteraction getFieldNameEndDate() {
        return fieldNameEndDate;
    }

}
