package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomExecutor;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.text51Symbol;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep.enterCreateClaimsActionButton;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.CreatingClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.FilteringWindowScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.WatchScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreatingClaimsScreenTest {

    CreatingClaimsScreenStep creatingClaimsScreenStep = new CreatingClaimsScreenStep();
    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    MainScreenStep mainScreenStep = new MainScreenStep();
    WatchScreenStep watchScreenStep = new WatchScreenStep();
    FilteringWindowScreenStep filteringWindowScreenStep = new FilteringWindowScreenStep();

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
    @Description("В этом тест кейсе мы проверяем название экрана Creating Claims")
    public void theScreenShouldHaveName() {
        mainScreenStep.randomTransitionToCreatingClaims();
        creatingClaimsScreenStep.checkingTheNameOfTheClaimCreationScreen();
    }

    @Test
    @DisplayName("The fields must have names")
    @Description("В этом тест кейсе мы проверяем названия полей")
    public void theFieldsMustHaveNames() {
        mainScreenStep.randomTransitionToCreatingClaims();
        creatingClaimsScreenStep.checkNameFieldInCreatingClaims();
    }

    @Test
    @DisplayName("no more than 50 characters should be entered")
    @Description("В этом тест кейсе мы проверяем что в поле Title нельзя ввести больше 50 символов")
    public void noMoreThan50CharactersShouldBeEntered() {
        String invalidText = text51Symbol();

        mainScreenStep.randomTransitionToCreatingClaims();
        creatingClaimsScreenStep.textInput(invalidText);
        creatingClaimsScreenStep.checkingTheNumberOfCharactersEnteredAndCharactersInTheField();
    }

    @Test
    @DisplayName("A drop-down list with categories should appear")
    @Description("В этом тест кейсе мы проверяем, что при клике на поле \"Category\" появляется выпадающий список с доспупными категориями")
    public void aDropDownListWithCategoriesShouldAppear() {
        mainScreenStep.randomTransitionToCreatingClaims();
        creatingClaimsScreenStep.clickingOnTheExecutorField();
        SystemClock.sleep(2000);
        creatingClaimsScreenStep.checkingTheAppearanceOfTheDropDownList(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("A calendar should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Date\" появляется календарь")
    public void aCalendarShouldAppear() {
        mainScreenStep.randomTransitionToCreatingClaims();
        creatingClaimsScreenStep.clickingOnTheDateField();
        creatingClaimsScreenStep.checkingTheCalendarAppearance(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("An arrow-shaped clock should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Time\" появляются часы стрелочного типа")
    public void anArrowShapedClockShouldAppear() {
        mainScreenStep.randomTransitionToCreatingClaims();
        creatingClaimsScreenStep.clickingOnTheTimeField();
        creatingClaimsScreenStep.checkingTheAppearanceOfClockOfTheArrowType(ActivityTestRule.getActivity());
    }

    @Test
    @DisplayName("The type of watch should change")
    @Description("В этом тест кейсе мы проверяем возможность выбора типа часов, при нажатии на кнопку с иконкой \"клавиатура\" должен поменяться вид часов")
    public void theTypeOfWatchShouldChange() {
        mainScreenStep.randomTransitionToCreatingClaims();
        SystemClock.sleep(3000);
        creatingClaimsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();
    }

    @Test
    @DisplayName("A claim should be created in the Claims section")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"SAVE\" после заполнения полей \"Title, Executor, Date Time, Description, \" валидными значениями, должна создаться \"Претензия\" в разделе Claims (появиться в ленте Claims на странице \"Claims\" ")
    public void aClaimShouldBeCreatedInTheClaimsSection() {
        String titleText = textSymbol(5);
        String randomExecutor = randomExecutor();
        enterCreateClaimsActionButton();

        creatingClaimsScreenStep.fillingInFieldsWithValidData(titleText, randomExecutor);

        String title = creatingClaimsScreenStep.title();
        String executor = creatingClaimsScreenStep.executor();
        String date = creatingClaimsScreenStep.dateFromTheField();
        String time = creatingClaimsScreenStep.time();
        String description = creatingClaimsScreenStep.description();

        creatingClaimsScreenStep.clickingOnTheSaveButton();
        claimsScreenStep.pressingOnTheButtonToGoToTheFilteringScreen();
        filteringWindowScreenStep.clickingOnTheOkButton();
        creatingClaimsScreenStep.searchForCreatedClaim(titleText);

        String titleOnCaredClaims = creatingClaimsScreenStep.titleOnCaredClaims();
        String executorOnCaredClaims = creatingClaimsScreenStep.executorOnCaredClaims();
        String dateOnCaredClaims = creatingClaimsScreenStep.dateOnCaredClaims();
        String timeOnCaredClaims = creatingClaimsScreenStep.timeOnCaredClaims();
        String descriptionOnCaredClaims = creatingClaimsScreenStep.descriptionOnCaredClaims();

        SystemClock.sleep(3000);
        creatingClaimsScreenStep.checkingTheDataOfTheCreatedClaimAndTheFoundOne(
                title, titleOnCaredClaims, executor, executorOnCaredClaims, date, dateOnCaredClaims, time, timeOnCaredClaims,
                description, descriptionOnCaredClaims);
    }

    @Test
    @DisplayName("A claim with the Open status should be created")
    @Description("В этом тест кейсе мы проверяем, что при отсутствии ввода, или ввода исполнителя не из выпадающего  списка создается претензия со статусом \"Open\"")
    public void aClaimWithTheOpenStatusShouldBeCreated() {
        String titleText = textSymbol(5);
        enterCreateClaimsActionButton();

        creatingClaimsScreenStep.fillingInTheFieldsWithValidDataToCreateClaimWithAnOpenStatus(titleText);
        String title = creatingClaimsScreenStep.title();
        String date = creatingClaimsScreenStep.dateFromTheField();
        String time = creatingClaimsScreenStep.time();
        String description = creatingClaimsScreenStep.description();

        creatingClaimsScreenStep.clickingOnTheSaveButton();
        claimsScreenStep.pressingOnTheButtonToGoToTheFilteringScreen();
        filteringWindowScreenStep.clickingOnTheCheckBoxInProgress();
        filteringWindowScreenStep.clickingOnTheOkButton();
        creatingClaimsScreenStep.searchForCreatedClaim(titleText);

        String titleOnCaredClaims = creatingClaimsScreenStep.titleOnCaredClaims();
        String dateOnCaredClaims = creatingClaimsScreenStep.dateOnCaredClaims();
        String timeOnCaredClaims = creatingClaimsScreenStep.timeOnCaredClaims();
        String descriptionOnCaredClaims = creatingClaimsScreenStep.descriptionOnCaredClaims();

        creatingClaimsScreenStep.checkingTheDataAndStatusOfTheClaimCreatedAndFound(
                title, titleOnCaredClaims, date, dateOnCaredClaims, time, timeOnCaredClaims,
                description, descriptionOnCaredClaims);
    }

    @Test
    @DisplayName("Cancellation of the claim creation")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку CANCEL после заполнения полей Title, Executor, Date Time, Description, валидными значениями, или незаполнения полей, не должна создаться  Claims в блоке topic (не должна появиться в ленте Claims на странице Claims")
    public void cancellationOfTheClaimCreation() {
        String titleText = textSymbol(5);
        String randomExecutor = randomExecutor();
        enterCreateClaimsActionButton();

        creatingClaimsScreenStep.fillingInFieldsWithValidData(titleText, randomExecutor);
        creatingClaimsScreenStep.clickingOnTheCancelClaimCreationButton();
        creatingClaimsScreenStep.clickingOnTheButtonToConfirmTheCancellationOfTheClaimCreation();
        claimsScreenStep.checkScreenNameClaims();

        claimsScreenStep.checkingForTheAbsenceOfAnUncreatedClaim(titleText);
    }

    @Test
    @DisplayName("A warning message should appear if the fields are empty when clicking on the save button")
    @Description("В этом тест кейсе мы проверяем, что при незаполненном, незаполненных полях появляется предупреждающее сообщение, после нажатия на кнопку \"SAVE\"  \"fill empty fields\"  ")
    public void aWarningMessageShouldAppearIfTheFieldsAreEmptyWhenClickingOnTheSaveButton() {
        String titleText = textSymbol(5);
        String randomExecutor = randomExecutor();
        mainScreenStep.randomTransitionToCreatingClaims();

        creatingClaimsScreenStep.fillingInFieldsWithValidData(titleText, randomExecutor);
        creatingClaimsScreenStep.nameDeletion();
        creatingClaimsScreenStep.clickingOnTheSaveButton();
        creatingClaimsScreenStep.checkingTheFillEmptyFields(ActivityTestRule.getActivity(), R.string.empty_fields);
    }

    @Test
    @DisplayName("Fields should not be filled in with Russian letters")
    @Description("В этом тест кейсе мы проверяем, что поля \"Title\",\"Description\", не заполняются русскими буквами")
    public void fieldsShouldNotBeFilledInWithRussianLetters() {
        String invalidLanguageText = "Привет мир";

        mainScreenStep.randomTransitionToCreatingClaims();
        try {
            creatingClaimsScreenStep.invalidLanguage(invalidLanguageText, invalidLanguageText, invalidLanguageText);
        } catch (RuntimeException expected) {

        } finally {
            creatingClaimsScreenStep.checkingForTheAbsenceOfWordsFromRussianLettersInTheFields();
        }
    }

    @Test
    @DisplayName("The fields must be filled in with English letters")
    @Description("В этом тест кейсе мы проверяем, что поля \"Title\",\"Description\", заполняются английскими буквами")
    public void theFieldsMustBeFilledInWithEnglishLetters() {
        String validLanguageText = "Hello world";

        mainScreenStep.randomTransitionToCreatingClaims();
        SystemClock.sleep(3000);
        creatingClaimsScreenStep.validLanguage(validLanguageText, validLanguageText, validLanguageText);
        SystemClock.sleep(3000);
        creatingClaimsScreenStep.checkingForThePresenceOfWordsFromEnglishLettersInTheFields(validLanguageText);
    }

    @Test
    @DisplayName("Cancellation of cancellation of claim creation")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"CANCEL\" и после появления окна с предупреждающей надписью и кнопками  \"CANCEL\", \"ок\" при нажатии на кнопку \"CANCEL\" происходит отмена выхода из создания претензии  пользователь остается в \"\"Creating Claims\"  ")
    public void cancellationOfCancellationOfClaimCreation() {
        String titleText = textSymbol(5);
        String randomExecutor = randomExecutor();
        mainScreenStep.randomTransitionToCreatingClaims();

        creatingClaimsScreenStep.fillingInFieldsWithValidData(titleText, randomExecutor);
        creatingClaimsScreenStep.clickingOnTheCancelClaimCreationButton();
        creatingClaimsScreenStep.clickingOnTheCancelButtonToExitTheClaimCreation();
        creatingClaimsScreenStep.checkingTheNameOfTheClaimCreationScreen();
    }
}
