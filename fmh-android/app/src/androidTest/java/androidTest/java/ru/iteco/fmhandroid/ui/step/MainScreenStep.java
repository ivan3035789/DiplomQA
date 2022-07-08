package androidTest.java.ru.iteco.fmhandroid.ui.step;

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
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomLogInToClaimsCreation;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomLogInToNewsCreation;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CreatingClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CreatingNewsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.MainScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.NewsScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class MainScreenStep {

    MainScreenElements mainScreenElements = new MainScreenElements();

    @Step("Нажатие на первую претензию в списке")
    public void clickingOnTheFirstClaimInTheList(int position) {
        mainScreenElements.getClaimList().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Нажатие на all Claims")
    public void clickingOnAllClaims() {
        mainScreenElements.getAllClaims().perform(click());
    }

    @Step("Нажатие на кнопку ActionMenu")
    public void clickingOnTheActionMenuButton() {
        mainScreenElements.getMainMenuImageButton().perform(click());
    }

    @Step("Нажатие на название News")
    public void clickingOnTheNewsName() {
        mainScreenElements.getTitleNews().perform(click());
    }

    @Step("Нажатие на название Main")
    public void clickingOnTheMainName() {
        mainScreenElements.getTitleMain().perform(click());
    }

    @Step("Нажатие на название Claims")
    public void clickingOnTheClaimsName() {
        mainScreenElements.getTitleClaimsScreen().perform(click());
    }

    @Step("Нажатие на название About")
    public void clickingOnTheAboutName() {
        mainScreenElements.getTitleAbout().perform(click());
    }

    @Step("Нажатие на текстовую ссылку AllNews")
    public void clickingOnTheAllNewsTextLink() {
        mainScreenElements.getAllNews().perform(click());
    }

    @Step("Нажатие на текстовую ссылку AllClaims")
    public void clickingOnTheAllClaimsTextLink() {
        mainScreenElements.getAllClaims().perform(click());
    }

    @Step("Нажатие на кнопку в виде бабочки")
    public void pressingTheButtonInTheFormOfButterfly() {
        mainScreenElements.getThematicQuotesButton().perform(click());
    }

    @Step("Нажатие на кнопку +")
    public void clickingOnTheButtonToGoToTheClaimCreationScreen() {
        mainScreenElements.getCreateClaimsButton().perform(click());
    }

    @Step("Нажатие на кнопку для развертывания/свертывания новостного блока ")
    public void clickingOnTheButtonExpandTheNewsFeed() {
        mainScreenElements.getExpandNewsFeedButton().perform(click());
    }

    @Step("Нажатие на кнопку для развертывания/свертывания блока с претензиями ")
    public void expandTheClaimsFeed() {
        mainScreenElements.getExpandTheClaimsFeedButton().perform(click());
    }

    @Step("Нажатие на кнопку развертывания/свертывания описания Новости ")
    public void clickingOnTheExpandNewsDescriptionButton(int position) {
        mainScreenElements.getExpandNewsButton().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Swipe до первой видемой претензии")
    public void swipeUpBlockClaims() {
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        mainScreenElements.getTitleClaimsScreen().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        SystemClock.sleep(5000);
    }

    @Step("Проверка название в блоке all News видно")
    public void theNameAllNewsIsVisible() {
        mainScreenElements.getAllNews().check(matches(isDisplayed()));
    }

    @Step("Проверка название в блоке all News не видно")
    public void theNameAllNewsIsNotVisible() {
        mainScreenElements.getAllNews().check(matches(not(isDisplayed())));
    }

    @Step("Проверка название в блоке all Claims видно")
    public void theNameAllClaimsIsVisible() {
        mainScreenElements.getAllClaims().check(matches(isDisplayed()));
    }

    @Step("Проверка название в блоке all Claims не видно")
    public void theNameAllClaimsIsNotVisible() {
        mainScreenElements.getAllClaims().check(matches(not(isDisplayed())));
    }

    @Step("Проверка названия блока с новостями")
    public void checkTheNameOfTheNewsBlock() {
        mainScreenElements.getChapterNews().check(matches(isDisplayed()));
    }

    @Step("Проверка названия блока с претензиями")
    public void checkTheNameOfTheClaimsBlock() {
        mainScreenElements.getChapterClaims().check(matches(isDisplayed()));
    }

    @Step("Проверка нахождения пользователя на экране Main")
    public void checkNameMainScreen() {
        mainScreenElements.getChapterNews().check(matches(isDisplayed()));
        mainScreenElements.getChapterClaims().check(matches(isDisplayed()));
    }

    @Step("Проверка видемости текста описания новости ")
    public void checkingTheTextOfTheNewsDescriptionIsVisible(int position) {
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction textNews =  onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        SystemClock.sleep(3000);
        textNews.check(matches(isDisplayed()));
    }

    @Step("Рандомный переход в Creating Claims")
    public void randomTransitionToCreatingClaims() { //добавить шаги в тесты
        randomLogInToClaimsCreation();
    }

    @Step("Рандомный переход в Creating News")
    public void randomTransitionToCreatingNews() { //добавить шаги в тесты
        randomLogInToNewsCreation();
    }

    @Step("Переход в Control Panel")
    public void switchingToTheControlPanel() {
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
        creatingClaimsScreenElements.getCreatingNameScreen().check(matches(withText("Creating"))).check(matches(isDisplayed()));
        creatingClaimsScreenElements.getClaimsNameScreen().check(matches(withText("Claims"))).check(matches(isDisplayed()));
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
