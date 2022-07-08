package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomExecutor;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.EditingClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class EditingClaimsScreenStep {

    EditingClaimsScreenElements editingClaimsScreenElements = new EditingClaimsScreenElements();

    @Step("Переход к карточке Claim со статусом Open")
    public void goToTheClaimCardWithTheOpenStatus() {
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

    @Step("Нажатие на кнопку сохранить")
    public void clickingOnTheSaveButton() {
        editingClaimsScreenElements.getSaveButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены редактирования")
    public void clickingOnTheUndoEditButton() {
        editingClaimsScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на кнопку  отмены выхода из редактирования редактирования")
    public void clickingOnTheCancelButtonToExitEditingEditing() {
        editingClaimsScreenElements.getCancelButton2().perform(scrollTo(), click());
    }

    @Step("Нажатие на кнопку подтверждения отмены редактирования")
    public void clickingOnTheButtonToConfirmTheCancellationOfEditing() {
        editingClaimsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    @Step("Удаление текста из поля Title")
    public void deletingTextFromTheTitleField() {
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(""), closeSoftKeyboard());
    }

    @Step("Нажатие на поле Executor")
    public void clickingOnTheExecutorField() {
        editingClaimsScreenElements.getExecutorClaimField().perform(click(), closeSoftKeyboard());
    }

    @Step("Нажатие на поле Date")
    public void clickingOnTheDateField() {
        editingClaimsScreenElements.getDateClaimField().perform(click());
    }

    @Step("Нажатие на поле Time")
    public void clickingOnTheTimeField() {
        editingClaimsScreenElements.getTimeClaimField().perform(click());
    }

    @Step("Ввод невалидного языка")
    public void invalidLanguage(String title, String executed, String description) {
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(""));
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(""));
        editingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(""));
        editingClaimsScreenElements.getTitleClaimField().perform(typeText(title)).perform(closeSoftKeyboard());
        editingClaimsScreenElements.getExecutorClaimField().perform(typeText(executed)).perform(closeSoftKeyboard());
        editingClaimsScreenElements.getDescriptionClaimField().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    @Step("Ввод валидного языка")
    public void validLanguage(String title, String executed, String description) {
        editingClaimsScreenElements.getTitleClaimField().perform(replaceText(title));
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(executed));
        editingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(description)).perform(closeSoftKeyboard());
    }

    @Step("Заполнение полей валидными данными")
    public void fillingInFieldsWithValidData() {
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

    @Step("Заполнение поля Executor")
    public void fillingInTheExecutorField(String text) {
        editingClaimsScreenElements.getExecutorClaimField().perform(replaceText(text));
    }

    @Step("Проверка идентифицирующих названий полей")
    public void checkNameFieldInEditingClaims() {
        editingClaimsScreenElements.getTitleName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDateName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getTimeName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    @Step("Проверка Заполнения Полей")
    public void checkingTheFieldsAreFilledIn() {
        editingClaimsScreenElements.getTitleClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDateClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getTimeClaimField().check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionClaimField().check(matches(isDisplayed()));
    }

    @Step("Проверка названия экрана для редактирования претензий")
    public void checkingTheNameOfTheScreenForEditingClaims() {
        editingClaimsScreenElements.getEditingName().check(matches(isDisplayed()));
        editingClaimsScreenElements.getClaimsName().check(matches(isDisplayed()));
    }

    @Step("Проверка данных до редактирования и после")
    public void checkingDataBeforeEditingAndAfter(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome,
            String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome, String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome,
            String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome) {
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

    @Step("Сравнение данных до редактирования и после")
    public void comparisonOfDataBeforeAndAfterEditing(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas,
            String executorClaimFieldItWasHasBecome, String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome,
            String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome, String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome) {
        assertNotEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        if (dateClaimFieldItWas.equals(dateClaimFieldItWasHasBecome)) {
            assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        }
        assertNotEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertNotEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Step("Проверка данных претензии до редактирования и после отмены редактирования")
    public void verificationOfClaimDataBeforeEditingAndAfterCancellationOfEditing(
            String titleClaimFieldItWas, String titleClaimFieldItWasHasBecome, String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome,
            String dateClaimFieldItWas, String dateClaimFieldItWasHasBecome, String timeClaimFieldItWas, String timeClaimFieldItWasHasBecome,
            String descriptionClaimFieldItWas, String descriptionClaimFieldItWasHasBecome
    ) {
        assertEquals(titleClaimFieldItWas, titleClaimFieldItWasHasBecome);
        assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        assertEquals(dateClaimFieldItWas, dateClaimFieldItWasHasBecome);
        assertEquals(timeClaimFieldItWas, timeClaimFieldItWasHasBecome);
        assertEquals(descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Step("Проверка на присудствие в полях слов из английских букв")
    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String validLanguageText) {
        editingClaimsScreenElements.getTitleClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(validLanguageText))).check(matches(isDisplayed()));
    }

    @Step("Проверка на отсутствие в полях слов из русских букв")
    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields() {
        editingClaimsScreenElements.getTitleClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getExecutorClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
        editingClaimsScreenElements.getDescriptionClaimField().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    @Step("Проверка исполнителя до редактирования и после")
    public void checkingThePerformerBeforeEditingAndAfter(
            String executorClaimFieldItWas, String executorClaimFieldItWasHasBecome, String executorClaimFieldInputText) {
        ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
        if (executorClaimFieldItWas.equals("NOT ASSIGNED")) {
            assertEquals(executorClaimFieldItWas, executorClaimFieldItWasHasBecome);
        } else {
            assertNotEquals(executorClaimFieldInputText, executorClaimFieldItWasHasBecome);
        }
        claimsScreenElements.getExecutorText().check(matches(isDisplayed()));
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
