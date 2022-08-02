package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.ThematicQuotesScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ThematicQuotesScreenStep {

    ThematicQuotesScreenElements thematicQuotesScreenElements = new ThematicQuotesScreenElements();

    public void checkingTheScreenName() {
        Allure.step("Проветка названия экрана {loveIsAll}");
        thematicQuotesScreenElements.getLoveIsAll().check(matches(isDisplayed()));
        thematicQuotesScreenElements.getLoveIsAll().check(matches(withText("Love is all")));
    }

    public void quoteSelection(int position) {
        Allure.step("Выбор цитаты");
        thematicQuotesScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
    }

    public void openDescription() {
        Allure.step("Нажатие на кнопку развернуть/свернуть описание цитаты");
        thematicQuotesScreenElements.getQuoteTitle().perform(click());
    }

    public void checkingTheText(String title, ViewInteraction title2, String description, ViewInteraction description2) {
        Allure.step("Проверка названия и описания");
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
