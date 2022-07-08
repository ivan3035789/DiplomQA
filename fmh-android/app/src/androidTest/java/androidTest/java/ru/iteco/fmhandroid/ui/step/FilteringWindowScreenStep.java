package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCheckBox;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.checkStatus;

import android.os.SystemClock;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import io.qameta.allure.kotlin.Step;

public class FilteringWindowScreenStep {

    MainScreenStep mainScreenStep = new MainScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
    FilteringWindowScreenElements filteringWindowScreenElements = new FilteringWindowScreenElements();

    @Step("Переход к экрану Filtering")
    public void switchingToFilteringWindow() {
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        SystemClock.sleep(3000);
        claimsScreenStep.pressingOnTheButtonToGoToTheFilteringScreen();
        SystemClock.sleep(3000);
    }

    @Step("Нажатие на кнопку ok")
    public void clickingOnTheOkButton() {
        filteringWindowScreenElements.getOkButton().perform(click());
    }

    @Step("Нажатие на кнопку выхода из Filtering")
    public void clickingOnTheExitFilteringButton() {
        filteringWindowScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на чек бокс In progress")
    public void clickingOnTheCheckBoxInProgress() {
        filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
    }

    @Step("Нажатие на чек бокс Open")
    public void clickingOnTheCheckBoxOpen() {
        filteringWindowScreenElements.getCheckBoxOpen().perform(click());
    }

    @Step("Нажатие на чек бокс Executed")
    public void clickingOnTheCheckBoxExecuted() {
        filteringWindowScreenElements.getCheckBoxExecuted().perform(click());
    }

    @Step("Нажатие на чек бокс Cancelled")
    public void clickingOnTheCheckBoxCancelled() {
        filteringWindowScreenElements.getCheckBoxCancelled().perform(click());
    }

    @Step("Нажатие на выбранный случайным образом чек бокс")
    public void clickingOnRandomlySelectedCheckBox() {
        randomCheckBox();
    }

    @Step("Проверка статуса")
    public void checkingTheStatus() {
        checkStatus(Helper.Text.getText(claimsScreenElements.getStatus()));
    }

    @Step("Проверка сообщения при ненайденных претензиях")
    public void checkingTheMessageForUndiscoveredClaims() {
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
    }

    @Step("Проверка названия экрана filtering")
    public void checkingTheScreenNameFiltering() {
        filteringWindowScreenElements.getFilteringNameScreen().check(matches(isDisplayed()));
    }


    @Step("Проверка отсутствия название экрана Filtering")
    public void checkingForMissingScreenNameFiltering() {
        claimsScreenElements.getScreenNameClaims().check(matches(not(filteringWindowScreenElements.getFilteringNameScreen())));
    }
}
