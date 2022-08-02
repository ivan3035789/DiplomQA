package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.dateFormat;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import java.text.ParseException;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.NewsScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsScreenStep {

    NewsScreenElements newsScreenElements = new NewsScreenElements();

    public void checkingTheScreenNameFilterNews() {
        Allure.step("Проверка названия экрана filterNews");
        newsScreenElements.getFilterNewsPage().check(matches(isDisplayed()));
    }

    public void clickingOnTheNewsSortingChangeButton() {
        Allure.step("Нажатие на кнопку смены сортировки новостей");
        newsScreenElements.getButtonSort().perform(click());
    }

    public void clickingOnTheButtonToGoToFilterNews() {
        Allure.step("Нажатие на кнопку перехода в Filter News");
        newsScreenElements.getFilterNewsButton().perform(click());
    }

    public void clickingOnTheNews(int position) {
        Allure.step("Нажатие на новость");
        newsScreenElements.getNewsRecyclerList().perform(actionOnItemAtPosition(position, click()));
    }

    public void clickingOnTheButtonToGoToTheControlPanel() {
        Allure.step("Нажатие на кнопку перехода в Control panel");
        newsScreenElements.getEditButton().perform(click());
    }

    public void clickingOnTheButtonExpandTheNewsFeed(int position) {
        Allure.step("Нажатие на кнопку для развертывания/свертывания новостного блока");
        newsScreenElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
    }

    public void clickingOnTheExpandNewsDescriptionButton(int position) {
        Allure.step("Нажатие на кнопку развертывания/свертывания описания Новости");
        newsScreenElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
    }

    public void checkTheNameOfTheNewsScreen() {
        Allure.step("Проверка названия экрана News");
        newsScreenElements.getScreenNameNews().check(matches(withText("News"))).check(matches(isDisplayed()));
    }

    public void checkingTheTextOfTheNewsDescriptionIsVisible(int position) {
        Allure.step("Проверка видемости текста описания новости");
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction textNews =  onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        SystemClock.sleep(3000);
        textNews.check(matches(isDisplayed()));
    }

    public void checkingTheDateOfTheFoundNewsWithTheDataEnteredForTheSearch(
            String dateOnCardNews, String dateStartInput, String dateEndInput, String localDate) throws ParseException {
        Allure.step("Проверка даты найденной новости с данными введенными для поиска");
        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
    }

    public String takeTheNameOfTheFirstNews(int position) {
        Allure.step("Взять название первой новости до сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String takeTheDescriptionOfTheFirstNewsBeforeSorting(int position) {
        Allure.step("Взять описание первой новости до сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    public String takeTheNameOfTheFirstNewsAfterSorting(int position) {
        Allure.step("Взять название первой новости после первой сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String takeTheDescriptionOfTheFirstNewsAfterTheFirstSorting(int position) {
        Allure.step("Взять описание первой новости после первой сортировки");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    public String takeTheNameOfTheFirstNewsAfterTwoSorts(int position) {
        Allure.step("Взять название первой новости после двух сортировок");
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String takeTheDescriptionOfTheFirstNewsAfterTwoSorts(int position) {
        Allure.step("Взять описание первой новости после двух сортировок");
        return Helper.Text.getText(onView(allOf(withIndex(withId(R.id.news_item_description_text_view), position))));
    }

    public void checkingTheDataOfTheFoundNewsWithTheEnteredSearchData(String category, String categoryText) {
        Allure.step("Проверка данных найденной новости с введенными данными поиска");
        assertEquals(category, categoryText);
    }

    public void reconciliationOfNewsTitlesAndDescriptionsAfterSorting(
            String firstNews, String firstNewsDescription, String lastNews, String lastNewsDescription, String firstNewsAgain,
            String firstAgainNewsDescription) {
        Allure.step("Сверка названий и описаний новостей после двух сортировок");
        assertEquals(firstNews, firstNewsAgain);
        assertEquals(firstNewsDescription, firstAgainNewsDescription);

        if (firstNews.equals(lastNews)) {
            assertEquals(firstNews, lastNews);
        } else {
            assertNotEquals(firstNews, lastNews);
        }
        assertNotEquals(firstNewsDescription, lastNewsDescription);
    }

    public void checkingTheFoundDataFromTheNewsWithTheDataEnteredForTheSearch(
            String dateOnCardNews, String dateStartInput, String dateEndInput, String category, String localDate, int position) throws ParseException {
        Allure.step("Проверка найденных данных из новости с введенными для поиска");
        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));
        String categoryText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
        assertEquals(category, categoryText);

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
    }

    public void checkingTheDisplayOfTheFoundNewsData(int position) {
        Allure.step("Проверка отображения данных найденной новости");
        onView(withIndex(withId(R.id.news_item_date_text_view), position)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.news_item_title_text_view), position)).check(matches(isDisplayed()));
    }

    public void checkingTheDisplayOfTheInscriptionInTheAbsenceOfFoundNews() {
        Allure.step("Проверка отображение надписи при отсутствии найденных по критериям новостей");
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
    }

    public String dateOnCardNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_date_text_view), position)));
    }

    public String categoryText(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String news() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }
}
