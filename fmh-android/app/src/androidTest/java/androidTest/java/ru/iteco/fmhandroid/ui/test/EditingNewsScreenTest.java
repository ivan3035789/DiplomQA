package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ControlPanelScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.EditingNewsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.WatchScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class EditingNewsScreenTest {
    MainScreenStep mainScreenStep = new MainScreenStep();
    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    ControlPanelScreenStep controlPanelScreenStep = new ControlPanelScreenStep();
    EditingNewsScreenStep editingNewsScreenStep = new EditingNewsScreenStep();
    WatchScreenStep watchScreenStep = new WatchScreenStep();

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenStep.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.validLoginPassword(authInfo());
            SystemClock.sleep(3000);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test /// добавить в кейсы
    @DisplayName("У экрана должно быть название")
    @Description("В этом тест кейсе мы проверяем название экрана Editing News")
    public void theScreenShouldHaveName() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.checkingTheNameOfTheEditingNewsScreen();

    }

    @Test
    @DisplayName("the fields must have names")
    @Description("В этом тест кейсе мы проверяем названия незаполненных полей")
    public void theFieldsMustHaveNames() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.checkNameFieldInEditingNews();
    }

    @Test
    @DisplayName("The fields must contain data")
    @Description("В этом тест кейсе мы проверяем, что поля имеют данные заполненые ранее")
    public void theFieldsMustContainData() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        SystemClock.sleep(3000);

        String category = editingNewsScreenStep.categoryField();
        String titleTextNews = editingNewsScreenStep.titleTextNewsField();
        String publishDate = editingNewsScreenStep.publishDateField();
        String time = editingNewsScreenStep.timeField();
        String description = editingNewsScreenStep.descriptionField();

        editingNewsScreenStep.checkingWhetherTheFieldsAreFilledWithData(
                category, titleTextNews, publishDate, time, description);
    }

    @Test
    @DisplayName("A drop-down list with categories should appear")
    @Description("В этом тест кейсе мы проверяем, что при клике на поле \"Category\" появляется выпадающий список с доспупными категориями")
    public void aDropDownListWithCategoriesShouldAppear() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.clickingOnTheCategoryField();
        SystemClock.sleep(3000);
        onView(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("A calendar should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Publication date\" появляется календарь ")
    public void aCalendarShouldAppear() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.clickingOnThePublicationDateField();
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("A clock of the arrow type should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Time\" появляется часы стрелочного типа")
    public void aClockOfTheArrowTypeShouldAppear() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.clickingOnTheTimeField();
        onView(withClassName(is("android.widget.RadialTimePickerView")))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("The type of watch should change")
    @Description("В этом тест кейсе мы проверяем возможность выбора типа часов. что при нажатии на кнопку с иконкой \"клавиатура\" должен поменяться вид часов")
    public void theTypeOfWatchShouldChange() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        SystemClock.sleep(3000);
        editingNewsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();
    }

    @Test
    @DisplayName("The news should be edited")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"SAVE\" после заполнения полей \"Title," +
            " Time, Description, Publication date\" валидными значениями, должна создаться отредактированная новость (появиться в ленте" +
            " новостей на странице \"News, Main\", а так же в \"Control panel\")")
    public void theNewsShouldBeEdited() {
        String text = textSymbol(5);
        String Category = randomCategory();
        int position = randomNews( 1);
        SystemClock.sleep(3000);

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);

        String nameNewsItWas = editingNewsScreenStep.nameNews();
        String publicationDateNewsItWas = editingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWas = editingNewsScreenStep.creationDateNews();
        String descriptionNewsItWas = editingNewsScreenStep.descriptionNews();

        editingNewsScreenStep.clickingOnTheButtonToEnterTheNewsEditing(nameNewsItWas);
        SystemClock.sleep(2000);
        editingNewsScreenStep.fillingInTheNewsFieldsWithNewData(text, Category);

        String nameNewsInput = editingNewsScreenStep.titleTextNewsField();
        String publicationDateNewsInput = editingNewsScreenStep.publishDateField();
        String timeNewsInput = editingNewsScreenStep.timeField();
        String descriptionNewsInput = editingNewsScreenStep.descriptionField();

        editingNewsScreenStep.clickingOnTheSaveButton();
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);

        String nameNewsItWasHasBecomes = editingNewsScreenStep.nameNews();
        String publicationDateNewsItWasHasBecomes = editingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWasHasBecomes = editingNewsScreenStep.creationDateNews();
        String descriptionNewsItWasHasBecomes = editingNewsScreenStep.descriptionNews();

        editingNewsScreenStep.checkingTheInitialDataOfTheNewsWithTheFillingDataAndTheFinal(
                nameNewsItWas, nameNewsInput, publicationDateNewsItWas, publicationDateNewsInput,
                creationDateNewsItWas, timeNewsInput, descriptionNewsItWas, descriptionNewsInput,
                nameNewsItWasHasBecomes, publicationDateNewsItWasHasBecomes, creationDateNewsItWasHasBecomes,
                descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("Canceling news editing")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"CANCEL\" и после появления окна с " +
            "предупреждающей надписью и кнопками  \"CANCEL\", \"ок\" при нажатии на кнопку  \"ок\" пользователь выходит " +
            "из Еditing News в  \"Control panel\"  ")
    public void cancelingNewsEditing() {
        String text = textSymbol(5);
        String Category = randomCategory();
        int position = randomClaims( 1);
        SystemClock.sleep(3000);

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);

        String nameNewsItWas = editingNewsScreenStep.nameNews();
        String publicationDateNewsItWas = editingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWas = editingNewsScreenStep.creationDateNews();
        String descriptionNewsItWas = editingNewsScreenStep.descriptionNews();

        editingNewsScreenStep.clickingOnTheButtonToEnterTheNewsEditing(nameNewsItWas);
        SystemClock.sleep(2000);
        editingNewsScreenStep.fillingInTheNewsFieldsWithNewData(text, Category);

        String nameNewsInput = editingNewsScreenStep.titleTextNewsField();
        String publicationDateNewsInput = editingNewsScreenStep.publishDateField();
        String timeNewsInput = editingNewsScreenStep.timeField();
        String descriptionNewsInput = editingNewsScreenStep.descriptionField();

        editingNewsScreenStep.clickingOnTheCancelNewsEditingButton();
        editingNewsScreenStep.clickingOnTheButtonToConfirmTheCancellationOfNewsEditing();

        String nameNewsItWasHasBecomes = editingNewsScreenStep.nameNews();
        String publicationDateNewsItWasHasBecomes = editingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWasHasBecomes = editingNewsScreenStep.creationDateNews();
        String descriptionNewsItWasHasBecomes = editingNewsScreenStep.descriptionNews();
        SystemClock.sleep(2000);
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);

        editingNewsScreenStep.checkingNewsDataBeforeEditingAndAfterCancelingEditing(
                nameNewsItWas, nameNewsInput, publicationDateNewsItWas, publicationDateNewsInput,
                creationDateNewsItWas, timeNewsInput, descriptionNewsItWas, descriptionNewsInput,
                nameNewsItWasHasBecomes, publicationDateNewsItWasHasBecomes, creationDateNewsItWasHasBecomes,
                descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("A warning message should appear if the fields are empty when clicking on the save button")
    @Description("В этом тест кейсе мы проверяем, что при незаполненном, незаполненных полях появляется предупреждающее сообщение, после нажатия на кнопку \"SAVE\"  \"fill empty fields\"  ")
    public void aWarningMessageShouldAppearIfTheFieldsAreEmptyWhenClickingOnTheSaveButton() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.deletingTheNewsTitle();
        editingNewsScreenStep.clickingOnTheSaveButton();
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Fill empty fields")));
    }

    @Test
    @DisplayName("A warning message should appear when filling in the Category field")
    @Description("В этом тест кейсе мы проверяем, что при заполненнии поле Category категорией не из списка, после нажатия на кнопку \"SAVE\" появляется предупреждающая надпись \"Saving failed. Try again later.\"  ")
    public void aWarningMessageShouldAppearWhenFillingInTheCategoryField() {
        String text = textSymbol(5);

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.enteringTextInTheCategoryField(text);
        SystemClock.sleep(2000);
        editingNewsScreenStep.clickingOnTheSaveButton();
        onView(withText(R.string.error_saving))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Saving failed. Try again later.")));
    }

