package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.CreatingClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.EditingClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.FilteringWindowScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ClaimsScreenTest {

    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    MainScreenStep mainScreenStep = new MainScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    CreatingClaimsScreenStep creatingClaimsScreenStep = new CreatingClaimsScreenStep();
    FilteringWindowScreenStep filteringWindowScreenStep = new FilteringWindowScreenStep();
    EditingClaimsScreenStep editingClaimsScreenStep = new EditingClaimsScreenStep();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenStep.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.validLoginPassword(authInfo());
            SystemClock.sleep(5000);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("У экрана должно быть название")
    @Description("В этом тест кейсе мы проверяем название экрана Claims")
    public void theScreenShouldHaveName() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.checkScreenNameClaims();
    }


    @Test
    @DisplayName("the filtering window should appear")
    @Description("В этом тест кейсе мы проверяем что при нажатии на кнопку \"три полоски с кружками\" на странице \"Claims\" пользователь попадает в \"Filtering\"")
    public void theFilteringWindowShouldAppear() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.pressingOnTheButtonToGoToTheFilteringScreen();
        filteringWindowScreenStep.checkingTheScreenNameFiltering();
    }

    @Test
    @DisplayName("should go to the create claims section")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"+\" пользователь попадает в \"Creating Claims\"")
    public void shouldGoToTheCreateClaimsSection() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.clickingOnTheButtonToGoToTheClaimCreationScreen();
        creatingClaimsScreenStep.checkingTheNameOfTheClaimCreationScreen();
    }

    @Test
    @DisplayName("Must go to Claims")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на блок \"претензия\" в ленте страницы \"Claims\", пользователь переходит в \"претензию\"")
    public void mustGoToClaims() {
        int position = random(1, 2, 3);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.choosingRandomClaim(position);
        claimsScreenStep.checkClaim();
    }

    @Test
    @DisplayName("The status should change to In progress")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"блокнот с шестиренкой\"  и нажатии в сплывшем окне на \"Take to work\" статус претензии меняется на  \"In progress\" ")
    public void theStatusShouldChangeToInProgress() {
        creatingClaimsScreenStep.createClaimStatusOpenSetUp();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        claimsScreenStep.checkingTheOpenStatus();
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        claimsScreenStep.clickingOnTakeToWork();
        claimsScreenStep.checkingTheInProgressStatus();
    }

    @Test
    @DisplayName("Should go to Editing Claims")
    @Description(" В этом тест кейсе мы проверяем что при нажатии на кнопку  \"блокнот с карандаошом\"  в  \"Claims\" пользователь попадает в  \"Editing Claims\" ")
    public void shouldGoToEditingClaims() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.checkingTheNameOfTheScreenForEditingClaims();
    }

    @Test
    @DisplayName("The claim must be edited")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"блокнот с карандашем\" пользователь попадает в раздел редактирования претензии претензия редактируется ")
    public void theClaimMustBeEdited() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        Helper.Swipes.swipeToBottom();

        claimsScreenStep.checkingTheOpenStatus();
        String titleClaimFieldItWas = editingClaimsScreenStep.titleClaimField();
        String executorClaimFieldItWas = editingClaimsScreenStep.executorClaimField();
        String dateClaimFieldItWas = editingClaimsScreenStep.dateClaimField();
        String timeClaimFieldItWas = editingClaimsScreenStep.timeClaimField();
        String descriptionClaimFieldItWas = editingClaimsScreenStep.descriptionClaimField();

        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.fillingInFieldsWithValidData();
        editingClaimsScreenStep.clickingOnTheSaveButton();

        String titleClaimFieldItWasHasBecome = editingClaimsScreenStep.titleClaimField();
        String executorClaimFieldItWasHasBecome = editingClaimsScreenStep.executorClaimField();
        String dateClaimFieldItWasHasBecome = editingClaimsScreenStep.dateClaimField();
        String timeClaimFieldItWasHasBecome = editingClaimsScreenStep.timeClaimField();
        String descriptionClaimFieldItWasHasBecome = editingClaimsScreenStep.descriptionClaimField();

        editingClaimsScreenStep.comparisonOfDataBeforeAndAfterEditing(titleClaimFieldItWas, titleClaimFieldItWasHasBecome, executorClaimFieldItWas,
                executorClaimFieldItWasHasBecome, dateClaimFieldItWas, dateClaimFieldItWasHasBecome,
                timeClaimFieldItWas, timeClaimFieldItWasHasBecome, descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Test
    @DisplayName("Cancellation of claim editing")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"CANCEL\" происходит отмена редактирования претензии")
    public void cancellationOfClaimEditing() {
        int position = randomClaims( 1, 2);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.pressingOnTheButtonToGoToTheFilteringScreen();
        filteringWindowScreenStep.clickingOnTheCheckBoxInProgress();
        filteringWindowScreenStep.clickingOnTheOkButton();
        Helper.Swipes.scrollSlowlyDown();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnRandomlySelectedClaim(position);
        SystemClock.sleep(3000);

        claimsScreenStep.checkingTheOpenStatus();
        String executorTextItWas = claimsScreenStep.executorText();
        String planDateTextItWas = claimsScreenStep.planDateText();
        String timeTextItWas = claimsScreenStep.timeText();
        String authorTextItWas = claimsScreenStep.authorText();

        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.checkingTheNameOfTheScreenForEditingClaims();
        editingClaimsScreenStep.fillingInFieldsWithValidData();
        editingClaimsScreenStep.clickingOnTheUndoEditButton();
        editingClaimsScreenStep.clickingOnTheButtonToConfirmTheCancellationOfEditing();

        claimsScreenStep.checkingTheOpenStatus();
        String executorTextItWasHasBecome = claimsScreenStep.executorText();
        String planDateTextItWasHasBecome = claimsScreenStep.planDateText();
        String timeTextItWasHasBecome = claimsScreenStep.timeText();
        String authorTextItWasHasBecome = claimsScreenStep.authorText();

        claimsScreenStep.comparisonOfDataBeforeAndAfterEditing(
                executorTextItWas, executorTextItWasHasBecome, planDateTextItWas, planDateTextItWasHasBecome,
                timeTextItWas, timeTextItWasHasBecome, authorTextItWas, authorTextItWasHasBecome);
    }

    @Test
    @DisplayName("Canceling the comment field when selecting To execute")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"CANCEL\" происходит отмена заполнения поля для коментария и дальнейшей смены статуса на \" Еxecuted")
    public void cancelingTheCommentFieldWhenSelectingToExecute() {
        creatingClaimsScreenStep.createClaimStatusOpenSetUp();
        String text = textSymbol(5);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        claimsScreenStep.checkingTheOpenStatus();
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTakeToWork();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheInProgressStatus();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnReset();
        SystemClock.sleep(2000);
        claimsScreenStep.fillingInTheCommentField(text);
        String commentText = claimsScreenStep.commentText();
        claimsScreenStep.clickingOnTheButtonToCancelAddingComment();
        SystemClock.sleep(2000);

        claimsScreenStep.checkingTheAbsenceOfComment(commentText);
        claimsScreenStep.checkingTheInProgressStatus();
    }

    @Test
    @DisplayName("Canceling the comment field when selecting Throw off")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на \"Throw off\" сбрасывается статус с \"In progress\" на \"Open\"")
    public void cancelingTheCommentFieldWhenSelectingThrowOff() {
        creatingClaimsScreenStep.createClaimStatusOpenSetUp();
        String text = textSymbol(5);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        claimsScreenStep.checkingTheOpenStatus();
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTakeToWork();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheInProgressStatus();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnReset();
        SystemClock.sleep(2000);
        claimsScreenStep.fillingInTheCommentField(text);
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheOkButtonToAddComment();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheVisibilityOfTheAddedComment();
        claimsScreenStep.checkingTheOpenStatus();
    }

    @Test
    @DisplayName("The status should change to executed")
    @Description("В этом тест кейсе мы проверяем смену статуса In Progress на статус executed")
    public void theStatusShouldChangeToExecuted() {
        creatingClaimsScreenStep.createClaimStatusOpenSetUp();
        String text = textSymbol(5);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        claimsScreenStep.checkingTheOpenStatus();
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(5000);
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTakeToWork();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheInProgressStatus();
        SystemClock.sleep(2000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(5000);
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnToExecute();
        claimsScreenStep.fillingInTheCommentField(text);
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTheOkButtonToAddComment();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheVisibilityOfTheAddedComment();
        claimsScreenStep.checkingTheExecutedStatus();
    }

    @Test
    @DisplayName("The status should be reset")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на \"Throw off\" сбрасывается статус с \"In progress\" на \"Open\"")
    public void theStatusShouldBeReset() {
        String text = textSymbol(5);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        claimsScreenStep.checkingTheOpenStatus();
        Helper.Swipes.swipeToBottom();
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTakeToWork();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheInProgressStatus();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        SystemClock.sleep(2000);
        claimsScreenStep.clickingOnReset();
        SystemClock.sleep(2000);
        claimsScreenStep.fillingInTheCommentField(text);
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheOkButtonToAddComment();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheVisibilityOfTheAddedComment();
        claimsScreenStep.checkingTheOpenStatus();
    }

    @Test
    @DisplayName("The status should change to cancelled")
    @Description("В этом тест кейсе мы проверяем смену статуса с Open на Canceled")
    public void theStatusShouldChangeToCancelled() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheButtonWithTheNotepadIconWithGear();
        claimsScreenStep.clickingOnToCancel();
        claimsScreenStep.checkingTheCanceledStatus();
    }
}
