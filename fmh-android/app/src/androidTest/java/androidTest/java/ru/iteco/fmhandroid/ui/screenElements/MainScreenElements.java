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

public class MainScreenElements {
    private final ViewInteraction mainMenuImageButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction titleNews = onView(withText("News"));
    private final ViewInteraction titleAbout = onView(withText("About"));
    private final ViewInteraction titleMain = onView(withText("Main"));
    private final ViewInteraction titleClaimsScreen = onView(withText("Claims"));
    private final ViewInteraction chapterNews = onView(withText("News"));
    private final ViewInteraction chapterClaims = onView(withText("Claims"));
    private final ViewInteraction allNews = onView(withId(R.id.all_news_text_view));
    private final ViewInteraction allClaims = onView(withId(R.id.all_claims_text_view));
    private final ViewInteraction expandNewsButton = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.all_news_cards_block_constraint_layout),
                            0)));

    private final ViewInteraction claimList  = onView(
            allOf(withId(R.id.claim_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.all_claims_cards_block_constraint_layout),
                            4)));

    private final ViewInteraction createClaimsButton = onView(withId(R.id.add_new_claim_material_button));

    private final ViewInteraction thematicQuotesButton = onView(withId(R.id.our_mission_image_button));

    private final ViewInteraction expandNewsFeedButton = onView(
            allOf(withId(R.id.expand_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(R.id.container_list_news_include_on_fragment_main),
                                    0),
                            4),
                    isDisplayed()));

    private final ViewInteraction expandTheClaimsFeedButton = onView(
            allOf(withId(R.id.expand_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(R.id.container_list_claim_include_on_fragment_main),
                                    0),
                            3),
                    isDisplayed()));

    public ViewInteraction getMainMenuImageButton() {
        return mainMenuImageButton;
    }

    public ViewInteraction getThematicQuotesButton() {
        return thematicQuotesButton;
    }

    public ViewInteraction getCreateClaimsButton() {
        return createClaimsButton;
    }

    public ViewInteraction getClaimList() {
        return claimList;
    }

    public ViewInteraction getExpandNewsButton() {
        return expandNewsButton;
    }

    public ViewInteraction getAllNews() {
        return allNews;
    }

    public ViewInteraction getAllClaims() {
        return allClaims;
    }

    public ViewInteraction getTitleClaimsScreen() {
        return titleClaimsScreen;
    }

    public ViewInteraction getChapterNews() {
        return chapterNews;
    }

    public ViewInteraction getChapterClaims() {
        return chapterClaims;
    }

    public ViewInteraction getTitleNews() {
        return titleNews;
    }

    public ViewInteraction getTitleAbout() {
        return titleAbout;
    }

    public ViewInteraction getTitleMain() {
        return titleMain;
    }

    public ViewInteraction getExpandNewsFeedButton() {
        return expandNewsFeedButton;
    }

    public ViewInteraction getExpandTheClaimsFeedButton() {
        return expandTheClaimsFeedButton;
    }

}
