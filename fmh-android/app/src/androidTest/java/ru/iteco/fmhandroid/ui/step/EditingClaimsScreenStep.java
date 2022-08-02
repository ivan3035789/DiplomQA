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
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomExecutor;
import static ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.EditingClaimsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class EditingClaimsScreenStep {

    EditingClaimsScreenElements editingClaimsScreenElements = new EditingClaimsScreenElements();

    public void goToTheClaimCardWithTheOpenStatus() {
        Allure.step("Переход к карточке Claim со статусом Open");
        ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
        FilteringWindowScreenElements filteringWindowScreenElements = new FilteringWindowScreenElements();
        MainScreenStep mainScreenStep = new MainScreenStep();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenElements.getFilteringButton().perform(click());
        filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
        filteringWindowScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(randomClaims(0, 1, 2, 3), click()));
    }

    public void clickingOnTheSaveButton() {
        Allure.step("Нажатие на кнопку сохранить");
        editingClaimsScreenElements.getSaveButton().perform(click());
    }

    public void clickingOnTheUndoEditButton() {
        Allure.step("Нажатие на кнопку отмены редактирования");
        editingClaimsScreenElements.getCancelButton().perform(click());
    }

    public void clickingOnRandomlySelectedArtist(@NonNull AppActivity activity, String executor) {
        Allure.step("Нажатие на исполнителя выбранного случайным образом");
        onView(withText(executor))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
    }

    public void clickingOnTheCancelButtonToExitEditingEditing() {
        Allure.step("Нажатие на кнопку  отмены выхода из редактирования редактирования");
        editingClaimsScreenElements.getCancelButton2().perform(scrollTo(), click());
    }

    public void clickingOnTheButtonToConfirmTheCancellationOfEditing() {
        Allure.step("Нажатие на кнопку подтверждения отмены редактирования");
        editingClaimsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    public void deletingTextFromTheTitleField() {
        Allure.step("Удаление текста из поля Title");
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(""), closeSoftKeyboard());
    }

    public void clickingOnTheExecutorField() {
        Allure.step("Нажатие на поле Executor");
        editingClaimsScreenElements.getExecutorClaimField().perform(click(), closeSoftKeyboard());
    }

    public void clickingOnTheDateField() {
        Allure.step("Нажатие на поле Date");
        editingClaimsScreenElements.getDateClaimField().perform(click());
    }

    public void clickingOnTheTimeField() {
        Allure.step("Нажатие на поле Time");
        editingClaimsScreenElements.getTimeClaimField().perform(click());
    }

    public void invalidLanguage(String title, String executed, String description) {
        Allure.step("Ввод невалидного языка");
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(""));
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(""));
        editingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(""));
        editingClaimsScreenElements.getTitleClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        editingClaimsScreenElements.getExecutorClaimField().perform(typeText(executed)).perform(closeSoftKeyboard());
        editingClaimsScreenElements.getDescriptionClaimField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    public void validLanguage(String title, String executed, String description) {
        Allure.step("Ввод валидного языка");
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(title));
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(executed));
        editingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(description)).perform(closeSoftKeyboard());
    }

    public void fillingInFieldsWithValidData() {
        Allure.step("Заполнение полей валидными данными");
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(textSymbol(5)), closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(randomExecutor()), closeSoftKeyboard());
        SystemClock.sleep(2000);
        editingClaimsScreenElements.getDateClaimField().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        editingClaimsScreenElements.getTimeClaimField().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        editingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(textSymbol(5)), closeSoftKeyboard());
    }

    public void fillingInTheExecutorField(String text) {
        Allure.step("Заполнение поля Executor");
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(text));
    }

    public void checkNameFieldInEditingClaims() {
        Allure.step("Проверка идентифицирующих названий полей");
        editingClaimsScreenElements.getTitleName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDateName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getTimeName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    public void checkingTheFieldsAreFilledIn() {
        Allure.step("Проверка Заполнения Полей");
        editingClaimsScreenElements.getTitleClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDateClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getTimeClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionClaimField().check(matches(isDisplayed()));
    }

    public void checkingTheNameOfTheScreenForEditingClaims() {
        Allure.step("Проверка названия экрана для редактирования претензий");
        editingClaimsScreenElements.getEditingName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getClaimsName().check(matches(isDisplayed()));
    }

    public void checkingDataBeforeEditingAndAfter(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome,
            String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome, String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome,
            String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome) {
        Allure.step("Проверка данных до редактирования и после");
        assertEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        if (dateClaimFieldItWas.equals(dateClaimFieldItWasHasBecome)) {
            assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        }
        assertNotEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertNotEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    public void comparisonOfDataBeforeAndAfterEditing(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas,
            String executorClaimFieldItWasHasBecome, String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome,
            String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome, String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome) {
        assertNotEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        if (dateClaimFieldItWas.equals(dateClaimFieldItWasHasBecome)) {
            Allure.step("Сравнение данных до редактирования и после");
            assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        }
        assertNotEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertNotEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    public void verificationOfClaimDataBeforeEditingAndAfterCancellationOfEditing(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome,
            String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome, String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome,
            String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome
    ) {
        Allure.step("Проверка данных претензии до редактирования и после отмены редактирования");
        assertEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        assertEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        Allure.step("Проверка на присудствие в полях слов из английских букв");
        editingClaimsScreenElements.getTitleClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
    }

    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        Allure.step("Проверка на отсутствие в полях слов из русских букв");
        editingClaimsScreenElements.getTitleClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    public void checkingThePerformerBeforeEditingAndAfter(
            String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome, String executorClaimFieldInputText) {
        Allure.step("Проверка исполнителя до редактирования и после");
        ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
        if (executorClaimFieldItWas.equals("NOT ASSIGNED")) {
            assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(executorClaimFieldInputText, executorClaimFieldItWasHasBecome);
        }
        claimsScreenElements.getExecutorText().check(matches(isDisplayed()));
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

    public String titleClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.title_text_view), 0)));
    }

    public String executorClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_name_text_view), 0)));
    }

    public String executorClaim() {
        return  Helper.Text.getText(editingClaimsScreenElements.getExecutorClaimField());
    }

    public String dateClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plane_date_text_view), 0)));
    }

    public String timeClaimField() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plan_time_text_view), 0)));
    }

    public String descriptionClaimField() {
        return Helper.Text.getText(onView(withId(R.id.description_text_view)));
    }
}
