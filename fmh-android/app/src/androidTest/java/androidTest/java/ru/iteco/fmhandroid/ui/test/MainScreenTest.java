package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
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

import androidTest.java.ru.iteco.fmhandroid.ui.step.AboutScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.CreatingClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.NewsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ThematicQuotesScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainScreenTest {

    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    MainScreenStep mainScreenStep = new MainScreenStep();
    NewsScreenStep newsScreenStep = new NewsScreenStep();
    AboutScreenStep aboutScreenStep = new AboutScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    CreatingClaimsScreenStep creatingClaimsScreenStep = new CreatingClaimsScreenStep();
    ThematicQuotesScreenStep thematicQuotesScreenStep = new ThematicQuotesScreenStep();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenStep.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.validLoginPassword(authInfo());
            SystemClock.sleep(8000);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("must Go To The Screen News")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"Action menu\", должно появиться окно с" +
            " выбором страниц, при нажатии на каждую выбранную страницу, должен происходит переход на нее, выбранная" +
            " страница при этом после перехода нее должна сменить цвет на серый в списке страниц \"Action menu")
    public void mustGoToTheScreenNews() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        SystemClock.sleep(3000);
        newsScreenStep.checkTheNameOfTheNewsScreen();
    }

    @Test
    @DisplayName("must Go To The Screen Main")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"Action menu\", должно появиться окно с" +
            " выбором страниц, при нажатии на каждую выбранную страницу, должен происходит переход на нее, выбранная" +
            " страница при этом после перехода нее должна сменить цвет на серый в списке страниц \"Action menu")
    public void mustGoToTheScreenMain() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheMainName();
        SystemClock.sleep(3000);
        mainScreenStep.checkTheNameOfTheNewsBlock();
        mainScreenStep.checkTheNameOfTheClaimsBlock();
    }

    @Test
    @DisplayName("must Go To The Screen Claims")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"Action menu\", должно появиться окно с" +
            " выбором страниц, при нажатии на каждую выбранную страницу, должен происходит переход на нее, выбранная" +
            " страница при этом после перехода нее должна сменить цвет на серый в списке страниц \"Action menu")
    public void mustGoToTheScreenClaims() {
        mainScreenStep.clickingOnTheClaimsName();
        SystemClock.sleep(3000);
        claimsScreenStep.checkScreenNameClaims();
    }

    @Test
    @DisplayName("must Go To The Screen About")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"Action menu\", должно появиться окно с" +
            " выбором страниц, при нажатии на каждую выбранную страницу, должен происходит переход на нее, выбранная" +
            " страница при этом после перехода нее должна сменить цвет на серый в списке страниц \"Action menu")
    public void mustGoToTheScreenAbout() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheAboutName();
        SystemClock.sleep(3000);
        aboutScreenStep.checkScreenNameAbout();
    }

    @Test
    @DisplayName("Should go to the News Screen when clicking on all News")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на текстовую ссылку  \"all news\", происходит переход на страницу \"News\" ")
    public void shouldGoToTheNewsScreenWhenClickingOnAllNews() {
        mainScreenStep.clickingOnTheAllNewsTextLink();
        SystemClock.sleep(3000);
        newsScreenStep.checkTheNameOfTheNewsScreen();
    }

    @Test
    @DisplayName("Should go to the Claims Screen when clicking on all Claims")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на текстовую ссылку  \"all claims\", происходит переход на страницу \"Claims\"")
    public void shouldGoToTheClaimsScreenWhenClickingOnAllClaims() {
        mainScreenStep.clickingOnTheAllClaimsTextLink();
        SystemClock.sleep(3000);
        claimsScreenStep.checkScreenNameClaims();
    }

    @Test
    @DisplayName("Should go to the Create Claims Screen")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"+\", происходит переход в \"Creating Claims\"")
    public void shouldGoToTheCreateClaimsScreen() {
        mainScreenStep.clickingOnTheButtonToGoToTheClaimCreationScreen();
        SystemClock.sleep(5000);
        creatingClaimsScreenStep.checkingTheNameOfTheClaimCreationScreen();
    }

    @Test
    @DisplayName("should go to the Screen with thematic quotes")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку с иконкой \"бабочки\" осуществляется переход на страницу \"LOVE IS ALL\" (переход должен осуществляться с любой из страниц ")
    public void shouldGoToTheScreenWithThematicQuotes() {
        mainScreenStep.pressingTheButtonInTheFormOfButterfly();
        SystemClock.sleep(3000);
        thematicQuotesScreenStep.checkingTheScreenName();
    }

    @Test
    @DisplayName("it is necessary To Expand Collapse The News Feed")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку с выпадающим окном, разворачивается, сварачивается блок \"News\"")
    public void itIsNecessaryToExpandCollapseTheNewsFeed() {
        mainScreenStep.clickingOnTheButtonExpandTheNewsFeed();
        SystemClock.sleep(3000);
        mainScreenStep.theNameAllNewsIsNotVisible();
        mainScreenStep.clickingOnTheButtonExpandTheNewsFeed();
        mainScreenStep.theNameAllNewsIsVisible();
    }

    @Test
    @DisplayName("it is necessary To Expand Collapse The Tape With claims")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку с выпадающим окном, разворачивается, сварачивается блок \"Claims\"")
    public void itIsNecessaryToExpandCollapseTheTapeWithClaims() {
        mainScreenStep.expandTheClaimsFeed();
        mainScreenStep.theNameAllClaimsIsNotVisible();
        SystemClock.sleep(3000);
        mainScreenStep.expandTheClaimsFeed();
        mainScreenStep.theNameAllClaimsIsVisible();
    }

    @Test
    @DisplayName("a description should appear at the news")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"свернуть, развернуть\" в новостном блоке, разворачивается, сварачивается (появляется) описание новости ")
    public void aDescriptionShouldAppearAtTheNews() {
        int position = random(0, 1, 2);

        mainScreenStep.clickingOnTheExpandNewsDescriptionButton(position);
        mainScreenStep.checkingTheTextOfTheNewsDescriptionIsVisible(position);
    }

    @Test
    @DisplayName("should be included in the claim")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на \"Претензию\" в блоке claims, пользователь переходит в \"Претензию\"")
    public void shouldBeIncludedInTheClaim() {
        int position = 0;

        mainScreenStep.swipeUpBlockClaims();
        mainScreenStep.clickingOnTheFirstClaimInTheList(position);
        claimsScreenStep.verificationOfIdentifyingNamesInTheClaim();
    }
}
