package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ThematicQuotesScreenElements {

    private final ViewInteraction loveIsAll = onView(allOf(withId(R.id.our_mission_title_text_view), withText("Love is all")));
    private final ViewInteraction quoteTitle = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
    private final ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));

    public ViewInteraction getRecyclerView() {
        return recyclerView;
    }

    public ViewInteraction getLoveIsAll() {
        return loveIsAll;
    }

    public ViewInteraction getQuoteTitle() {
        return quoteTitle;
    }

}
