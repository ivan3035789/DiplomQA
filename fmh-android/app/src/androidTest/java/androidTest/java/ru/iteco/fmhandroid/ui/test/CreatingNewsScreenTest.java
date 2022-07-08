package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
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

import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ControlPanelScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.CreatingNewsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.WatchScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreatingNewsScreenTest {

    MainScreenStep mainScreenStep = new MainScreenStep();
    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    ControlPanelScreenStep controlPanelScreenStep = new ControlPanelScreenStep();
    CreatingNewsScreenStep creatingNewsScreenStep = new CreatingNewsScreenStep();
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
        mainScreenStep.randomTransitionToCreatingNews();
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("У экрана должно быть название")
    @Description("В этом тест кейсе мы проверяем название экрана Creating News")
    public void theScreenShouldHaveName() {
        creatingNewsScreenStep.checkingTheNameOfTheCreatingNewsScreen();
    }

    @Test
    @DisplayName("the fields must have names")
    @Description("В этом тест кейсе мы проверяем названия полей")
    public void FieldsMustHaveNames() {
        creatingNewsScreenStep.checkNameFieldInCreatingNews();
    }

    @Test
    @DisplayName("A drop-down list with categories should appear")
    @Description("В этом тест кейсе мы проверяем, что при клике на поле \"Category\" появляется выпадающий список с доспупными категориями ")
    public void aDropDownListWithCategoriesShouldAppear() {
        creatingNewsScreenStep.clickingOnTheCategoryField();
        SystemClock.sleep(2000);
        onView(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("A calendar should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Publication date\" появляется календарь ")
    public void aCalendarShouldAppear() {
        creatingNewsScreenStep.clickingOnTheDateField();
        onView(withClassName(is("android.widget.DatePicker")))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("A clock of the arrow type should appear")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на поле \"Time\" появляется часы стрелочного типа")
    public void aClockOfTheArrowTypeShouldAppear() {
        creatingNewsScreenStep.clickingOnTheTimeField();
        onView(withClassName(is("android.widget.RadialTimePickerView")))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("The type of watch should change")
    @Description("В этом тест кейсе мы проверяем возможность выбора типа часов. что при нажатии на кнопку с иконкой \"клавиатура\" должен поменяться вид часов")
    public void theTypeOfWatchShouldChange() {
        SystemClock.sleep(3000);
        creatingNewsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();
    }

    @Test
    @DisplayName("News should be created")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"SAVE\" после заполнения полей \"Title, Time, Description, Publicftion date\" валидными значениями, должна создаться новость (появиться в ленте новостей на странице \"News, Main\", а так же в \"Control panel\"")
    public void newsShouldBeCreated() {
        int position = 0;
        String text = textSymbol(5);

        creatingNewsScreenStep.clickingOnTheExitButtonFromNewsCreation();
        creatingNewsScreenStep.clickingOnTheConfirmationButtonToExitTheNewsCreation();
        creatingNewsScreenStep.choosingNews(position);

        String nameNewsItWas = creatingNewsScreenStep.nameNews();
        String publicationDateNewsItWas = creatingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWas = creatingNewsScreenStep.creationDateNews();
        String authorNewsItWas = creatingNewsScreenStep.authorNews();
        String descriptionNewsItWas = creatingNewsScreenStep.descriptionNews();

        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsCreationScreen();
        creatingNewsScreenStep.fillingInFieldsWithValidData(text);
        creatingNewsScreenStep.clickingOnTheSaveNewsButton();
        creatingNewsScreenStep.choosingNews(position);

        String nameNewsItWasHasBecomes = creatingNewsScreenStep.nameNews();
        String publicationDateNewsItWasHasBecomes = creatingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWasHasBecomes = creatingNewsScreenStep.creationDateNews();
        String authorNewsItWasHasBecomes = creatingNewsScreenStep.authorNews();
        String descriptionNewsItWasHasBecomes = creatingNewsScreenStep.descriptionNews();

        creatingNewsScreenStep.comparingTheDataOfTheCreatedNewsWithTheDataOfTheFirstNewsFromTheList(
                nameNewsItWas, nameNewsItWasHasBecomes, publicationDateNewsItWas, publicationDateNewsItWasHasBecomes,
                creationDateNewsItWas, creationDateNewsItWasHasBecomes, authorNewsItWas, authorNewsItWasHasBecomes,
                descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("Canceling news creation")
    @Description("В этом тест кейсе мы проверяем отмену создания новости при нажатии на кнопку \"CANCEL\"")
    public void cancelingNewsCreation() {
        int position = 0;
        String text = textSymbol(5);

        creatingNewsScreenStep.clickingOnTheExitButtonFromNewsCreation();
        creatingNewsScreenStep.clickingOnTheConfirmationButtonToExitTheNewsCreation();
        creatingNewsScreenStep.choosingNews(position);

        String nameNewsItWas = creatingNewsScreenStep.nameNews();
        String publicationDateNewsItWas = creatingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWas = creatingNewsScreenStep.creationDateNews();
        String authorNewsItWas = creatingNewsScreenStep.authorNews();
        String descriptionNewsItWas = creatingNewsScreenStep.descriptionNews();

        controlPanelScreenStep.clickingOnTheButtonToGoToTheNewsCreationScreen();
        creatingNewsScreenStep.fillingInFieldsWithValidData(text);
        creatingNewsScreenStep.clickingOnTheExitButtonFromNewsCreation();
        creatingNewsScreenStep.clickingOnTheConfirmationButtonToCancelTheCreationOfTheNews();

        creatingNewsScreenStep.choosingNews(position);

        String nameNewsItWasHasBecomes = creatingNewsScreenStep.nameNews();
        String publicationDateNewsItWasHasBecomes = creatingNewsScreenStep.publicationDateNews();
        String creationDateNewsItWasHasBecomes = creatingNewsScreenStep.creationDateNews();
        String authorNewsItWasHasBecomes = creatingNewsScreenStep.authorNews();
        String descriptionNewsItWasHasBecomes = creatingNewsScreenStep.descriptionNews();

        creatingNewsScreenStep.checkingTheDataOfTheFirstNewsFromTheListMustMatchAfterCancelingTheCreationOfTheNews(
                nameNewsItWas, nameNewsItWasHasBecomes, publicationDateNewsItWas, publicationDateNewsItWasHasBecomes,
                creationDateNewsItWas, creationDateNewsItWasHasBecomes, authorNewsItWas, authorNewsItWasHasBecomes,
                descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Test
    @DisplayName("A warning message should appear if the fields are empty when you click on the SAVE button")
    @Description("В этом тест кейсе мы проверяем, что при незаполненном, незаполненных полях появляется предупреждающее сообщение, после нажатия на кнопку \"SAVE\"  \"fill empty fields\" ")
    public void aWarningMessageShouldAppearIfTheFieldsAreEmptyWhenYouClickOnTheSaveButton() {
        creatingNewsScreenStep.clickingOnTheSaveNewsButton();
        SystemClock.sleep(2000);
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Fill empty fields")));
    }

    @Test
    @DisplayName("A warning message should appear when filling in the Category field")
    @Description("В этом тест кейсе мы проверяем, что при заполнении поля Category и последующим нажатием на кнопку сохранения появляется предупреждающая надпись")
    public void aWarningMessageShouldAppearWhenFillingInTheCategoryField() {
        String text = textSymbol(5);

        creatingNewsScreenStep.fillingInTheCategoryField(text);
        creatingNewsScreenStep.clickingOnTheSaveNewsButton();
        onView(withText(R.string.error_saving))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Saving failed. Try again later.")));
    }

    @Test
    @DisplayName("The fields must be filled in with English letters")
    @Description("В этом тест кейсе мы проверяем, что поля заполняются латинскими буквами")
    public void theFieldsMustBeFilledInWithEnglishLetters() {
        String validLanguageText = "hello world";

        creatingNewsScreenStep.validLanguage(validLanguageText, validLanguageText, validLanguageText);
        creatingNewsScreenStep.checkingForThePresenceOfWordsFromEnglishLettersInTheFields(validLanguageText);
    }

    @Test
    @DisplayName("Fields should not be filled in with Russian letters")
    @Description("В этом тест кейсе мы проверяем, что поля незаполняются нелатинскими буквами")
    public void fieldsShouldNotBeFilledInWithRussianLetters() {
        String invalidLanguageText = "привет мир";
        try {
            creatingNewsScreenStep.invalidLanguage(invalidLanguageText, invalidLanguageText);
        } catch (RuntimeException expected) {
        } finally {
            creatingNewsScreenStep.checkingForTheAbsenceOfWordsFromRussianLettersInTheFields(invalidLanguageText);
        }
    }
}
