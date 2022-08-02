package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.CreatingNewsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CreatingNewsScreenStep {

    CreatingNewsScreenElements creatingNewsScreenElements = new CreatingNewsScreenElements();

    public void clickingOnTheCategoryField() {
        Allure.step("Нажатие на поле категория");
        creatingNewsScreenElements.getCategoryFieldNews().perform(click()).perform(closeSoftKeyboard());
    }

    public void clickingOnTheDateField() {
        Allure.step("Нажатие на поле дата");
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
    }

    public void clickingOnTheTimeField() {
        Allure.step("Нажатие на поле время");
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
    }

    public void clickingOnTheExitButtonFromNewsCreation() {
        Allure.step("Нажатие на кнопку выхода из создания новости");
        creatingNewsScreenElements.getCancelButton().perform(click());
    }

    public void clickingOnTheConfirmationButtonToExitTheNewsCreation() {
        Allure.step("Нажатие на кнопку подтверждения выхода из создания новости");
        creatingNewsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    public void clickingOnTheSaveNewsButton() {
        Allure.step("Нажатие на кнопку сохранения новости");
        creatingNewsScreenElements.getSaveButton().perform(click());
    }

    public void clickingOnTheCancelNewsCreationButton() {
        Allure.step("Нажатие на кнопку отмены создания новости");
        creatingNewsScreenElements.getCancelButton2().perform(scrollTo(), click());
    }

    public void clickingOnTheConfirmationButtonToCancelTheCreationOfTheNews() {
        Allure.step("Нажатие на кнопку подтверждения отмены создания новости");
        creatingNewsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    public void validLanguage(String category, String title, String description) {
        Allure.step("Ввод валидного языка");
        creatingNewsScreenElements.getTitleFieldNews().perform(typeText(title));
        creatingNewsScreenElements.getCategoryFieldNews().perform(typeText(category));
        creatingNewsScreenElements.getDescriptionFieldNews().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    public void invalidLanguage(String title, String description) {
        Allure.step("Ввод невалидного языка");
        creatingNewsScreenElements.getTitleFieldNews().perform(typeText(title));
        creatingNewsScreenElements.getDescriptionFieldNews().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    public void fillingInFieldsWithValidData(String text) {
        Allure.step("Заполнение полей валидными данными");
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenStep calendarScreenStep = new CalendarScreenStep();

        creatingNewsScreenElements.getCategoryFieldNews().perform(replaceText(randomCategory())).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getTitleFieldNews().perform(replaceText(textSymbol(5)), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
        calendarScreenStep.clickingOnTheConfirmButton();
        creatingNewsScreenElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
    }

    public void fillingInTheCategoryField(String text) {
        Allure.step("Заполнение поля Category");
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
                                                                      //randomCategory()
        creatingNewsScreenElements.getCategoryFieldNews().perform(replaceText(text)).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getTitleFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getDescriptionFieldNews().perform(click());
        creatingNewsScreenElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
    }

    public void choosingNews(int position) {
        Allure.step("Выбор новости");
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();
        controlPanelScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
    }

    public void checkingTheNameOfTheCreatingNewsScreen() {
        Allure.step("Проверка названия экрана Creating News");
        creatingNewsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsScreenElements.getNewsNameScreen().check(matches(isDisplayed()));
    }

    public void checkNameFieldInCreatingNews() {
        Allure.step("Проверка идентифицирующих названий полей для заполнения");
        creatingNewsScreenElements.getCategoryName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getTitleName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getPublicationDateName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getTimeName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        Allure.step("Проверка на отсутствие в полях слов из русских букв");
        creatingNewsScreenElements.getTitleFieldNews().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingNewsScreenElements.getDescriptionFieldNews().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String text) {
        Allure.step("Проверка на присудствие в полях слов из английских букв");
        creatingNewsScreenElements.getCategoryFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        creatingNewsScreenElements.getTitleFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        creatingNewsScreenElements.getDescriptionFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
    }

    public void comparingTheDataOfTheCreatedNewsWithTheDataOfTheFirstNewsFromTheList(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Сравнение данных созданной новости с данными новости первой из списка");
        assertNotEquals(nameNewsItWas, nameNewsItWasHasBecomes);

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
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    public void checkingTheDataOfTheFirstNewsFromTheListMustMatchAfterCancelingTheCreationOfTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        Allure.step("Проверка данные первой новости из списка должны совпадать после отмены создания новости");
        assertEquals(nameNewsItWas, nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
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

    public String nameNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
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
