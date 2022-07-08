package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomExecutor;
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
import androidTest.java.ru.iteco.fmhandroid.ui.step.EditingClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.WatchScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ЕditingClaimsScreenTest {

    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    MainScreenStep mainScreenStep = new MainScreenStep();
    EditingClaimsScreenStep editingClaimsScreenStep = new EditingClaimsScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    WatchScreenStep watchScreenStep = new WatchScreenStep();

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
    @Description("В этом тест кейсе мы проверяем название экрана Editing Claims")
    public void theScreenShouldHaveName() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.checkingTheNameOfTheScreenForEditingClaims();
    }

    @Test
    @DisplayName("the fields must have names")
    @Description("В этом тест кейсе мы проверяем, что поля имеют идентифицирующие названия")
    public void FieldsMustHaveNames() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.checkNameFieldInEditingClaims();
    }

    @Test
    @DisplayName("The fields must contain data")
    @Description("В этом тест кейсе мы проверяем, что поля имеют данные заполненые ранее")
    public void theFieldsMustContainData() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.checkingTheFieldsAreFilledIn();
    }

    @Test
    @DisplayName("A drop-down list with categories should appear")
    @Description("В этом тест кейсе мы проверяем, что при клике на поле \"Category\" появляется выпадающий список с доспупными категориями")
    public void aDropDownListWithCategoriesShouldAppear() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.clickingOnTheExecutorField();
        editingClaimsScreenStep.checkingTheAppearanceOfTheDropDownList(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("A calendar should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Publication date\" появляется календарь")
    public void aCalendarShouldAppear() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.clickingOnTheDateField();
        editingClaimsScreenStep.checkingTheCalendarAppearance(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("A clock of the arrow type should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Time\" появляется часы стрелочного типа")
    public void aClockOfTheArrowTypeShouldAppear() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.clickingOnTheTimeField();
        editingClaimsScreenStep.checkingTheAppearanceOfClockOfTheArrowType(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("The type of watch should change")
    @Description("В этом тест кейсе мы проверяем возможность смены типа часов. что при нажатии на кнопку с иконкой \"клавиатура\" должен поменяться вид часов")
    public void theTypeOfWatchShouldChange() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();
    }

    @Test
    @DisplayName("The claim must be edited")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"блокнот с карандашем\" пользователь попадает в раздел редактирования претензии , претензия редактируется")
    public void theClaimMustBeEdited() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);

        claimsScreenStep.checkingTheOpenStatus();
        String titleClaimFieldItWas = claimsScreenStep.authorText();
        String executorClaimFieldItWas = claimsScreenStep.executorText();
        String dateClaimFieldItWas = claimsScreenStep.planDateText();
        String timeClaimFieldItWas = claimsScreenStep.timeText();
        String descriptionClaimFieldItWas = claimsScreenStep.descriptionClaimField();

        Helper.Swipes.swipeToBottom();
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.fillingInFieldsWithValidData();
        editingClaimsScreenStep.clickingOnTheSaveButton();

        String titleClaimFieldItWasHasBecome = claimsScreenStep.authorText();
        String executorClaimFieldItWasHasBecome = claimsScreenStep.executorText();
        String dateClaimFieldItWasHasBecome = claimsScreenStep.planDateText();
        String timeClaimFieldItWasHasBecome = claimsScreenStep.timeText();
        String descriptionClaimFieldItWasHasBecome = claimsScreenStep.descriptionClaimField();

        SystemClock.sleep(3000);
        editingClaimsScreenStep.checkingDataBeforeEditingAndAfter(
                titleClaimFieldItWas, titleClaimFieldItWasHasBecome, executorClaimFieldItWas, executorClaimFieldItWasHasBecome,
                dateClaimFieldItWas, dateClaimFieldItWasHasBecome, timeClaimFieldItWas, timeClaimFieldItWasHasBecome,
                descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Test
    @DisplayName("Cancellation of claim editing")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"CANCEL\" происходит отмена редактирования претензии")
    public void cancellationOfClaimEditing() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);

        claimsScreenStep.checkingTheOpenStatus();
        String titleClaimFieldItWas = claimsScreenStep.authorText();
        String executorClaimFieldItWas = claimsScreenStep.executorText();
        String dateClaimFieldItWas = claimsScreenStep.planDateText();
        String timeClaimFieldItWas = claimsScreenStep.timeText();
        String descriptionClaimFieldItWas = claimsScreenStep.descriptionClaimField();

        Helper.Swipes.swipeToBottom();
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.clickingOnTheUndoEditButton();
        editingClaimsScreenStep.clickingOnTheButtonToConfirmTheCancellationOfEditing();

        claimsScreenStep.checkingTheOpenStatus();
        String titleClaimFieldItWasHasBecome = claimsScreenStep.authorText();
        String executorClaimFieldItWasHasBecome = claimsScreenStep.executorText();
        String dateClaimFieldItWasHasBecome = claimsScreenStep.planDateText();
        String timeClaimFieldItWasHasBecome = claimsScreenStep.timeText();
        String descriptionClaimFieldItWasHasBecome = claimsScreenStep.descriptionClaimField();

        editingClaimsScreenStep.verificationOfClaimDataBeforeEditingAndAfterCancellationOfEditing(
                titleClaimFieldItWas, titleClaimFieldItWasHasBecome, executorClaimFieldItWas, executorClaimFieldItWasHasBecome,
                dateClaimFieldItWas, dateClaimFieldItWasHasBecome, timeClaimFieldItWas, timeClaimFieldItWasHasBecome,
                descriptionClaimFieldItWas, descriptionClaimFieldItWasHasBecome);
    }

    @Test
    @DisplayName("Cancellation of cancellation of claim editing")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку CANCEL появляется окно, при нажатии на кнопку CANCEL, происходит отмена отмены выхода из редактирования претензии")
    public void cancellationOfCancellationOfClaimEditing() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        claimsScreenStep.searchForClaimsWithTheOpenStatus();
        Helper.Swipes.swipeToBottom();
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.checkingTheNameOfTheScreenForEditingClaims();
        editingClaimsScreenStep.clickingOnTheUndoEditButton();
        editingClaimsScreenStep.clickingOnTheCancelButtonToExitEditingEditing();
        editingClaimsScreenStep.checkingTheNameOfTheScreenForEditingClaims();
    }

    @Test
    @DisplayName("A warning message should appear if the fields are empty when clicking on the save button")
    @Description("В этом тест кейсе мы проверяем, что при незаполненном, незаполненных полях появляется предупреждающее сообщение, после нажатия на кнопку \"SAVE\"  \"fill empty fields\" ")
    public void aWarningMessageShouldAppearIfTheFieldsAreEmptyWhenClickingOnTheSaveButton() {
        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        editingClaimsScreenStep.deletingTextFromTheTitleField();
        editingClaimsScreenStep.clickingOnTheSaveButton();
        editingClaimsScreenStep.checkingTheFillEmptyFields(ActivityTestRule.getActivity(), R.string.empty_fields);
    }

    @Test
    @DisplayName("The fields must be filled in with English letters")
    @Description("В этом тест кейсе мы проверяем, что поля заполняются латинскими буквами ")
    public void theFieldsMustBeFilledInWithEnglishLetters() {
        String validLanguageText = "hello world";

        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        editingClaimsScreenStep.validLanguage(validLanguageText, validLanguageText, validLanguageText);
        SystemClock.sleep(3000);
        editingClaimsScreenStep.checkingForThePresenceOfWordsFromEnglishLettersInTheFields(validLanguageText);
    }

    @Test
    @DisplayName("Fields should not be filled in with Russian letters")
    @Description("В этом тест кейсе мы проверяем, что поля незаполняются нелатинскими буквами")
    public void fieldsShouldNotBeFilledInWithRussianLetters() {
        String invalidLanguageText = "Привет мир";

        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(3000);
        try {
            editingClaimsScreenStep.invalidLanguage(invalidLanguageText, invalidLanguageText, invalidLanguageText);
        } catch (RuntimeException expected) {

        } finally {
            editingClaimsScreenStep.checkingForTheAbsenceOfWordsFromRussianLettersInTheFields();
        }
    }

    @Test
    @DisplayName("The status of the claim in Claims should change")
    @Description("В этом тест кейсе мы проверяем что при установке исполнителя и дальнейшем сохранении статус меняется с Open на In progress")
    public void theStatusOfTheClaimInClaimsShouldChange() {
        String executor = randomExecutor();

        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        claimsScreenStep.checkingTheOpenStatus();
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();
        SystemClock.sleep(2000);
        editingClaimsScreenStep.clickingOnTheExecutorField();
        SystemClock.sleep(2000);
        editingClaimsScreenStep.clickingOnRandomlySelectedArtist(ActivityTestRule.getActivity(), executor);
        SystemClock.sleep(2000);
        editingClaimsScreenStep.clickingOnTheSaveButton();
        SystemClock.sleep(2000);
        claimsScreenStep.checkingTheInProgressStatus();
    }

    @Test
    @DisplayName("the performer should not be installed when saving if it is not selected from the proposed list of performers")
    @Description("В этом тест кейсе мы проверяем, что поле  \"Executor\" в  \"Editing claims\"  заполненное категорией не входящей в список не может быть сохранено")
    public void performerShouldNotBeInstalledWhenSavingIfItIsNotSelectedFromTheProposedListOfPerformers() {
        String text = textSymbol(5);

        editingClaimsScreenStep.goToTheClaimCardWithTheOpenStatus();
        SystemClock.sleep(3000);

        String executorClaimFieldItWas = claimsScreenStep.executorText();
        SystemClock.sleep(2000);

        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheNotepadWithPencilButton();

        String executorClaimFieldInputText = editingClaimsScreenStep.executorClaim();
        SystemClock.sleep(2000);

        editingClaimsScreenStep.fillingInTheExecutorField(text);
        SystemClock.sleep(2000);
        editingClaimsScreenStep.clickingOnTheSaveButton();

        String executorClaimFieldItWasHasBecome = claimsScreenStep.executorText();

        editingClaimsScreenStep.checkingThePerformerBeforeEditingAndAfter(
                executorClaimFieldItWas, executorClaimFieldItWasHasBecome, executorClaimFieldInputText);
    }
}
