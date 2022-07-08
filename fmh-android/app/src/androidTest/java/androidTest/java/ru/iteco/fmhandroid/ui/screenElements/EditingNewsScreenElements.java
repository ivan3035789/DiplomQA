package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class EditingNewsScreenElements {
    private final ViewInteraction categoryName = onView(withHint("Category"));
    private final ViewInteraction titleName = onView(withHint("Title"));
    private final ViewInteraction publicationDateName = onView(withHint("Publication date"));
    private final ViewInteraction timeName = onView(withHint("Time"));
    private final ViewInteraction descriptionName = onView(withHint("Description"));

    private final ViewInteraction categoryField = onView(
            allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
    private final ViewInteraction titleTextNewsField = onView(
            allOf(withId(R.id.news_item_title_text_input_edit_text)));
    private final ViewInteraction publishDateField = onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));

    private final ViewInteraction newsItemTitle = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    private final ViewInteraction timeField = onView(
            allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
    private final ViewInteraction descriptionField = onView(
            allOf(withId(R.id.news_item_description_text_input_edit_text)));

    private final ViewInteraction checkBox = onView(
            allOf(withId(R.id.switcher)));

    private final ViewInteraction statusNotActive = onView(
            allOf(withId(R.id.news_item_published_text_view), withText("NOT ACTIVE"),
                    withParent(withParent(withId(R.id.news_item_material_card_view))),
                    isDisplayed()));

    private final ViewInteraction statusActive = onView(
            allOf(withId(R.id.news_item_published_text_view), withText("ACTIVE"),
                    withParent(withParent(withId(R.id.news_item_material_card_view))),
                    isDisplayed()));

    private final ViewInteraction saveButton = onView(
            allOf(withId(R.id.save_button)));
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
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    ViewInteraction cancelButton2  = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(androidx.appcompat.R.id.buttonPanel),
                                    0),
                            2)));

    private final ViewInteraction editingNameScreen = onView(
            allOf(withId(R.id.custom_app_bar_title_text_view), withText("Editing"),
                    withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_news),
                            withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                    isDisplayed()));

    private final ViewInteraction newsNameScreen = onView(
            allOf(withId(R.id.custom_app_bar_sub_title_text_view), withText("News"),
                    withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_create_edit_news),
                            withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                    isDisplayed()));
    private final ViewInteraction category = onView(allOf(withClassName(is("PopupWindow$PopupBackgroundView")), withParent(withParent(allOf(withClassName(is("DropDownListView")), withChild(withChild(allOf(withClassName(is("MaterialTextView")), withText("Зарплата")))))))));

    public ViewInteraction getCategory() {
        return category;
    }

    public ViewInteraction getCategoryField() {
        return categoryField;
    }

    public ViewInteraction getTitleTextNewsField () {
        return titleTextNewsField ;
    }

    public ViewInteraction getPublishDateField() {
        return publishDateField;
    }

    public ViewInteraction getTimeField() {
        return timeField;
    }

    public ViewInteraction getDescriptionField() {
        return descriptionField;
    }

    public ViewInteraction getNewsItemTitle() {
        return newsItemTitle;
    }

    public ViewInteraction getCheckBox() {
        return checkBox;
    }

    public ViewInteraction getStatusNotActive() {
        return statusNotActive;
    }

    public ViewInteraction getStatusActive() {
        return statusActive;
    }

    public ViewInteraction getSaveButton() {
        return saveButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getOkButton() {
        return okButton;
    }

    public ViewInteraction getCancelButton2() {
        return cancelButton2;
    }

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

    public ViewInteraction getEditingNameScreen() {
        return editingNameScreen;
    }

    public ViewInteraction getNewsNameScreen() {
        return newsNameScreen;
    }
}
