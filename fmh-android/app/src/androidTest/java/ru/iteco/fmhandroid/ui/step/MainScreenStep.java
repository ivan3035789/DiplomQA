package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomLogInToClaimsCreation;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomLogInToNewsCreation;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.CreatingClaimsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.CreatingNewsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.MainScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.NewsScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainScreenStep {

    MainScreenElements mainScreenElements = new MainScreenElements();

    public void clickingOnTheFirstClaimInTheList(int position) {
        Allure.step("Нажатие на первую претензию в списке");
        mainScreenElements.getClaimList().perform(actionOnItemAtPosition(position, click()));
    }

    public void clickingOnAllClaims() {
        Allure.step("Нажатие на all Claims");
        mainScreenElements.getAllClaims().perform(click());
    }

    public void clickingOnTheActionMenuButton() {
        Allure.step("Нажатие на кнопку ActionMenu");
        mainScreenElements.getMainMenuImageButton().perform(click());
    }

    public void clickingOnTheNewsName() {
        Allure.step("Нажатие на название News");
        mainScreenElements.getTitleNews().perform(click());
    }

    public void clickingOnTheMainName() {
        Allure.step("Нажатие на название Main");
        mainScreenElements.getTitleMain().perform(click());
    }

    public void clickingOnTheClaimsName() {
        Allure.step("Нажатие на название Claims");
        mainScreenElements.getTitleClaimsScreen().perform(click());
    }

    public void clickingOnTheAboutName() {
        Allure.step("Нажатие на название About");
        mainScreenElements.getTitleAbout().perform(click());
    }

    public void clickingOnTheAllNewsTextLink() {
        Allure.step("Нажатие на текстовую ссылку AllNews");
        mainScreenElements.getAllNews().perform(click());
    }

    public void clickingOnTheAllClaimsTextLink() {
        Allure.step("Нажатие на текстовую ссылку AllClaims");
        mainScreenElements.getAllClaims().perform(click());
    }

    public void pressingTheButtonInTheFormOfButterfly() {
        Allure.step("Нажатие на кнопку в виде бабочки");
        mainScreenElements.getThematicQuotesButton().perform(click());
    }

    public void clickingOnTheButtonToGoToTheClaimCreationScreen() {
        Allure.step("Нажатие на кнопку +");
        mainScreenElements.getCreateClaimsButton().perform(click());
    }

    public void clickingOnTheButtonExpandTheNewsFeed() {
        Allure.step("Нажатие на кнопку для развертывания/свертывания новостного блока");
        mainScreenElements.getExpandNewsFeedButton().perform(click());
    }

    public void expandTheClaimsFeed() {
        Allure.step("Нажатие на кнопку для развертывания/свертывания блока с претензиями");
        mainScreenElements.getExpandTheClaimsFeedButton().perform(click());
    }

    public void clickingOnTheExpandNewsDescriptionButton(int position) {
        Allure.step("Нажатие на кнопку развертывания/свертывания описания Новости");
        mainScreenElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
    }

    public void swipeUpBlockClaims() {
        Allure.step("Swipe до первой видемой претензии");
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        SystemClock.sleep(5000);
    }

    public void theNameAllNewsIsVisible() {
        Allure.step("Проверка название в блоке all News видно");
        mainScreenElements.getAllNews().check(matches(isDisplayed()));
    }

    public void theNameAllNewsIsNotVisible() {
        Allure.step("Проверка название в блоке all News не видно");
        mainScreenElements.getAllNews().check(matches(not(isDisplayed())));
    }

    public void theNameAllClaimsIsVisible() {
        Allure.step("Проверка название в блоке all Claims видно");
        mainScreenElements.getAllClaims().check(matches(isDisplayed()));
    }

    public void theNameAllClaimsIsNotVisible() {
        Allure.step("Проверка название в блоке all Claims не видно");
        mainScreenElements.getAllClaims().check(matches(not(isDisplayed())));
    }

    public void checkTheNameOfTheNewsBlock() {
        Allure.step("Проверка названия блока с новостями");
        mainScreenElements.getChapterNews().check(matches(isDisplayed()));
    }

    public void checkTheNameOfTheClaimsBlock() {
        Allure.step("Проверка названия блока с претензиями");
        mainScreenElements.getChapterClaims().check(matches(isDisplayed()));
    }

    public void checkNameMainScreen() {
        Allure.step("Проверка нахождения пользователя на экране Main");
        mainScreenElements.getChapterNews().check(matches(isDisplayed()));
        mainScreenElements.getChapterClaims().check(matches(isDisplayed()));
    }

    public void checkingTheTextOfTheNewsDescriptionIsVisible(int position) {
        Allure.step("Проверка видемости текста описания новости");
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction textNews =  onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        SystemClock.sleep(3000);
        textNews.check(matches(isDisplayed()));
    }

    public void checkingTheNamesOfScreensInTheList() {
        Allure.step("Проверка названий экранов в списке");
        mainScreenElements.getTitleMain().check(matches(isDisplayed()));
        mainScreenElements.getTitleNews().check(matches(isDisplayed()));
        mainScreenElements.getTitleClaimsScreen().check(matches(isDisplayed()));
        mainScreenElements.getTitleAbout().check(matches(isDisplayed()));
    }

    public void randomTransitionToCreatingClaims() {
        Allure.step("Рандомный переход в Creating Claims");
        //добавить шаги в тесты
        randomLogInToClaimsCreation();
    }

    public void randomTransitionToCreatingNews() {
        Allure.step("Рандомный переход в Creating News");
        //добавить шаги в тесты
        randomLogInToNewsCreation();
    }

    public void switchingToTheControlPanel() {
        Allure.step("Переход в Control Panel");
        MainScreenStep mainScreenStep = new MainScreenStep();
        NewsScreenStep newsScreenStep = new NewsScreenStep();
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenStep.clickingOnTheButtonToGoToTheControlPanel();
    }

    public static void enterCreateClaimsAllClaims() {
        MainScreenElements mainScreenElements = new MainScreenElements();
        ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
        CreatingClaimsScreenElements creatingClaimsScreenElements = new CreatingClaimsScreenElements();

        mainScreenElements.getAllClaims().perform(click());
        claimsScreenElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(withText("Creating"))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(withText("Claims"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateClaimsActionButton() {
        MainScreenElements mainScreenElements = new MainScreenElements();
        ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
        CreatingClaimsScreenElements creatingClaimsScreenElements = new CreatingClaimsScreenElements();

        mainScreenElements.getMainMenuImageButton().perform(click());
        mainScreenElements.getTitleClaimsScreen().perform(click());
        SystemClock.sleep(3000);
        claimsScreenElements.getCreateClaimsButton().perform(click());
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateClaimsButtonPlus() {
        MainScreenElements mainScreenElements = new MainScreenElements();
        CreatingClaimsScreenElements creatingClaimsScreenElements = new CreatingClaimsScreenElements();

        SystemClock.sleep(2000);
        mainScreenElements.getCreateClaimsButton().perform(click());
        SystemClock.sleep(3000);
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(withText("Creating"))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(withText("Claims"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    public static void enterCreateNews1() {
        MainScreenElements mainScreenElements = new MainScreenElements();
        NewsScreenElements newsScreenElements = new NewsScreenElements();
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();
        CreatingNewsScreenElements creatingNewsScreenElements = new CreatingNewsScreenElements();

        mainScreenElements.getAllNews().perform(click());
        SystemClock.sleep(3000);
        newsScreenElements.getScreenNameNews().check(matches(withText("News"))).check(matches(isDisplayed()));
        newsScreenElements.getEditButton().perform(click());
        controlPanelScreenElements.getControlPanelNameScreen().check(matches(withText("Control panel"))).check(matches(isDisplayed()));
        controlPanelScreenElements.getCreateNewsButton().perform(click());
        SystemClock.sleep(5000);
        creatingNewsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsScreenElements.getNewsNameScreen().check(matches(isDisplayed()));
    }

    public static void enterCreateNews2() {
        MainScreenElements mainScreenElements = new MainScreenElements();
        NewsScreenElements newsScreenElements = new NewsScreenElements();
        CreatingNewsScreenElements creatingNewsScreenElements = new CreatingNewsScreenElements();
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();

        mainScreenElements.getMainMenuImageButton().perform(click());
        mainScreenElements.getTitleNews().perform(click());
        newsScreenElements.getScreenNameNews().check(matches(withText("News"))).check(matches(isDisplayed()));
        newsScreenElements.getEditButton().perform(click());
        controlPanelScreenElements.getControlPanelNameScreen().check(matches(withText("Control panel"))).check(matches(isDisplayed()));
        controlPanelScreenElements.getCreateNewsButton().perform(click());
        SystemClock.sleep(5000);
        creatingNewsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsScreenElements.getNewsNameScreen().check(matches(isDisplayed()));
    }
}
