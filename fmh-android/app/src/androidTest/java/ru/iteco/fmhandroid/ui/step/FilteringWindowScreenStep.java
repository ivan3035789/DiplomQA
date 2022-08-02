package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCheckBox;
import static ru.iteco.fmhandroid.ui.data.Helper.checkStatus;

import android.os.SystemClock;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import io.qameta.allure.kotlin.Allure;

public class FilteringWindowScreenStep {

    MainScreenStep mainScreenStep = new MainScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
    FilteringWindowScreenElements filteringWindowScreenElements = new FilteringWindowScreenElements();
    
    public void switchingToFilteringWindow() {
        Allure.step("Переход к экрану Filtering");
        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheClaimsName();
        SystemClock.sleep(3000);
        claimsScreenStep.pressingOnTheButtonToGoToTheFilteringScreen();
        SystemClock.sleep(3000);
    }
    
    public void clickingOnTheOkButton() {
        Allure.step("Нажатие на кнопку ok");
        filteringWindowScreenElements.getOkButton().perform(click());
    }

    public void clickingOnTheExitFilteringButton() {
        Allure.step("Нажатие на кнопку выхода из Filtering");
        filteringWindowScreenElements.getCancelButton().perform(click());
    }
    
    public void clickingOnTheCheckBoxInProgress() {
        Allure.step("Нажатие на чек бокс In progress");
        filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
    }
    
    public void clickingOnTheCheckBoxOpen() {
        Allure.step("Нажатие на чек бокс Open");
        filteringWindowScreenElements.getCheckBoxOpen().perform(click());
    }
    
    public void clickingOnTheCheckBoxExecuted() {
        Allure.step("Нажатие на чек бокс Executed");
        filteringWindowScreenElements.getCheckBoxExecuted().perform(click());
    }
    
    public void clickingOnTheCheckBoxCancelled() {
        Allure.step("Нажатие на чек бокс Cancelled");
        filteringWindowScreenElements.getCheckBoxCancelled().perform(click());
    }
    
    public void clickingOnRandomlySelectedCheckBox() {
        Allure.step("Нажатие на выбранный случайным образом чек бокс");
        randomCheckBox();
    }
    
    public void checkingTheStatus() {
        Allure.step("Проверка статуса");
        checkStatus(Helper.Text.getText(claimsScreenElements.getStatus()));
    }
    
    public void checkingTheMessageForUndiscoveredClaims() {
        Allure.step("Проверка сообщения при ненайденных претензиях");
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
    }
    
    public void checkingTheScreenNameFiltering() {
        Allure.step("Проверка названия экрана filtering");
        filteringWindowScreenElements.getFilteringNameScreen().check(matches(isDisplayed()));
    }
    
    public void checkingForMissingScreenNameFiltering() {
        Allure.step("Проверка отсутствия название экрана Filtering");
        claimsScreenElements.getScreenNameClaims().check(matches(not(filteringWindowScreenElements.getFilteringNameScreen())));
    }
}
