package androidTest.java.ru.iteco.fmhandroid.ui.step;

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
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertNotEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.dateFormat;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.localDate;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import java.text.ParseException;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ControlPanelScreenStep {

    ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();

    @Step("Нажатие на кнопку перехода к экрану создания новости")
    public void clickingOnTheButtonToGoToTheNewsCreationScreen() {
        controlPanelScreenElements.getCreateNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку для перехода на экран расширенного поиска новостей")
    public void pressingTheButtonToGoToTheAdvancedNewsSearchScreen() {
        controlPanelScreenElements.getFilterNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку перехода на экран редактирование новости")
    public void clickingOnTheButtonToGoToTheNewsEditingScreen() {
        controlPanelScreenElements.getEditingNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку удаления новости")
    public void clickingOnTheDeleteNewsButton() {
        controlPanelScreenElements.getDeleteNews().perform(click());
    }

    @Step("Нажатие на кнопку удаления новости")
    public void clickingOnTheDeleteNewsButtonPosition(String nameNews) {
        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNews)))))))).perform(click());
    }

    @Step("Нажатие на кнопку подтверждения удаления новости")
    public void clickingOnTheConfirmationButtonToDeleteTheNews() {
        controlPanelScreenElements.getOkDeleteNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены подтверждения удаления новости")
    public void clickingOnTheCancelConfirmationButtonToDeleteTheNews() {
        controlPanelScreenElements.getCancelDeleteNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку перехода к экрану Editing News")
    public void clickingOnTheButtonToGoToTheEditingNewsScreen(int position) {
        onView(withIndex(withId(R.id.edit_news_item_image_view), position)).perform(click());
    }

    @Step("Нажатие на новость выбранную случайным образом")
    public void clickingOnRandomlySelectedNewsItem(int position) {
        controlPanelScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на кнопку смены сортировки новостей")
    public void changeOfSorting() {
        controlPanelScreenElements.getButtonSort().perform(click());
    }

    @Step("Проверка названия экрана Control Panel")
    public void checkingTheNameOfTheControlPanelScreen() {
        controlPanelScreenElements.getControlPanelNameScreen().check(matches(isDisplayed()));
    }

    @Step("Проверка статуса Active")
    public void CheckingTheStatusActive(int position) {
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));

        onView(allOf(withIndex(withId(R.id.news_item_material_card_view), position))).check(matches(isDisplayed()));
        assertEquals("ACTIVE", statusAfter.toUpperCase());
    }

    @Step("Проверка статуса Not Active")
    public void CheckingTheStatusNotActive(int position) {
        String statusAfter = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_published_text_view), position)));
        ViewInteraction statusAfter2 = onView(withIndex(withId(R.id.news_item_published_text_view), position));

        statusAfter2.check(matches(isDisplayed()));
        assertEquals("NOT ACTIVE", statusAfter.toUpperCase());
    }

    @Step("Проверка статуса найденной новости")
    public void checkingTheStatusOfTheFoundNews(int position) {
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

    @Step("Проверка смены статуса")
    public void checkingTheStatusChange(String statusBefore, String statusAfter, ViewInteraction statusAfter2) {
        assertNotEquals(statusBefore, statusAfter);
        assertEquals("ACTIVE", statusAfter.toUpperCase().trim());

        statusAfter2.check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    @Step("Проверка найденной новости")
    public void checkingTheFoundNews(int position) {
        ViewInteraction dateOnCardNews = onView(allOf(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
        try {
            dateOnCardNews.check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
        }
    }

    @Step("Сравнение данных поиска по дате с данными новости")
    public void comparisonOfSearchDataByDateWithNewsData(int position, String dateStartInput, String dateEndInput) throws ParseException {
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

    @Step("Сравнение данных поиска с данными новости")
    public void comparisonOfSearchDataWithNewsData(
            String dateOnCardNews,String dateStartInput,String dateEndInput,int position,String category) throws ParseException {
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

    @Step("Проверка введенных данных для поиска с полученными из новости")
    public void checkingTheEnteredDataForTheSearchWithThoseObtainedFromTheNews(String category, int position) {
        String categoryText = categoryTextOnCardNews(position);
        assertEquals(category, categoryText);
    }

    @Step("Проверка новостей до сортировки и после")
    public void checkingTheNewsBeforeSortingAndAfter(String firstNews, String firstNewsAgain, String lastNews) {
        assertEquals(firstNews, firstNewsAgain);
        assertNotEquals(firstNews, lastNews);
    }

    @Step("Проверка данных первой новости в списке до и после отмены удаления новости")
    public void checkingTheDataOfTheFirstNewsInTheListBeforeAndAfterCancelingTheDeletionOfTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        assertEquals(nameNewsItWas , nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Step("Проверка данных первой новости в списке до и после удаления новости")
    public void checkingTheDataOfTheFirstNewsInTheListBeforeAndAfterDeletingTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
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
}
