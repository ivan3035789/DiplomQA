package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.dateFormat;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.localDate;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import java.text.ParseException;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ControlPanelScreenStep {

    ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();

    public void clickingOnTheButtonToGoToTheNewsCreationScreen() {
        Allure.step("Нажатие на кнопку перехода к экрану создания новости");
        controlPanelScreenElements.getCreateNewsButton().perform(click());
    }

    public void pressingTheButtonToGoToTheAdvancedNewsSearchScreen() {
        Allure.step("Нажатие на кнопку для перехода на экран расширенного поиска новостей");
        controlPanelScreenElements.getFilterNewsButton().perform(click());
    }

    public void clickingOnTheButtonToGoToTheNewsEditingScreen() {
        Allure.step("Нажатие на кнопку перехода на экран редактирование новости");
        controlPanelScreenElements.getEditingNewsButton().perform(click());
    }

    public void clickingOnTheDeleteNewsButton() {
        Allure.step("Нажатие на кнопку удаления новости");
        controlPanelScreenElements.getDeleteNews().perform(click());
    }

    public void clickingOnTheDeleteNewsButtonPosition(String nameNews) {
        Allure.step("Нажатие на кнопку удаления новости");
        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNews)))))))).perform(click());
    }

    public void clickingOnTheConfirmationButtonToDeleteTheNews() {
        Allure.step("Нажатие на кнопку подтверждения удаления новости");
        controlPanelScreenElements.getOkDeleteNewsButton().perform(click());
    }

    public void clickingOnTheCancelConfirmationButtonToDeleteTheNews() {
        Allure.step("Нажатие на кнопку отмены подтверждения удаления новости");
        controlPanelScreenElements.getCancelDeleteNewsButton().perform(click());
    }

    public void clickingOnTheButtonToGoToTheEditingNewsScreen(int position) {
        Allure.step("Нажатие на кнопку перехода к экрану Editing News");
        onView(withIndex(withId(R.id.edit_news_item_image_view), position)).perform(click());
    }

    public void clickingOnRandomlySelectedNewsItem(int position) {
        Allure.step("Нажатие на новость выбранную случайным образом");
        controlPanelScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
    }

    public void changeOfSorting() {
        Allure.step("Нажатие на кнопку смены сортировки новостей");
        controlPanelScreenElements.getButtonSort().perform(click());
    }

    public void checkingTheNameOfTheControlPanelScreen() {
        Allure.step("Проверка названия экрана Control Panel");
        controlPanelScreenElements.getControlPanelNameScreen().check(matches(isDisplayed()));
    }

    public void CheckingTheStatusActive(int position) {
        Allure.step("Проверка статуса Active");
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));

        onView(allOf(withIndex(withId(R.id.news_item_material_card_view), position))).check(matches(isDisplayed()));
        assertEquals("ACTIVE", statusAfter.toUpperCase());
    }

    public void CheckingTheStatusNotActive(int position) {
        Allure.step("Проверка статуса Not Active");
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        ViewInteraction statusAfter2 = onView(withIndex(withId(R.id.news_item_published_text_view), position));

        statusAfter2.check(matches(isDisplayed()));
        assertEquals("NOT ACTIVE", statusAfter.toUpperCase());
    }

    public void checkingTheStatusOfTheFoundNews(int position) {
        Allure.step("Проверка статуса найденной новости");
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        ViewInteraction statusAfter2 = onView(withIndex(withId(R.id.news_item_published_text_view), position));

        statusAfter2.check(matches(isDisplayed()));
        if (statusAfter.equals("ACTIVE")) {
            assertEquals("ACTIVE", statusAfter.toUpperCase());
        }
        else if (statusAfter.equals("NOT ACTIVE")){
            assertEquals("NOT ACTIVE", statusAfter.toUpperCase());
        }
    }

    public void checkingTheStatusChange(String statusBefore, String statusAfter, ViewInteraction statusAfter2) {
        Allure.step("Проверка смены статуса");
        assertNotEquals(statusBefore, statusAfter);
        assertEquals("ACTIVE", statusAfter.toUpperCase().trim());

        statusAfter2.check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public void checkingTheFoundNews(int position) {
        Allure.step("Проверка найденной новости");
        ViewInteraction dateOnCardNews = onView(allOf(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
        try {
            dateOnCardNews.check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
        }
    }

    public void comparisonOfSearchDataByDateWithNewsData(int position, String dateStartInput, String dateEndInput) throws ParseException {
        Allure.step("Сравнение данных поиска по дате с данными новости");
        String dateOnCardNews = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));

        String localDate = localDate();

        dateFormat(dateOnCardNews).after(dateFormat(dateStartInput));
        dateFormat(dateOnCardNews).before(dateFormat(dateEndInput));

        if (localDate.equals(dateOnCardNews)) {
            assertEquals(localDate, dateOnCardNews);
        } else {
            assertNotEquals(localDate, dateOnCardNews);
        }
    }

    public void comparisonOfSearchDataWithNewsData(
            String dateOnCardNews,String dateStartInput,String dateEndInput,int position,String category) throws ParseException {
        Allure.step("Сравнение данных поиска с данными новости");
        String localDate = localDate();

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

    public void checkingTheEnteredDataForTheSearchWithThoseObtainedFromTheNews(String category, int position) {
        Allure.step("Проверка введенных данных для поиска с полученными из новости");
        String categoryText = categoryTextOnCardNews(position);
        assertEquals(category, categoryText);
    }

    public void checkingTheNewsBeforeSortingAndAfter(String firstNews, String firstNewsAgain, String lastNews) {
        Allure.step("Проверка новостей до сортировки и после");
        assertEquals(firstNews, firstNewsAgain);
        assertNotEquals(firstNews, lastNews);
    }

    public void checkingTheDataOfTheFirstNewsInTheListBeforeAndAfterCancelingTheDeletionOfTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка данных первой новости в списке до и после отмены удаления новости");
        assertEquals(nameNewsItWas , nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    public void checkingTheInvisibilityOfTheNewsDescription(String descriptionNews) {
        Allure.step("Проверка невидимости описания новости");
        onView(allOf(withIndex(withId(R.id.news_item_description_text_view), 0), withText(descriptionNews))).check(matches(not(isDisplayed())));
    }

    public void checkingTheVisibilityOfTheNewsDescription() {
        Allure.step("Проверка видимости описания новости");
        onView(withIndex(withId(R.id.news_item_description_text_view), 0)).check(matches(isDisplayed()));
    }


    public void checkingTheDataOfTheFirstNewsInTheListBeforeAndAfterDeletingTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка данных первой новости в списке до и после удаления новости");
        assertNotEquals(nameNewsItWas , nameNewsItWasHasBecomes);

        if (publicationDateNewsItWas.equals(publicationDateNewsItWasHasBecomes)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        }
        if (creationDateNewsItWas.equals(creationDateNewsItWasHasBecomes)) {
            assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        } else {
            assertNotEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        }
        if (authorNewsItWas.equals(authorNewsItWasHasBecomes)) {
            assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        } else {
            assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
        }
    }

    public String dateOnCardNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
    }

    public String categoryTextOnCardNews(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public String nameNews() {
        return  Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String publicationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)));
    }

    public String creationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)));
    }

    public String authorNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_author_name_text_view), 0)));
    }

    public String descriptionNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }

    public String descriptionNewsPosition(int position) {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }
}
