package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.screenElements.ApplicationLoadingScreenElements;
import io.qameta.allure.kotlin.Allure;

public class ApplicationLoadingScreenStep {

    ApplicationLoadingScreenElements applicationLoadingScreenElements = new ApplicationLoadingScreenElements();

    public void checkSplashscreenImageView() {
        Allure.step("Проверка просмотр изображения на заставке");
        applicationLoadingScreenElements.getSplashscreenImageView().check(matches(isDisplayed()));
    }

    public void checkSplashscreenTextView() {
        Allure.step("Проверка текстовое представление заставки");
        applicationLoadingScreenElements.getSplashscreenTextView().check(matches(isDisplayed()));
    }

    public void checkProgressIndicator() {
        Allure.step("Проверка индикатора выполнения");
        applicationLoadingScreenElements.getProgressIndicator().check(matches(isDisplayed()));
    }
}
