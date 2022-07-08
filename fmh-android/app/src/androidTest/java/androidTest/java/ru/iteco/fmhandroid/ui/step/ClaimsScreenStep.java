package androidTest.java.ru.iteco.fmhandroid.ui.step;

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
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Search.searchForAnUncreatedClaim;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Search.searchForAnUncreatedComment;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ClaimsScreenStep {

    ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();

    @Step("Нажатие на кнопку перехода на экран создания претензии")
    public void clickingOnTheButtonToGoToTheClaimCreationScreen() {
        claimsScreenElements.getCreateClaimsButton().perform(click());
    }

    @Step("Нажатие на кнопку перехода на экран Filtering")
    public void pressingOnTheButtonToGoToTheFilteringScreen() {
        claimsScreenElements.getFilteringButton().perform(click());
    }

    @Step("Проверка статус не должен быть Executed")
    public void checkingTheStatusShouldNotBeExecuted() {
        claimsScreenElements.getStatus().check(matches(not(withText("Executed"))));
    }

    @Step("Нажатие на кнопку выхода")
    public void pressingTheExitButton() {
        claimsScreenElements.getExitButton().perform(click());
    }

    @Step("Нажатие на случайно выбранную претензию")
    public void clickingOnRandomlySelectedClaim(int position) {
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на кнопку закрыть")
    public void clickingOnTheCloseButton() {
        claimsScreenElements.getCloseImageButton().perform(click());
    }

    @Step("Нажатие на кнопку блокнот с карандашом для перехода к экрану для редактирования")
    public void clickingOnTheNotepadWithPencilButton() {
        claimsScreenElements.getEditClaimsButton().perform(click());
    }

    @Step("Нажатие на кнопку с иконкой блокнот с шестеренкой")
    public void clickingOnTheButtonWithTheNotepadIconWithGear() {
        claimsScreenElements.getButtonChangeStatus().perform(click());
    }

    @Step("Нажатие на взять в работу")
    public void clickingOnTakeToWork() {
        claimsScreenElements.getTakeToWork().perform(click());
    }

    @Step("Нажатие на Сбросить")
    public void clickingOnReset() {
        claimsScreenElements.getThrowOff().perform(click());
    }

    @Step("Нажатие на кнопку ок добавления комментария")
    public void clickingOnTheOkButtonToAddComment() {
        claimsScreenElements.getOkButtonAddComment().perform(scrollTo(), click());
    }

    @Step("Нажатие на кнопку добавления комментария")
    public void clickingOnTheAddCommentButton() {
        claimsScreenElements.getAddComment().perform(click());
    }

    @Step("Нажатие на кнопку для входа в экран редактирования комментария")
    public void clickingOnTheButtonToEnterTheCommentEditingScreen() {
        claimsScreenElements.getEditCommentButton().perform(click());
    }

    @Step("Нажатие на для выполнения")
    public void clickingOnToExecute() {
        claimsScreenElements.getToExecute().perform(click());
    }

    @Step("Нажатие на для отмены")
    public void clickingOnToCancel() {
        claimsScreenElements.getCancelButton().perform(click());
    }

    @Step("Случайный выбор претензии")
    public void choosingRandomClaim(int position) {
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    @Step("Случайный выбор претензии со статусом Open")
    public void searchForClaimsWithTheOpenStatus() {
        FilteringWindowScreenElements filteringWindowScreenElements = new FilteringWindowScreenElements();
        pressingOnTheButtonToGoToTheFilteringScreen();
        filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
        filteringWindowScreenElements.getOkButton().perform(click());
        SystemClock.sleep(2000);
        claimsScreenElements.getBlockClaims().perform(actionOnItemAtPosition(randomClaims(0, 1, 2), click()));
        SystemClock.sleep(3000);
    }

    @Step("Проверка статус не должен быть Cancelled")
    public void checkingTheStatusShouldNotBeCancelled() {
        claimsScreenElements.getStatus().check(matches(not(withText("Cancelled"))));
    }

    @Step("Проверка названия экрана Claims")
    public void checkScreenNameClaims() {
        claimsScreenElements.getScreenNameClaims().check(matches(isDisplayed()));
    }

    @Step("Проверка идентифицирующих названий в претензии")
    public void verificationOfIdentifyingNamesInTheClaim() {
        claimsScreenElements.getTitle().check(matches(isDisplayed()));
        claimsScreenElements.getExecutorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getPlanDateLabel().check(matches(isDisplayed()));
        claimsScreenElements.getStatus().check(matches(isDisplayed()));
        claimsScreenElements.getDescriptionText().check(matches(isDisplayed()));
        claimsScreenElements.getAuthorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getCreatedLabel().check(matches(isDisplayed()));
    }

    @Step("Проверка названий в претензии")
    public void checkClaim() {
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

    @Step("Заполнение поля комментария")
    public void fillingInTheCommentField(String text) {
        claimsScreenElements.getCommentField().perform(typeText(text));
    }

    @Step("Нажатие на кнопку отмены добавления комментария")
    public void clickingOnTheButtonToCancelAddingComment() {
        claimsScreenElements.getCancelAddCommentButton().perform(click());
    }

    @Step("Проверка отсутствия комментария")
    public void checkingTheAbsenceOfComment(String commentText) {
        ViewInteraction commentTextFromField = onView(allOf(withId(R.id.comment_description_text_view), withParent(withParent(allOf(withId(R.id.claim_comments_list_recycler_view), withChild(withChild(allOf(withText(commentText)))))))));
        claimsScreenElements.getCommentStatus().check(matches(not(withText(commentTextFromField.toString()))));
    }

    @Step("Проверка видимости добавленного комментария")
    public void checkingTheVisibilityOfTheAddedComment() {
        claimsScreenElements.getCommentStatus().check(matches(isDisplayed()));
    }

    @Step("Проверка должен добавиться комментарий")
    public void checkingCommentShouldBeAdded(String validTextComment) {
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment.trim()))).check(matches(isDisplayed()));
    }

    @Step("Проверка комментария до редактирования и после")
    public void checkingTheCommentBeforeEditingAndAfter(String validTextComment) {
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment))).check(matches(isDisplayed()));
    }

    @Step("Проверка добавленного комментария ")
    public void checkingTheAddedComment(String validTextComment) {
        onView(allOf(withId(R.id.comment_description_text_view), withText(validTextComment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(validTextComment.trim()))).check(matches(isDisplayed()));
    }

    @Step("Проверка коментарий не должен создаться")
    public void checkingTheCommentShouldNotBeCreated(String validTextComment) {
        assertNotEquals(validTextComment , searchForAnUncreatedComment(validTextComment.trim()));
    }

    @Step("Проверка открытого статуса")
    public void checkingTheOpenStatus() {
        claimsScreenElements.getStatus().check(matches(withText("Open"))).check(matches(isDisplayed()));
    }

    @Step("Проверка статуса In Progress")
    public void checkingTheInProgressStatus() {
        claimsScreenElements.getStatus().check(matches(withText("In progress"))).check(matches(isDisplayed()));
    }

    @Step("Проверка статуса Executed")
    public void checkingTheExecutedStatus() {
        claimsScreenElements.getStatus().check(matches(withText("Executed"))).check(matches(isDisplayed()));
    }

    @Step("Проверка статуса Canceled")
    public void checkingTheCanceledStatus() {
        claimsScreenElements.getStatus().check(matches(withText("Canceled"))).check(matches(isDisplayed()));
    }

    @Step("Проверка отсутствия в блоке претензий не созданной претензии ")
    public void checkingForTheAbsenceOfAnUncreatedClaim(String titleText) {
        assertNotEquals(titleText, searchForAnUncreatedClaim(titleText));
    }

    @Step("Сравнение данных до редактирования и после")
    public void comparisonOfDataBeforeAndAfterEditing(
            String executorTextItWas, String executorTextItWasHasBecome, String planDateTextItWas, String planDateTextItWasHasBecome,
            String timeTextItWas, String timeTextItWasHasBecome, String authorTextItWas, String authorTextItWasHasBecome) {
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
