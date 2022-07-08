package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.generatorDate2;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidGeneratorDate;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.localDate;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomNews;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.authInfo;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.createNewsForCategory;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.deletingNewsUpToTheNumberOfTenControlPanelScreen;

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

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ControlPanelScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.FilterNewsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.NewsScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsScreenTest {

    MainScreenStep mainScreenStep = new MainScreenStep();
    NewsScreenStep newsScreenStep = new NewsScreenStep();
    ControlPanelScreenStep controlPanelScreenStep = new ControlPanelScreenStep();
    FilterNewsScreenStep filterNewsScreenStep = new FilterNewsScreenStep();
    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenStep.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.validLoginPassword(authInfo());
        }
        deletingNewsUpToTheNumberOfTenControlPanelScreen(7);
        SystemClock.sleep(5000);
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("У экрана должно быть название")
    @Description("В этом тест кейсе мы проверяем название экрана Filter News")
    public void theScreenShouldHaveName() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        filterNewsScreenStep.checkingTheScreenNameForNewsSearch();
    }

    @Test // добавить в тест кейсы
    @DisplayName("the fields must have names")
    @Description("В этом тест кейсе мы проверяемя названия полей")
    public void theFieldsMustHaveNames() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        filterNewsScreenStep.checkingIdentifyingFieldNames();
    }

    @Test
    @DisplayName("Must search for news in the list of news blocks using FilterNews according to the specified criteria in all fields")
    @Description("В этом тест кейсе мы проверяем поиск новости по всем заданным критериям в полях в разделе \"Filter news\"")
    public void mustSearchForNewsByCriteriaInAllFields() throws ParseException {
        String localDate = localDate();
        int position = random(0);
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();
        String category = randomCategory();
        String text = Helper.Text.textSymbol(5);
        createNewsForCategory(text, category);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        SystemClock.sleep(3000);

        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        SystemClock.sleep(3000);
        filterNewsScreenStep.checkingTheScreenNameForNewsSearch();
        SystemClock.sleep(3000);

        filterNewsScreenStep.enteringSearchData(category, dateStartInput, dateEndInput);
        filterNewsScreenStep.clickingOnTheSearchButton();

        controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(position);
        String dateOnCardNews = newsScreenStep.dateOnCardNews(position);

        newsScreenStep.checkingTheFoundDataFromTheNewsWithTheDataEnteredForTheSearch(
                dateOnCardNews, dateStartInput, dateEndInput, category, localDate, position);
    }

    @Test
    @DisplayName("Must search by Category")
    @Description("В этом тест кейсе мы проверяем поиск новости по категории")
    public void mustSearchByCategory() {
        int position = randomNews(0);
        String category = randomCategory();
        String text = Helper.Text.textSymbol(5);
        createNewsForCategory(text, category);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        SystemClock.sleep(3000);

        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        SystemClock.sleep(3000);
        filterNewsScreenStep.checkingTheScreenNameForNewsSearch();
        SystemClock.sleep(3000);

        filterNewsScreenStep.enteringCategory(category);
        SystemClock.sleep(3000);
        filterNewsScreenStep.clickingOnTheSearchButton();

        String categoryText = newsScreenStep.categoryText(position);
        newsScreenStep.checkingTheDataOfTheFoundNewsWithTheEnteredSearchData(category, categoryText);
    }

    @Test
    @DisplayName("Must search by date")
    @Description("В этом тест кейсе мы проверяем поиск новости по дате")
    public void mustSearchByDate() throws ParseException {
        int position = randomNews(0, 1, 2);
        String localDate = localDate();
        String dateStartInput = generatorDate2();
        String dateEndInput = generatorDate();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        SystemClock.sleep(3000);
        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        SystemClock.sleep(3000);
        filterNewsScreenStep.checkingTheScreenNameForNewsSearch();
        SystemClock.sleep(3000);
        filterNewsScreenStep.enteringTheStartDate(dateStartInput);
        SystemClock.sleep(3000);
        filterNewsScreenStep.enteringTheEndOfTheDate(dateEndInput);
        SystemClock.sleep(3000);
        filterNewsScreenStep.clickingOnTheSearchButton();

        newsScreenStep.clickingOnTheNews(position);
        String dateOnCardNews = newsScreenStep.dateOnCardNews(position);

        newsScreenStep.checkingTheDateOfTheFoundNewsWithTheDataEnteredForTheSearch(
                dateOnCardNews, dateStartInput, dateEndInput, localDate);
    }

    @Test
    @DisplayName("Must find all the news when performing a search without entering data")
    @Description("В этом тест кейсе мы проверяем поиск новости без установки критериев для поиска")
    public void mustFindAllTheNewsWhenPerformingSearchWithoutEnteringData() {
        int position = randomNews(0, 1, 2);

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        SystemClock.sleep(3000);
        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        filterNewsScreenStep.clickingOnTheSearchButton();
        newsScreenStep.clickingOnTheNews(position);
        try {
            newsScreenStep.checkingTheDisplayOfTheFoundNewsData(position);
        } catch (NoMatchingViewException e) {
            newsScreenStep.checkingTheDisplayOfTheInscriptionInTheAbsenceOfFoundNews();
        }
    }

    @Test
    @DisplayName("Canceling the search")
    @Description("В этом тест кейсе мы проверяем отмену поиска ")
    public void cancelingTheSearch() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        SystemClock.sleep(3000);
        filterNewsScreenStep.checkingTheScreenNameForNewsSearch();
        SystemClock.sleep(3000);
        filterNewsScreenStep.clickingOnTheCancelSearchButton();
        newsScreenStep.checkTheNameOfTheNewsScreen();
    }

    @Test
    @DisplayName("A window should appear with the inscription news not found")
    @Description("В этом тест кейсе мы проверяем, что при отсутствии подходящей по критериям поиска новости, появляется окно с надписью \"There is nothing here yet...\" ")
    public void aWindowShouldAppearWithTheInscriptionNewsNotFound() {
        String dateStartInput = invalidGeneratorDate();
        String dateEndInput = invalidGeneratorDate();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        SystemClock.sleep(3000);
        newsScreenStep.clickingOnTheButtonToGoToFilterNews();
        SystemClock.sleep(3000);
        filterNewsScreenStep.checkingTheScreenNameForNewsSearch();
        SystemClock.sleep(3000);
        filterNewsScreenStep.enteringDates(dateStartInput, dateEndInput);
        SystemClock.sleep(3000);
        filterNewsScreenStep.clickingOnTheSearchButton();
        newsScreenStep.checkingTheDisplayOfTheInscriptionInTheAbsenceOfFoundNews();
    }
}
