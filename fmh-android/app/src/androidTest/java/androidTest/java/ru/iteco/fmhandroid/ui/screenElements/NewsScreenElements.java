package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsScreenElements {
    private final ViewInteraction screenNameNews = onView(withText("News"));

    private final ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction filterNewsPage = onView(withId(R.id.filter_news_title_text_view));

    private final ViewInteraction buttonSort = onView(withId(R.id.sort_news_material_button));

    private final ViewInteraction editButton = onView(
            allOf(withId(R.id.edit_news_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(R.id.container_list_news_include),
                                    0),
                            3),
                    isDisplayed()));

    private final ViewInteraction expandNewsButton = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.all_news_cards_block_constraint_layout),
                            0)));

    private final ViewInteraction newsRecyclerList = onView(withId(R.id.news_list_recycler_view));

    public ViewInteraction getNewsRecyclerList() {
        return newsRecyclerList;
    }

    public ViewInteraction getScreenNameNews() {
        return screenNameNews;
    }

    public ViewInteraction getFilterNewsButton() {
        return filterNewsButton;
    }

    public ViewInteraction getButtonSort() {
        return buttonSort;
    }

    public ViewInteraction getEditButton() {
        return editButton;
    }

    public ViewInteraction getFilterNewsPage() {
        return filterNewsPage;
    }

    public ViewInteraction getExpandNewsButton() {
        return expandNewsButton;
    }
}
