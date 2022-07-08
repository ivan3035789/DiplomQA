package androidTest.java.ru.iteco.fmhandroid.ui.step;

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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Search.textSearchClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.createClaim;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.action.ViewActions;

import org.hamcrest.Matchers;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CreatingClaimsScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CreatingClaimsScreenStep {

    CreatingClaimsScreenElements creatingClaimsScreenElements = new CreatingClaimsScreenElements();

    @Step("Нажатие на кнопку сохранения")
    public void clickingOnTheSaveButton() {
        creatingClaimsScreenElements.getSaveButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены выхода из создания претензии")
    public void clickingOnTheCancelButtonToExitTheClaimCreation() {
        creatingClaimsScreenElements.getCancelButtonInWindow().perform(click());
    }

    @Step("Нажатие на кнопку отмены создания претензии")
    public void clickingOnTheCancelClaimCreationButton() {
        creatingClaimsScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на кнопку подтверждения отмены создания претензии")
    public void clickingOnTheButtonToConfirmTheCancellationOfTheClaimCreation() {
        creatingClaimsScreenElements.getOkButton().perform(click());
    }

    @Step("Ввод текста 51 символ")
    public void textInput(String invalidText) {
        creatingClaimsScreenElements.getTitleClaimField().perform(typeText(invalidText));
    }

    @Step("Нажатие на поле Executor")
    public void clickingOnTheExecutorField() {
        creatingClaimsScreenElements.getExecutorClaimField().perform(click()).perform(ViewActions.closeSoftKeyboard());
    }

    @Step("Нажатие на поле дата")
    public void clickingOnTheDateField() {
        creatingClaimsScreenElements.getDateClaimField().perform(click());
    }

    @Step("Нажатие на поле время")
    public void clickingOnTheTimeField() {
        creatingClaimsScreenElements.getTimeClaimField().perform(click());
    }

    @Step("Ввод невалидного языка")
    public void invalidLanguage(String title, String executed, String description) {
        creatingClaimsScreenElements.getTitleClaimField().perform(typeText(title));
        creatingClaimsScreenElements.getExecutorClaimField().perform(typeText(executed));
        creatingClaimsScreenElements.getDescriptionClaimField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    @Step("Ввод валидного языка")
    public void validLanguage(String title, String executed, String description) {
        creatingClaimsScreenElements.getTitleClaimField().perform(typeText(title));
        creatingClaimsScreenElements.getExecutorClaimField().perform(typeText(executed));
        creatingClaimsScreenElements.getDescriptionClaimField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    @Step("Заполнение полей валидными данными")
    public void fillingInFieldsWithValidData(String titleText, String randomExecutor) {
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

    @Step("Заполнение полей валидными данными для создания претензии с открытым статусом")
    public void fillingInTheFieldsWithValidDataToCreateClaimWithAnOpenStatus(String titleText) {
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

    @Step("Удаление названия")
    public void nameDeletion() {
        creatingClaimsScreenElements.getTitleClaimField().perform(replaceText(""));
    }

    @Step("Поиск созданной претензии")
    public void searchForCreatedClaim(String titleText) {
        textSearchClaims(titleText);
    }

    @Step("Проверка данных созданной претензии и найденной")
    public void checkingTheDataOfTheCreatedClaimAndTheFoundOne(
            String title, String title2, String executor, String executor2, String date, String date2, String time, String time2,
            String description, String description2) {
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

    @Step("Проверка на отсутствие в полях слов из русских букв")
    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        creatingClaimsScreenElements.getTitleClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getExecutorClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    @Step("Проверка на присудствие в полях слов из английских букв")
    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        creatingClaimsScreenElements.getTitleClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getExecutorClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
    }

    @Step("Проверка выставленной даты с датой в поле ")
    public void checkingTheSetDateWithTheDateInTheField(String dateField, String today) {
        creatingClaimsScreenElements.getDateClaimField().check(matches(withText(dateField))).check(matches(isDisplayed()));
        assertEquals(today, dateField);
    }

    @Step("Проверка данных и статуса созданной претензии и найденной")
    public void checkingTheDataAndStatusOfTheClaimCreatedAndFound(
            String title, String titleOnCaredClaims, String date, String dateOnCaredClaims, String time, String timeOnCaredClaims,
            String description, String descriptionOnCaredClaims) {
        ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();

        assertEquals(title, titleOnCaredClaims);
        assertEquals(date, dateOnCaredClaims);
        assertEquals(time, timeOnCaredClaims);
        assertEquals(description, descriptionOnCaredClaims);

        claimsScreenStep.checkingTheOpenStatus();
    }

    @Step("Проверка идентифицирующих названий полей для заполнения")
    public void checkNameFieldInCreatingClaims() {
        creatingClaimsScreenElements.getTitleName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getExecutorName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDateName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getTimeName().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    @Step("Проверка количества введенных символов и  символов в поле ")
    public void checkingTheNumberOfCharactersEnteredAndCharactersInTheField() {
        String text = Helper.Text.getText(onView(withId(R.id.title_edit_text)));
        int textLength = text.length();
        assertEquals(50, textLength);
    }

    @Step("Проверка названия экрана создания претензии creating Claims")
    public void checkingTheNameOfTheClaimCreationScreen() {
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(withText("Creating"))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(withText("Claims"))).check(matches(isDisplayed()));
    }

    @Step("Проверка отсутствия установленного года в поле")
    public void checkingTheAbsenceOfSetYear(String year) {
        creatingClaimsScreenElements.getDateClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getDateClaimField().check(matches(Matchers.not(withText(year)))).check(matches(isDisplayed()));
    }

    @Step("Проверка появления календаря")
    public void checkingTheCalendarAppearance(@NonNull AppActivity activity) {
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Step("Проверка появления выпадающего списка")
    public void checkingTheAppearanceOfTheDropDownList(@NonNull AppActivity activity) {
        onView(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Step("Проверка появления часов стрелочного типа")
    public void checkingTheAppearanceOfClockOfTheArrowType(@NonNull AppActivity activity) {
        onView(withClassName(is("android.widget.RadialTimePickerView")))
            .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Step("Проверка появления предупреждающего сообщения Fill empty fields")
    public void checkingTheFillEmptyFields(@NonNull AppActivity activity, int text) {
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
