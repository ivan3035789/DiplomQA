package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreatingNewsScreenElements {

    private final ViewInteraction categoryName = onView(withHint("Category"));
    private final ViewInteraction titleName = onView(withHint("Title"));
    private final ViewInteraction publicationDateName = onView(withHint("Publication date"));
    private final ViewInteraction timeName = onView(withHint("Time"));
    private final ViewInteraction descriptionName = onView(withHint("Description"));

    private final ViewInteraction categoryFieldNews = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction titleFieldNews = onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction publicationDateFieldNews = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction timeFieldNews = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction descriptionFieldNews = onView(withId(R.id.news_item_description_text_input_edit_text));
    private final ViewInteraction creatingNameScreen = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    private final ViewInteraction newsNameScreen = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = onView(
            allOf(withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("com.google.android.material.card.MaterialCardView")),
                                    0),
                            7)));

    private final ViewInteraction okButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(androidx.appcompat.R.id.buttonPanel),
                                    0),
                            3)));

    private final ViewInteraction cancelButton2 = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(androidx.appcompat.R.id.buttonPanel),
                                    0),
                            2)));

    public ViewInteraction getCategoryName() {
        return categoryName;
    }

    public ViewInteraction getTitleName() {
        return titleName;
    }

    public ViewInteraction getPublicationDateName() {
        return publicationDateName;
    }

    public ViewInteraction getTimeName() {
        return timeName;
    }

    public ViewInteraction getDescriptionName() {
        return descriptionName;
    }

    public ViewInteraction getSaveButton() {
        return saveButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getCategoryFieldNews() {
        return categoryFieldNews;
    }

    public ViewInteraction getTitleFieldNews() {
        return titleFieldNews;
    }

    public ViewInteraction getPublicationDateFieldNews() {
        return publicationDateFieldNews;
    }

    public ViewInteraction getTimeFieldNews() {
        return timeFieldNews;
    }

    public ViewInteraction getDescriptionFieldNews() {
        return descriptionFieldNews;
    }

    public ViewInteraction getCreatingNameScreen() {
        return creatingNameScreen;
    }

    public ViewInteraction getNewsNameScreen() {
        return newsNameScreen;
    }

    public ViewInteraction getOkButton() {
        return okButton;
    }

    public ViewInteraction getCancelButton2() {
        return cancelButton2;
    }
}
