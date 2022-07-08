package androidTest.java.ru.iteco.fmhandroid.ui.step;

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
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.dateFormat;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import java.text.ParseException;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.NewsScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class NewsScreenStep {

    NewsScreenElements newsScreenElements = new NewsScreenElements();

    @Step("Проверка названия экрана filterNews")
    public void checkingTheScreenNameFilterNews() {
        newsScreenElements.getFilterNewsPage().check(matches(isDisplayed()));
    }

    @Step("Нажатие на кнопку смены сортировки новостей")
    public void clickingOnTheNewsSortingChangeButton() {
        newsScreenElements.getButtonSort().perform(click());
    }

    @Step("Нажатие на кнопку перехода в Filter News")
    public void clickingOnTheButtonToGoToFilterNews() {
        newsScreenElements.getFilterNewsButton().perform(click());
    }

    @Step("Нажатие на новость")
    public void clickingOnTheNews(int position) {
        newsScreenElements.getNewsRecyclerList().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на кнопку перехода в Control panel")
    public void clickingOnTheButtonToGoToTheControlPanel() {
        newsScreenElements.getEditButton().perform(click());
    }

    @Step("Нажатие на кнопку для развертывания/свертывания новостного блока ")
    public void clickingOnTheButtonExpandTheNewsFeed(int position) {
        newsScreenElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на кнопку развертывания/свертывания описания Новости")
    public void clickingOnTheExpandNewsDescriptionButton(int position) {
        newsScreenElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Проверка названия экрана News")
    public void checkTheNameOfTheNewsScreen() {
        newsScreenElements.getScreenNameNews().check(matches(withText("News"))).check(matches(isDisplayed()));
    }


    @Step("Проверка видемости текста описания новости ")
    public void checkingTheTextOfTheNewsDescriptionIsVisible(int position) {
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction textNews =  onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        SystemClock.sleep(3000);
        textNews.check(matches(isDisplayed()));
    }

    @Step("Проверка даты найденной новости с данными введенными для поиска")
    public void checkingTheDateOfTheFoundNewsWithTheDataEnteredForTheSearch(
            String dateOnCardNews, String dateStartInput, String dateEndInput, String localDate) throws ParseException {
        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
    }

    @Step("Взять название первой новости до сортировки")
    public String takeTheNameOfTheFirstNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    @Step("Взять описание первой новости до сортировки")
    public String takeTheDescriptionOfTheFirstNewsBeforeSorting(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    @Step("Взять название первой новости после первой сортировки")
    public String takeTheNameOfTheFirstNewsAfterSorting(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    @Step("Взять описание первой новости после первой сортировки")
    public String takeTheDescriptionOfTheFirstNewsAfterTheFirstSorting(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
    }

    @Step("Взять название первой новости после двух сортировок")
    public String takeTheNameOfTheFirstNewsAfterTwoSorts(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    @Step("Взять описание первой новости после двух сортировок")
    public String takeTheDescriptionOfTheFirstNewsAfterTwoSorts(int position) {
        return Helper.Text.getText(onView(allOf(withIndex(withId(R.id.news_item_description_text_view), position))));
    }

    @Step("Проверка данных найденной новости с введенными данными поиска")
    public void checkingTheDataOfTheFoundNewsWithTheEnteredSearchData(String category, String categoryText) {
        assertEquals(category, categoryText);
    }

    @Step("Сверка названий и описаний новостей после двух сортировок")
    public void reconciliationOfNewsTitlesAndDescriptionsAfterSorting(String firstNews, String firstNewsDescription, String lastNews, String lastNewsDescription, String firstNewsAgain, String firstAgainNewsDescription) {
        assertEquals(firstNews, firstNewsAgain);
        assertEquals(firstNewsDescription, firstAgainNewsDescription);

        if (firstNews.equals(lastNews)) {
            assertEquals(firstNews, lastNews);
        } else {
            assertNotEquals(firstNews, lastNews);
        }
        assertNotEquals(firstNewsDescription, lastNewsDescription);
    }

    @Step("Проверка найденных данных из новости с введенными для поиска ")
    public void checkingTheFoundDataFromTheNewsWithTheDataEnteredForTheSearch(
            String dateOnCardNews, String dateStartInput, String dateEndInput, String category, String localDate, int position) throws ParseException {
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

    @Step("Проверка отображения данных найденной новости")
    public void checkingTheDisplayOfTheFoundNewsData(int position) {
        onView(withIndex(withId(R.id.news_item_date_text_view), position)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.news_item_title_text_view), position)).check(matches(isDisplayed()));
    }

    @Step("Проверка отображение надписи при отсутствии найденных по критериям новостей")
    public void checkingTheDisplayOfTheInscriptionInTheAbsenceOfFoundNews() {
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
