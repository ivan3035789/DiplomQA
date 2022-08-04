package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.Search.searchForAnUncreatedClaim;
import static ru.iteco.fmhandroid.ui.data.Helper.Search.searchForAnUncreatedComment;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ClaimsScreenStep {

    ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();

    public void clickingOnTheButtonToGoToTheClaimCreationScreen() {
        Allure.step("Нажатие на кнопку перехода на экран создания претензии");
        claimsScreenElements.getCreateClaimsButton().perform(click());
    }

    public void pressingOnTheButtonToGoToTheFilteringScreen() {
        Allure.step("Нажатие на кнопку перехода на экран Filtering");
        claimsScreenElements.getFilteringButton().perform(click());
    }

    public void checkingTheStatusShouldNotBeExecuted() {
        Allure.step("Проверка статус не должен быть Executed");
        claimsScreenElements.getStatus().check(matches(not(withText("Executed"))));
    }

    public void pressingTheExitButton() {
        Allure.step("Нажатие на кнопку выхода");
        claimsScreenElements.getExitButton().perform(click());
    }

    public void clickingOnRandomlySelectedClaim(int position) {
        Allure.step("Нажатие на случайно выбранную претензию");
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(position, click()));
    }

    public void clickingOnTheCloseButton() {
        Allure.step("Нажатие на кнопку закрыть");
        claimsScreenElements.getCloseImageButton().perform(click());
    }

    public void clickingOnTheNotepadWithPencilButton() {
        Allure.step("Нажатие на кнопку блокнот с карандашом для перехода к экрану для редактирования");
        claimsScreenElements.getEditClaimsButton().perform(click());
    }

    public void clickingOnTheButtonWithTheNotepadIconWithGear() {
        Allure.step("Нажатие на кнопку с иконкой блокнот с шестеренкой");
        claimsScreenElements.getButtonChangeStatus().perform(click());
    }

    public void clickingOnTakeToWork() {
        Allure.step("Нажатие на взять в работу");
        claimsScreenElements.getTakeToWork().perform(click());
    }

    public void clickingOnReset() {
        Allure.step("Нажатие на Сбросить");
        claimsScreenElements.getThrowOff().perform(click());
    }

    public void clickingOnTheOkButtonToAddComment() {
        Allure.step("Нажатие на кнопку ок добавления комментария");
        claimsScreenElements.getOkButtonAddComment().perform(scrollTo(), click());
    }

    public void clickingOnTheAddCommentButton() {
        Allure.step("Нажатие на кнопку добавления комментария");
        claimsScreenElements.getAddComment().perform(click());
    }

    public void clickingOnTheButtonToEnterTheCommentEditingScreen() {
        Allure.step("Нажатие на кнопку для входа в экран редактирования комментария");
        claimsScreenElements.getEditCommentButton().perform(click());
    }

    public void clickingOnToExecute() {
        Allure.step("Нажатие на для выполнения");
        claimsScreenElements.getToExecute().perform(click());
    }

    public void clickingOnToCancel() {
        Allure.step("Нажатие на для отмены");
        claimsScreenElements.getCancelButton().perform(click());
    }

    public void choosingRandomClaim(int position) {
        Allure.step("Случайный выбор претензии");
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    public void searchForClaimsWithTheOpenStatus() {
        Allure.step("Случайный выбор претензии со статусом Open");
        FilteringWindowScreenElements filteringWindowScreenElements = new FilteringWindowScreenElements();
        pressingOnTheButtonToGoToTheFilteringScreen();
        filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
        filteringWindowScreenElements.getOkButton().perform(click());
        SystemClock.sleep(2000);
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(randomClaims(0, 1, 2), click()));
        SystemClock.sleep(3000);
    }

    public void checkingTheStatusShouldNotBeCancelled() {
        Allure.step("Проверка статус не должен быть Cancelled");
        claimsScreenElements.getStatus().check(matches(not(withText("Cancelled"))));
    }

    public void checkScreenNameClaims() {
        Allure.step("Проверка названия экрана Claims");
        claimsScreenElements.getScreenNameClaims().check(matches(isDisplayed()));
    }

    public void verificationOfIdentifyingNamesInTheClaim() {
        Allure.step("Проверка идентифицирующих названий в претензии");
        claimsScreenElements.getTitle().check(matches(isDisplayed()));
        claimsScreenElements.getExecutorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getPlanDateLabel().check(matches(isDisplayed()));
        claimsScreenElements.getStatus().check(matches(isDisplayed()));
        claimsScreenElements.getDescriptionText().check(matches(isDisplayed()));
        claimsScreenElements.getAuthorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getCreatedLabel().check(matches(isDisplayed()));
    }

    public void checkClaim() {
        Allure.step("Проверка названий в претензии");
        String textExecutor = Helper.Text.getText(claimsScreenElements.getExecutorText());
        claimsScreenElements.getExecutorText().check(matches(withText(textExecutor))).check(matches(isDisplayed()));
        claimsScreenElements.getPlanDateText().check(matches(isDisplayed()));
        claimsScreenElements.getTimeText().check(matches(isDisplayed()));
        claimsScreenElements.getAuthorText().check(matches(isDisplayed()));
        claimsScreenElements.getDescriptionText().check(matches(isDisplayed()));
        claimsScreenElements.getStatus().check(matches(isDisplayed()));
        claimsScreenElements.getTitle().check(matches(withText("Title"))).check(matches(isDisplayed()));
        claimsScreenElements.getExecutorLabel().check(matches(withText("Executor"))).check(matches(isDisplayed()));
        claimsScreenElements.getPlanDateLabel().check(matches(withText("Plan date"))).check(matches(isDisplayed()));
        claimsScreenElements.getAuthorLabel().check(matches(withText("Author"))).check(matches(isDisplayed()));
        claimsScreenElements.getCreatedLabel().check(matches(withText("Created"))).check(matches(isDisplayed()));
    }

    public void fillingInTheCommentField(String text) {
        Allure.step("Заполнение поля комментария");
        claimsScreenElements.getCommentField().perform(typeText(text));
    }

    public void clickingOnTheButtonToCancelAddingComment() {
        Allure.step("Нажатие на кнопку отмены добавления комментария");
        claimsScreenElements.getCancelAddCommentButton().perform(click());
    }

    public void checkingTheAbsenceOfComment(String commentText) {
        Allure.step("Проверка отсутствия комментария");
        ViewInteraction commentTextFromField = onView(allOf(withId(R.id.comment_description_text_view), withParent(withParent(allOf(withId(R.id.claim_comments_list_recycler_view), withChild(withChild(allOf(withText(commentText)))))))));
        claimsScreenElements.getCommentStatus().check(matches(not(withText(commentTextFromField.toString()))));
    }

    public void checkingTheVisibilityOfTheAddedComment() {
        Allure.step("Проверка видимости добавленного комментария");
        claimsScreenElements.getCommentStatus().check(matches(isDisplayed()));
    }

    public void checkingCommentShouldBeAdded(String validTextComment) {
        Allure.step("Проверка должен добавиться комментарий");
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment.trim()))).check(matches(isDisplayed()));
    }

    public void checkingTheCommentBeforeEditingAndAfter(String validTextComment) {
        Allure.step("Проверка комментария до редактирования и после");
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment))).check(matches(isDisplayed()));
    }

    public void checkingTheAddedComment(String validTextComment) {
        Allure.step("Проверка добавленного комментария");
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment.trim()))).check(matches(isDisplayed()));
    }

    public void checkingTheCommentShouldNotBeCreated(String validTextComment) {
        Allure.step("Проверка коментарий не должен создаться");
        assertNotEquals(validTextComment , searchForAnUncreatedComment(validTextComment.trim()));
    }

    public void checkingTheOpenStatus() {
        Allure.step("Проверка открытого статуса");
        claimsScreenElements.getStatusOpen().check(matches(isDisplayed()));
    }

    public void checkingTheInProgressStatus() {
        Allure.step("Проверка статуса In Progress");
        claimsScreenElements.getStatusInProgress().check(matches(isDisplayed()));
    }

    public void checkingTheExecutedStatus() {
        Allure.step("Проверка статуса Executed");
        claimsScreenElements.getStatusExecuted().check(matches(isDisplayed()));
    }

    public void checkingTheCanceledStatus() {
        Allure.step("Проверка статуса Canceled");
        claimsScreenElements.getStatusCancelled().check(matches(isDisplayed()));
    }

    public void checkingForTheAbsenceOfAnUncreatedClaim(String titleText) {
        Allure.step("Проверка отсутствия в блоке претензий не созданной претензии");
        assertNotEquals(titleText, searchForAnUncreatedClaim(titleText));
    }

    public void comparisonOfDataBeforeAndAfterEditing(
            String executorTextItWas, String executorTextItWasHasBecome, String planDateTextItWas, String planDateTextItWasHasBecome,
            String timeTextItWas, String timeTextItWasHasBecome, String authorTextItWas, String authorTextItWasHasBecome) {
        Allure.step("Сравнение данных до редактирования и после");
        Assert.assertEquals(executorTextItWas, executorTextItWasHasBecome);
        Assert.assertEquals(planDateTextItWas, planDateTextItWasHasBecome);
        Assert.assertEquals(timeTextItWas, timeTextItWasHasBecome);
        Assert.assertEquals(authorTextItWas, authorTextItWasHasBecome);
    }

    public String commentText() {
        return Helper.Text.getText(claimsScreenElements.getCommentField());
    }

    public String executorText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.executor_name_text_view), 0)));
    }

    public String planDateText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plane_date_text_view), 0)));
    }

    public String timeText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.plan_time_text_view), 0)));
    }

    public String authorText() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.author_name_text_view), 0)));
    }

    public String descriptionClaimField() {
        return Helper.Text.getText(onView(withId(R.id.description_text_view)));
    }

}
