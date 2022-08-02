package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.EditingNewsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class EditingNewsScreenStep {

    EditingNewsScreenElements editingNewsScreenElements = new EditingNewsScreenElements();

    public void deletingTheNewsTitle() {
        Allure.step("Удаление названия новости");
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
    }

    public void clickingOnTheCategoryField() {
        Allure.step("Нажатие на поле Category");
        editingNewsScreenElements.getCategoryField().perform(replaceText(""), click()).perform(closeSoftKeyboard());
    }

    public void clickingOnThePublicationDateField() {
        Allure.step("Нажатие на поле Publication date");
        editingNewsScreenElements.getPublishDateField().perform(scrollTo(), click());
    }

    public void clickingOnTheTimeField() {
        Allure.step("Нажатие на поле Time");
        editingNewsScreenElements.getTimeField().perform(click());
    }

    public void clickingOnTheSaveButton() {
        Allure.step("Нажатие на кнопку сохранить");
        editingNewsScreenElements.getSaveButton().perform(click());
    }

    public void clickingOnTheCancelNewsEditingButton() {
        Allure.step("Нажатие на кнопку отмены редактирования новости");
        editingNewsScreenElements.getCancelButton().perform(click());
    }

    public void clickingOnTheCancelButtonToExitEditing() {
        Allure.step("Нажатие на кнопку отмены выхода из редактирования");
        editingNewsScreenElements.getCancelButton2().perform(click());
    }

    public void clickingOnTheButtonToConfirmTheCancellationOfNewsEditing() {
        Allure.step("Нажатие на кнопку подтверждения отмены редактирования новости");
        editingNewsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    public void clickingOnTheCheckBox() {
        Allure.step("Нажатие на Чек бокс");
        editingNewsScreenElements.getCheckBox().perform(click());
    }

    public void clickingOnTheButtonToEnterTheNewsEditing(String nameNewsItWas) {
        Allure.step("Нажатие на кнопку для входа в редактирование новости");
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
    }

    public void clickingOnTheNews(String nameNewsItWas) {
        Allure.step("Нажатие на новость");
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
    }

    public void enteringTextInTheCategoryField(String text) {
        Allure.step("Ввод текста в поле Category");
        editingNewsScreenElements.getCategoryField().perform(replaceText(text));
    }

    public void invalidLanguage(String title, String description) {
        Allure.step("Ввод невалидного языка");
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText("")).perform(closeSoftKeyboard());
        editingNewsScreenElements.getDescriptionField().perform(replaceText("")).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        editingNewsScreenElements.getTitleTextNewsField().perform(typeText(title)).perform(closeSoftKeyboard());
        editingNewsScreenElements.getDescriptionField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    public void validLanguage(String title, String category, String description) {
        Allure.step("Ввод валидного языка");
        editingNewsScreenElements.getTitleTextNewsField().perform(replaceText(title));
        editingNewsScreenElements.getCategoryField().perform(replaceText(category));
        editingNewsScreenElements.getDescriptionField().perform(replaceText(description)).perform(closeSoftKeyboard());
    }

    public void fillingInTheNewsFieldsWithNewData(String text, String Category) {
        Allure.step("Заполнение полей новости новыми данными");
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

    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        Allure.step("Проверка на присудствие в полях слов из английских букв");
        editingNewsScreenElements.getTitleTextNewsField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingNewsScreenElements.getCategoryField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
    }

    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        Allure.step("Проверка на отсутствие в полях слов из русских букв");
        editingNewsScreenElements.getTitleTextNewsField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionField().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    public void checkingTheNameOfTheEditingNewsScreen() {
        Allure.step("Проверка названия экрана Editing News");
        editingNewsScreenElements.getEditingNameScreen().check(matches(withText("Editing"))).check(matches(isDisplayed()));
        editingNewsScreenElements.getNewsNameScreen().check(matches(withText("News"))).check(matches(isDisplayed()));
    }

    public void checkNameFieldInEditingNews() {
        Allure.step("Проверка идентифицирующих названий полей");
        editingNewsScreenElements.getCategoryName().check(matches(isDisplayed()));
        editingNewsScreenElements.getTitleName().check(matches(isDisplayed()));
        editingNewsScreenElements.getPublicationDateName().check(matches(isDisplayed()));
        editingNewsScreenElements.getTimeName().check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    public void checkingWhetherTheFieldsAreFilledWithData(
            String category, String titleTextNews, String publishDate, String time, String description) {
        Allure.step("Проверка заполненности полей данными");
        editingNewsScreenElements.getCategoryField().check(matches(withText(category))).check(matches(isDisplayed()));
        editingNewsScreenElements.getTitleTextNewsField().check(matches(withText(titleTextNews))).check(matches(isDisplayed()));
        editingNewsScreenElements.getPublishDateField().check(matches(withText(publishDate))).check(matches(isDisplayed()));
        editingNewsScreenElements.getTimeField().check(matches(withText(time))).check(matches(isDisplayed()));
        editingNewsScreenElements.getDescriptionField().check(matches(withText(description))).check(matches(isDisplayed()));
    }

    public void checkingTheInitialDataOfTheNewsWithTheFillingDataAndTheFinal(
            String nameNewsItWas, String nameNewsInput, String publicationDateNewsItWas, String publicationDateNewsInput,
            String creationDateNewsItWas, String timeNewsInput, String descriptionNewsItWas, String descriptionNewsInput,
            String nameNewsItWasHasBecomes, String publicationDateNewsItWasHasBecomes, String creationDateNewsItWasHasBecomes,
            String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка начальных данных новости с данными заполнения и конечными");
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

    public void checkingNewsDataBeforeEditingAndAfterCancelingEditing(
            String nameNewsItWas, String nameNewsInput, String publicationDateNewsItWas, String publicationDateNewsInput,
            String creationDateNewsItWas, String timeNewsInput, String descriptionNewsItWas, String descriptionNewsInput,
            String nameNewsItWasHasBecomes, String publicationDateNewsItWasHasBecomes, String creationDateNewsItWasHasBecomes,
            String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка данных новости до редактирования и после отмены редактирования");
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

    public void checkingTheCalendarAppearance(@NonNull AppActivity activity) {
        Allure.step("Проверка появления календаря");
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    public void checkingTheAppearanceOfTheDropDownList(@NonNull AppActivity activity) {
        Allure.step("Проверка появления выпадающего списка");
        onView(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    public void checkingTheAppearanceOfClockOfTheArrowType(@NonNull AppActivity activity) {
        Allure.step("Проверка появления часов стрелочного типа");
        onView(withClassName(is("android.widget.RadialTimePickerView")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    public void checkingTheFillEmptyFields(@NonNull AppActivity activity, int text) {
        Allure.step("Проверка появления предупреждающего сообщения Fill empty fields");
        onView(withText(text))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(withText("Fill empty fields"))).check(matches(isDisplayed()));
    }

    public void checkingTheSavingFailedTryAgainLater(@NonNull AppActivity activity, int text) {
        Allure.step("Проверка появления предупреждающего сообщения Saving failed. Try again later");
        onView(withText(text))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(withText("Saving failed. Try again later.")));
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
