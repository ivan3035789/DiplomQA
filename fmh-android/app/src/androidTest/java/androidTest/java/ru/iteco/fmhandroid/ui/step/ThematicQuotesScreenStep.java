package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import androidx.test.espresso.ViewInteraction;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ThematicQuotesScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ThematicQuotesScreenStep {

    ThematicQuotesScreenElements thematicQuotesScreenElements = new ThematicQuotesScreenElements();

    @Step("Проветка названия экрана {loveIsAll}")
    public void checkingTheScreenName() {
        thematicQuotesScreenElements.getLoveIsAll().check(matches(isDisplayed()));
        thematicQuotesScreenElements.getLoveIsAll().check(matches(withText("Love is all")));
    }

    @Step("Выбор цитаты")
    public void quoteSelection(int position) {
        thematicQuotesScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на кнопку развернуть/свернуть описание цитаты")
    public void openDescription() {
        thematicQuotesScreenElements.getQuoteTitle().perform(click());
    }

    @Step("Проверка названия и описания")
    public void checkingTheText(String title, ViewInteraction title2, String description, ViewInteraction description2) {
        assertEquals("«Хоспис для меня - это то, каким должен быть мир.", title);
        title2.check(matches(isDisplayed()));
        assertEquals("Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер", description);
        description2.check(matches(isDisplayed()));
    }

    public String title(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.our_mission_item_title_text_view), position))).replaceAll("^\"|\"$", "");
    }

    public String description(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.our_mission_item_description_text_view), position))).replaceAll("^\"|\"$", "");
    }

    public ViewInteraction title2(int position) {
        return onView(withIndex(withId(R.id.our_mission_item_title_text_view), position));
    }

    public ViewInteraction description2(int position) {
        return onView(withIndex(withId(R.id.our_mission_item_description_text_view), position));
    }
}
