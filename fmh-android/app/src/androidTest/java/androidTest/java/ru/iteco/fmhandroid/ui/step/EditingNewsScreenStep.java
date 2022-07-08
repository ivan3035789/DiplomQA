package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.EditingNewsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class EditingNewsScreenStep {

    EditingNewsScreenElements editingNewsScreenElements = new EditingNewsScreenElements();

    @Step("Удаление названия новости")
    public void deletingTheNewsTitle() {
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
    }

    @Step("Нажатие на поле Category")
    public void clickingOnTheCategoryField() {
        editingNewsScreenElements.getCategoryField().perform(replaceText(""), click()).perform(closeSoftKeyboard());
    }

    @Step("Нажатие на поле Publication date")
    public void clickingOnThePublicationDateField() {
        editingNewsScreenElements.getPublishDateField().perform(scrollTo(), click());
    }

    @Step("Нажатие на поле Time")
    public void clickingOnTheTimeField() {
        editingNewsScreenElements.getTimeField().perform(click());
    }

    @Step("Нажатие на кнопку сохранить")
    public void clickingOnTheSaveButton() {
        editingNewsScreenElements.getSaveButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены редактирования новости")
    public void clickingOnTheCancelNewsEditingButton() {
        editingNewsScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены выхода из редактирования")
    public void clickingOnTheCancelButtonToExitEditing() {
        editingNewsScreenElements.getCancelButton2().perform(click());
    }

    @Step("Нажатие на кнопку подтверждения отмены редактирования новости")
    public void clickingOnTheButtonToConfirmTheCancellationOfNewsEditing() {
        editingNewsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    @Step("Нажатие на Чек бокс")
    public void clickingOnTheCheckBox() {
        editingNewsScreenElements.getCheckBox().perform(click());
    }

    @Step("Нажатие на кнопку для входа в редактирование новости")
    public void clickingOnTheButtonToEnterTheNewsEditing(String nameNewsItWas) {
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
    }

    @Step("Нажатие на новость ")
    public void clickingOnTheNews(String nameNewsItWas) {
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
    }

    @Step("Ввод текста в поле Category")
    public void enteringTextInTheCategoryField(String text) {
        editingNewsScreenElements.getCategoryField().perform(replaceText(text));
    }

    @Step("Ввод невалидного языка")
    public void invalidLanguage(String title, String description) {
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsScreenElements.getDescriptionField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        editingNewsScreenElements.getTitleTextNewsField().perform(typeText(title)).perform(closeSoftKeyboard());
        editingNewsScreenElements.getDescriptionField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    @Step("Ввод валидного языка")
    public void validLanguage(String title, String category, String description) {
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText(title));
        editingNewsScreenElements.getCategoryField().perform(replaceText(category));
        editingNewsScreenElements.getDescriptionField().perform(replaceText(description)).perform(closeSoftKeyboard());
    }

    @Step("Заполнение полей новости новыми данными")
    public void fillingInTheNewsFieldsWithNewData(String text, String Category) {
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();

        editingNewsScreenElements.getCategoryField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsScreenElements.getCategoryField().perform(replaceText(Category)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText(text)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        editingNewsScreenElements.getPublishDateField().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        editingNewsScreenElements.getTimeField().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        editingNewsScreenElements.getDescriptionField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsScreenElements.getDescriptionField().perform(replaceText(text)).perform(closeSoftKeyboard());
    }

    @Step("Проверка на присудствие в полях слов из английских букв")
    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        editingNewsScreenElements.getTitleTextNewsField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingNewsScreenElements.getCategoryField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
    }

    @Step("Проверка на отсутствие в полях слов из русских букв")
    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        editingNewsScreenElements.getTitleTextNewsField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionField().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    @Step("Проверка названия экрана Editing News")
    public void checkingTheNameOfTheEditingNewsScreen() {
        editingNewsScreenElements.getEditingNameScreen().check(matches(withText("Editing"))).check(matches(isDisplayed()));
        editingNewsScreenElements.getNewsNameScreen().check(matches(withText("News"))).check(matches(isDisplayed()));
    }

    @Step("Проверка идентифицирующих названий полей")
    public void checkNameFieldInEditingNews() {
        editingNewsScreenElements.getCategoryName().check(matches(isDisplayed()));
        editingNewsScreenElements.getTitleName().check(matches(isDisplayed()));
        editingNewsScreenElements.getPublicationDateName().check(matches(isDisplayed()));
        editingNewsScreenElements.getTimeName().check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    @Step("Проверка заполненности полей данными")
    public void checkingWhetherTheFieldsAreFilledWithData(
            String category, String titleTextNews, String publishDate, String time, String description) {
        editingNewsScreenElements.getCategoryField().check(matches(withText(category))).check(matches(isDisplayed()));
        editingNewsScreenElements.getTitleTextNewsField().check(matches(withText(titleTextNews))).check(matches(isDisplayed()));
        editingNewsScreenElements.getPublishDateField().check(matches(withText(publishDate))).check(matches(isDisplayed()));
        editingNewsScreenElements.getTimeField().check(matches(withText(time))).check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionField().check(matches(withText(description))).check(matches(isDisplayed()));
    }

    @Step("Проверка начальных данных новости с данными заполнения и конечными")
    public void checkingTheInitialDataOfTheNewsWithTheFillingDataAndTheFinal(
            String nameNewsItWas, String nameNewsInput, String publicationDateNewsItWas, String publicationDateNewsInput,
            String creationDateNewsItWas, String timeNewsInput, String descriptionNewsItWas, String descriptionNewsInput,
            String nameNewsItWasHasBecomes, String publicationDateNewsItWasHasBecomes, String creationDateNewsItWasHasBecomes,
            String descriptionNewsItWasHasBecomes) {
        assertNotEquals(nameNewsItWas, nameNewsInput);
        if (publicationDateNewsItWas.equals(publicationDateNewsInput)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsInput);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsInput);
        }
        if (creationDateNewsItWas.equals(timeNewsInput)) {
            assertEquals(creationDateNewsItWas, timeNewsInput);
        } else {
            assertNotEquals(creationDateNewsItWas, timeNewsInput);
        }
        if (descriptionNewsItWas.equals(descriptionNewsInput)) {
            assertEquals(descriptionNewsItWas, descriptionNewsInput);
        } else {
            assertNotEquals(descriptionNewsItWas, descriptionNewsInput);
        }

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
        assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Step("Проверка данных новости до редактирования и после отмены редактирования")
    public void checkingNewsDataBeforeEditingAndAfterCancelingEditing(
            String nameNewsItWas, String nameNewsInput, String publicationDateNewsItWas, String publicationDateNewsInput,
            String creationDateNewsItWas, String timeNewsInput, String descriptionNewsItWas, String descriptionNewsInput,
            String nameNewsItWasHasBecomes, String publicationDateNewsItWasHasBecomes, String creationDateNewsItWasHasBecomes,
            String descriptionNewsItWasHasBecomes) {
        assertNotEquals(nameNewsItWas , nameNewsInput);
        if (publicationDateNewsItWas.equals(publicationDateNewsInput)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsInput);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsInput);
        }
        if (creationDateNewsItWas.equals(timeNewsInput)) {
            assertEquals(creationDateNewsItWas, timeNewsInput);
        } else {
            assertNotEquals(descriptionNewsItWas, descriptionNewsInput);
        }

        assertEquals(nameNewsItWas , nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    public String categoryField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_category_text_auto_complete_text_view))));
    }

    public String titleTextNewsField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_title_text_input_edit_text))));
    }

    public String publishDateField() {
        return Helper.Text.getText(onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text))));
    }

    public String timeField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text))));
    }

    public String descriptionField() {
        return Helper.Text.getText(onView(
                allOf(withId(R.id.news_item_description_text_input_edit_text))));
    }

    public String nameNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String publicationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)));
    }

    public String creationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)));
    }

    public String descriptionNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }

    public ViewInteraction statusNews(String nameNewsItWas) {
        return onView(allOf(withId(R.id.news_item_published_text_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas))))))));
    }

    public String statusNewsText(String nameNewsItWas) {
        return Helper.Text.getText(onView(allOf(withId(R.id.news_item_published_text_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))));
    }

    public ViewInteraction statusNewsPosition(int position) {
        return onView(allOf(withIndex(withId(R.id.news_item_published_text_view), position)));
    }
}