//    @Test
//    @DisplayName("Icons should appear exclamation mark in a circle of red color")
//    @Description("В этом тест кейсе мы проверяем, что в незаполненных  поле, полях, появляется красный восклецательный знак в круге  цвета красного цвета после нажатия на кнопку \"SAVE\" ")
//    public void iconsShouldAppearExclamationMarkInCircleOfRedColor() {
//        mainScreenStep.switchingToTheControlPanel();
//        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
//        editingNewsScreenStep.deletingTheNewsTitle();
//        SystemClock.sleep(2000);
//        editingNewsScreenStep.clickingOnTheSaveButton();
//        //доделать
//    }

    @Test
    @DisplayName("The fields must be filled in with English letters")
    @Description("В этом тест кейсе мы проверяем, что поля заполняются латинскими буквами ")
    public void theFieldsMustBeFilledInWithEnglishLetters() {
        String validLanguageText = "Hello world";

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.validLanguage(validLanguageText, validLanguageText, validLanguageText);
        SystemClock.sleep(3000);
        editingNewsScreenStep.checkingForThePresenceOfWordsFromEnglishLettersInTheFields(validLanguageText);
    }

    @Test
    @DisplayName("The fields must be filled in with Russian letters")
    @Description("В этом тест кейсе мы проверяем, что поля незаполняются нелатинскими буквами")
    public void fieldsShouldNotBeFilledInWithRussianLetters() {
        String invalidLanguageText = "Привет мир";

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        try {
            editingNewsScreenStep.invalidLanguage(invalidLanguageText, invalidLanguageText);
        } catch (RuntimeException expected) {
        } finally {
            editingNewsScreenStep.checkingForTheAbsenceOfWordsFromRussianLettersInTheFields();
        }
    }

    @Test
    @DisplayName("Undo Undo Edit")
    @Description("В этом тест кейсе мы проверяем что при нажатии на кнопку Cancel в всплывшем окне (для подтверждения выхода или отмены выхода) пользователь остается на экране Editing News")
    public void undoUndoEdit() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsEditingScreen();
        editingNewsScreenStep.clickingOnTheCancelNewsEditingButton();
        SystemClock.sleep(3000);
        editingNewsScreenStep.clickingOnTheCancelButtonToExitEditing();
        SystemClock.sleep(3000);
        editingNewsScreenStep.checkingTheNameOfTheEditingNewsScreen();
    }

    @Test
    @DisplayName("The status in the news block in the Control panel should change")
    @Description("В этом тест кейсе мы проверяем что при переключении чек бокса со статусм Not Active на Active в новостной ленте статус меняется с Not Active на Active")
    public void theStatusInTheNewsBlockInTheControlPanelShouldChange() {
        int position = randomClaims(1);
        Helper.setUpStatusNewsNotActive(position);

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);

        SystemClock.sleep(3000);
        String nameNewsItWas = editingNewsScreenStep.nameNews();
        ViewInteraction statusBefore = editingNewsScreenStep.statusNews(nameNewsItWas);

        editingNewsScreenStep.clickingOnTheNews(nameNewsItWas);
        SystemClock.sleep(3000);
        editingNewsScreenStep.clickingOnTheCheckBox();
        editingNewsScreenStep.clickingOnTheSaveButton();

        String statusAfter = editingNewsScreenStep.statusNewsText(nameNewsItWas);
        ViewInteraction statusAfter2 = editingNewsScreenStep.statusNewsPosition(position);
        SystemClock.sleep(3000);

        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        controlPanelScreenStep.checkingTheStatusChange(statusBefore.toString(), statusAfter, statusAfter2);
    }
}
