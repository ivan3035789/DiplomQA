package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNewsScreenElements {
    private final ViewInteraction nameFilterNews = onView(withText("Filter news"));
    private final ViewInteraction filterNewsPage = onView(withId(R.id.filter_news_title_text_view));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction dateStartField = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction dateEndField = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction textInputEnd = onView(withId(R.id.text_input_end_icon));
    private final ViewInteraction fieldNameCategory = onView(withHint("Category"));
    private final ViewInteraction fieldNameStartDate = onView(
            allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text), withHint("DD.MM.YYYY"),
                    withParent(withParent(withId(R.id.news_item_publish_date_start_text_input_layout)))));

    private final ViewInteraction fieldNameEndDate = onView(
            allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text), withHint("DD.MM.YYYY"),
                    withParent(withParent(withId(R.id.news_item_publish_date_end_text_input_layout)))));

    public ViewInteraction getFilterNewsPage() {
        return filterNewsPage;
    }

    public ViewInteraction getTextInputEnd() {
        return textInputEnd;
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

    public ViewInteraction getNameFilterNews() {
        return nameFilterNews;
    }

    public ViewInteraction getDateStartField() {
        return dateStartField;
    }

    public ViewInteraction getDateEndField() {
        return dateEndField;
    }

    public ViewInteraction getCategoryField() {
        return categoryField;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getFilterButton() {
        return filterButton;
    }
}
