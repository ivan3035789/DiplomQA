package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate2;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidGeneratorDate;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.createNews;
import static ru.iteco.fmhandroid.ui.data.Helper.createNewsForCategory;
import static ru.iteco.fmhandroid.ui.data.Helper.deletingNewsUpToTheNumberOfTenControlPanelScreen;
import static ru.iteco.fmhandroid.ui.data.Helper.setUpStatusNewsNotActive;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.step.AdvancedNewsSearchScreenStep;
import ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import ru.iteco.fmhandroid.ui.step.ControlPanelScreenStep;
import ru.iteco.fmhandroid.ui.step.MainScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AdvancedNewsSearchTest {

    MainScreenStep mainScreenStep = new MainScreenStep();
    ControlPanelScreenStep controlPanelScreenStep = new ControlPanelScreenStep();
    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    AdvancedNewsSearchScreenStep advancedNewsSearchScreenStep = new AdvancedNewsSearchScreenStep();

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
        deletingNewsUpToTheNumberOfTenControlPanelScreen(7);
        SystemClock.sleep(5000);
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("The screen should have name")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ???????????????? ???????????? Filter news")
    public void theScreenShouldHaveName() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
    }

    @Test
    @DisplayName("the fields must have names")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ???????????????? ??????????")
    public void theFieldsMustHaveNames() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        advancedNewsSearchScreenStep.checkingTheVisibilityOfIdentifyingFieldNames();
    }

    @Test
    @DisplayName("there must be check boxes")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ???????????????? ?????? ????????????")
    public void thereMustBeCheckBoxes() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        advancedNewsSearchScreenStep.checkingTheVisibilityOfTheNamesOfCheckBoxes();
    }

    @Test
    @DisplayName("Must search for news in the list of news blocks using FilterNews according to the specified criteria in all fields")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ???? ???????? ???????????????? ?????????????????? ?? ?????????? ?? ?????????????? \"Filter news\"")
    public void mustSearchForNewsByCriteriaInAllFields() throws ParseException {
        int position = 0;
        String category = randomCategory();
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();
        String text = Helper.Text.textSymbol(5);
        createNewsForCategory(text, category);

        SystemClock.sleep(3000);
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        SystemClock.sleep(3000);

        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        advancedNewsSearchScreenStep.fillingInFieldsWithSearchData(category, dateStartInput, dateEndInput);
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();

        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        String dateOnCardNews = controlPanelScreenStep.dateOnCardNews(position);
        controlPanelScreenStep.comparisonOfSearchDataWithNewsData(dateOnCardNews, dateStartInput, dateEndInput, position, category);
    }

    @Test
    @DisplayName("Must search by Category")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ???? ??????????????????")
    public void mustSearchByCategory() {
        String category = randomCategory();
        int position = 0;
        String text = Helper.Text.textSymbol(5);
        createNewsForCategory(text, category);

        SystemClock.sleep(3000);
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        SystemClock.sleep(3000);

        advancedNewsSearchScreenStep.fillingInTheCategoryField(category);
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();

        controlPanelScreenStep.checkingTheEnteredDataForTheSearchWithThoseObtainedFromTheNews(category, position);
    }

    @Test
    @DisplayName("Must search by date")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ???? ????????")
    public void mustSearchByDate() throws ParseException {
        int position = random(1, 2);
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();

        mainScreenStep.switchingToTheControlPanel();
        SystemClock.sleep(3000);

        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.fillingInFieldsForDateSearch(dateStartInput, dateEndInput);
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();

        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        controlPanelScreenStep.comparisonOfSearchDataByDateWithNewsData(position, dateStartInput, dateEndInput);
    }

    @Test
    @DisplayName("Must find all the news when performing a search without entering data")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ?????? ?????????????????? ?????????????????? ?????? ????????????")
    public void mustFindAllTheNewsWhenPerformingSearchWithoutEnteringData() {
        int position = random( 1, 2);

        mainScreenStep.switchingToTheControlPanel();
        SystemClock.sleep(3000);

        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        controlPanelScreenStep.checkingTheFoundNews(position);
    }

    @Test
    @DisplayName("Canceling the search")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ???????????? ????????????")
    public void cancelingTheSearch() {
        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheCancelSearchButton();
        controlPanelScreenStep.checkingTheNameOfTheControlPanelScreen();
    }

    @Test
    @DisplayName("A window should appear with the inscription news not found")
    @Description("?? ???????? ???????? ?????????? ???? ??????????????????, ?????? ?????? ???????????????????? ???????????????????? ???? ?????????????????? ???????????? ??????????????, ???????????????????? ???????? ?? ???????????????? \"There is nothing here yet...\"")
    public void aWindowShouldAppearWithTheInscriptionNewsNotFound() {
        String dateStartInput = invalidGeneratorDate();
        String dateEndInput = invalidGeneratorDate();

        mainScreenStep.switchingToTheControlPanel();

        SystemClock.sleep(3000);
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.fillingInTheFieldsForTheDate(dateStartInput, dateEndInput);
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();
        advancedNewsSearchScreenStep.checkingTheTextWhenNewsIsNotFound();
    }

    @Test
    @DisplayName("must find news with active status")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ???? ?????????????? Active")
    public void mustFindNewsWithActiveStatus() {
        int position = randomNews( 1);

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        SystemClock.sleep(3000);

        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        advancedNewsSearchScreenStep.checkingTheNameOfTheAdvancedSearchScreen();
        advancedNewsSearchScreenStep.clickingOnTheActiveCheckBox();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();

        SystemClock.sleep(3000);
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        SystemClock.sleep(3000);
        controlPanelScreenStep.CheckingTheStatusActive(position);
    }

    @Test
    @DisplayName("must find news with the status not active")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ???? ?????????????? Not active")
    public void mustFindNewsWithTheStatusNotActive() {
        String category = randomCategory();
        int position = randomNews(0);
        String text = Helper.Text.textSymbol(5);
        createNews(text, category);
        SystemClock.sleep(3000);
        setUpStatusNewsNotActive(0);

        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheNotActiveCheckBox();
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();

        SystemClock.sleep(3000);
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        controlPanelScreenStep.CheckingTheStatusNotActive(position);
    }

    @Test
    @DisplayName("Must search by Status and Active Not active")
    @Description("?? ???????? ???????? ?????????? ???? ?????????????????? ?????????? ?????????????? ???? ???? ????????????????  Active ?? Not active")
    public void mustSearchByStatusAndActiveNotActive() {
        int position = randomClaims(0, 1);

        mainScreenStep.switchingToTheControlPanel();
        controlPanelScreenStep.pressingTheButtonToGoToTheAdvancedNewsSearchScreen();
        SystemClock.sleep(3000);
        advancedNewsSearchScreenStep.clickingOnTheFilterButtonToSearchForNews();

        SystemClock.sleep(3000);
        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        controlPanelScreenStep.checkingTheStatusOfTheFoundNews(position);
    }
}

