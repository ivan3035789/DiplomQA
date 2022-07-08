package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanelScreenElements {

    private final ViewInteraction controlPanelNameScreen = onView(withText("Control panel"));
    private final ViewInteraction newsItemTitle = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    private final ViewInteraction creating = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    private final ViewInteraction description = onView(withId(R.id.news_item_description_text_view));
    private final ViewInteraction news = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    private final ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction buttonSort = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction deleteNews = onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(allOf(withIndex(withId(R.id.news_item_title_text_view), 0)))))))));
    private final ViewInteraction okDeleteNewsButton = onView(
            allOf(withId(android.R.id.button1), withText("OK"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            3)));

    private final ViewInteraction cancelDeleteNewsButton = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.ScrollView")),
                                    0),
                            2)));

    private final ViewInteraction editingNewsButton = onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(allOf(withIndex(withId(R.id.news_item_title_text_view), 0)))))))));

    private final ViewInteraction createNewsButton = onView(
            allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    1),
                            3),
                    isDisplayed()));

    private final ViewInteraction recyclerView = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                            0)));

    public ViewInteraction getDescription() {
        return description;
    }

    public ViewInteraction getButtonSort() {
        return buttonSort;
    }

    public ViewInteraction getFilterNewsButton() {
        return filterNewsButton;
    }

    public ViewInteraction getNewsItemTitle() {
        return newsItemTitle;
    }

    public ViewInteraction getOkDeleteNewsButton() {
        return okDeleteNewsButton;
    }

    public ViewInteraction getCancelDeleteNewsButton() {
        return cancelDeleteNewsButton;
    }

    public ViewInteraction getDeleteNews() {
        return deleteNews;
    }

    public ViewInteraction getRecyclerView() {
        return recyclerView;
    }

    public ViewInteraction getCreateNewsButton() {
        return createNewsButton;
    }

    public ViewInteraction getEditingNewsButton() {
        return editingNewsButton;
    }

    public ViewInteraction getControlPanelNameScreen() {
        return controlPanelNameScreen;
    }

    public ViewInteraction getCreating() {
        return creating;
    }

    public ViewInteraction getNews() {
        return news;
    }
}
