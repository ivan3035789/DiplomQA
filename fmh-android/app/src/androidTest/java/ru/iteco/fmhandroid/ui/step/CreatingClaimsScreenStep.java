package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Search.textSearchClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.createClaim;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.action.ViewActions;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.CreatingClaimsScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CreatingClaimsScreenStep {

    CreatingClaimsScreenElements creatingClaimsScreenElements = new CreatingClaimsScreenElements();

    public void clickingOnTheSaveButton() {
        Allure.step("Нажатие на кнопку сохранения");
        creatingClaimsScreenElements.getSaveButton().perform(click());
    }

    public void clickingOnTheCancelButtonToExitTheClaimCreation() {
        Allure.step("Нажатие на кнопку отмены выхода из создания претензии");
        creatingClaimsScreenElements.getCancelButtonInWindow().perform(click());
    }

    public void clickingOnTheCancelClaimCreationButton() {
        Allure.step("Нажатие на кнопку отмены создания претензии");
        creatingClaimsScreenElements.getCancelButton().perform(click());
    }

    public void clickingOnTheButtonToConfirmTheCancellationOfTheClaimCreation() {
        Allure.step("Нажатие на кнопку подтверждения отмены создания претензии");
        creatingClaimsScreenElements.getOkButton().perform(click());
    }

    public void textInput(String invalidText) {
        Allure.step("Ввод текста 51 символ");
        creatingClaimsScreenElements.getTitleClaimField().perform(typeText(invalidText));
    }

    public void clickingOnTheExecutorField() {
        Allure.step("Нажатие на поле Executor");
        creatingClaimsScreenElements.getExecutorClaimField().perform(click()).perform(ViewActions.closeSoftKeyboard());
    }

    public void clickingOnTheDateField() {
        Allure.step("Нажатие на поле дата");
        creatingClaimsScreenElements.getDateClaimField().perform(click());
    }

    public void clickingOnTheTimeField() {
        Allure.step("Нажатие на поле время");
        creatingClaimsScreenElements.getTimeClaimField().perform(click());
    }

    public void invalidLanguage(String title, String executed, String description) {
        Allure.step("Ввод невалидного языка");
        creatingClaimsScreenElements.getTitleClaimField().perform(typeText(title));
        creatingClaimsScreenElements.getExecutorClaimField().perform(typeText(executed));
        creatingClaimsScreenElements.getDescriptionClaimField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    public void validLanguage(String title, String executed, String description) {
        Allure.step("Ввод валидного языка");
        creatingClaimsScreenElements.getTitleClaimField().perform(typeText(title));
        creatingClaimsScreenElements.getExecutorClaimField().perform(typeText(executed));
        creatingClaimsScreenElements.getDescriptionClaimField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    public void fillingInFieldsWithValidData(String titleText, String randomExecutor) {
        Allure.step("Заполнение полей валидными данными");
        WatchScreenStep watchScreenStep = new WatchScreenStep();
        CalendarScreenStep calendarScreenStep = new CalendarScreenStep();

        creatingClaimsScreenElements.getTitleClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getExecutorClaimField().perform(replaceText(randomExecutor)).perform(closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getDateClaimField().perform(click());
        watchScreenStep.clickingOnTheConfirmationButton();
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getTimeClaimField().perform(click());
        calendarScreenStep.clickingOnTheConfirmButton();
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
    }

    public void fillingInTheFieldsWithValidDataToCreateClaimWithAnOpenStatus(String titleText) {
        Allure.step("Заполнение полей валидными данными для создания претензии с открытым статусом");
        WatchScreenStep watchScreenStep = new WatchScreenStep();
        CalendarScreenStep calendarScreenStep = new CalendarScreenStep();

        creatingClaimsScreenElements.getTitleClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getDateClaimField().perform(click());
        watchScreenStep.clickingOnTheConfirmationButton();
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getTimeClaimField().perform(click());
        calendarScreenStep.clickingOnTheConfirmButton();
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(titleText), closeSoftKeyboard());
    }

    public void nameDeletion() {
        Allure.step("Удаление названия");
        creatingClaimsScreenElements.getTitleClaimField().perform(replaceText(""));
    }

    public void searchForCreatedClaim(String titleText) {
        Allure.step("Поиск созданной претензии");
        textSearchClaims(titleText);
    }

    public void checkingTheDataOfTheCreatedClaimAndTheFoundOne(
            String title, String title2, String executor, String executor2, String date, String date2, String time, String time2,
            String description, String description2) {
        Allure.step("Проверка данных созданной претензии и найденной");
        assertEquals(title, title2);
        if (executor.equals("NOT ASSIGNED")) {
            assertEquals(executor, executor2);
        } else {
            onView(withId(R.id.executor_name_text_view)).check(matches(withText("NOT ASSIGNED"))).check(matches(isDisplayed()));
        }
        assertEquals(date, date2);
        assertEquals(time, time2);
        assertEquals(description.trim(), description2.trim());
    }

    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        Allure.step("Проверка на отсутствие в полях слов из русских букв");
        creatingClaimsScreenElements.getTitleClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getExecutorClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        Allure.step("Проверка на присудствие в полях слов из английских букв");
        creatingClaimsScreenElements.getTitleClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getExecutorClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
    }

    public void checkingTheSetDateWithTheDateInTheField(String dateField, String today) {
        Allure.step("Проверка выставленной даты с датой в поле");
        creatingClaimsScreenElements.getDateClaimField().check(matches(withText(dateField))).check(matches(isDisplayed()));
        assertEquals(today, dateField);
    }

    public void checkingTheDataAndStatusOfTheClaimCreatedAndFound(
            String title, String titleOnCaredClaims, String date, String dateOnCaredClaims, String time, String timeOnCaredClaims,
            String description, String descriptionOnCaredClaims) {
        Allure.step("Проверка данных и статуса созданной претензии и найденной");
        ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();

        assertEquals(title, titleOnCaredClaims);
        assertEquals(date, dateOnCaredClaims);
        assertEquals(time, timeOnCaredClaims);
        assertEquals(description, descriptionOnCaredClaims);

        claimsScreenStep.checkingTheOpenStatus();
    }

    public void checkNameFieldInCreatingClaims() {
        Allure.step("Проверка идентифицирующих названий полей для заполнения");
        creatingClaimsScreenElements.getTitleName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getExecutorName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDateName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getTimeName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    public void checkingTheNumberOfCharactersEnteredAndCharactersInTheField() {
        Allure.step("Проверка количества введенных символов и  символов в поле");
        String text = Helper.Text.getText(onView(withId(R.id.title_edit_text)));
        int textLength = text.length();
        assertEquals(50, textLength);
    }

    public void checkingTheNameOfTheClaimCreationScreen() {
        Allure.step("Проверка названия экрана создания претензии creating Claims");
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(withText("Creating"))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(withText("Claims"))).check(matches(isDisplayed()));
    }

    public void checkingTheAbsenceOfSetYear() {
        Allure.step("Проверка отсутствия установленного года в поле");
//        onView(allOf(withId(R.id.date_in_plan_text_input_edit_text), withText(""))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDateClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
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

    public void createClaimStatusOpenSetUp() {
        createClaim();
    }

    public String title() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.title_edit_text), 0)));
    }

    public String executor() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_drop_menu_auto_complete_text_view), 0)));
    }

    public String dateFromTheField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.date_in_plan_text_input_edit_text), 0)));
    }

    public String time() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.time_in_plan_text_input_edit_text), 0)));
    }

    public String description() {
        return Helper.Text.getText(onView(withId(R.id.description_edit_text)));
    }

    public String titleOnCaredClaims() {
        return Helper.Text.getText(onView(withId(R.id.title_text_view)));
    }

    public String executorOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_name_text_view), 0)));
    }

    public String dateOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plane_date_text_view), 0)));
    }

    public String timeOnCaredClaims() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plan_time_text_view), 0)));
    }

    public String descriptionOnCaredClaims() {
        return Helper.Text.getText(onView(withId(R.id.description_text_view)));
    }
}
