package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ApplicationLoadingScreenElements;
import io.qameta.allure.kotlin.Step;

public class ApplicationLoadingScreenStep {

    ApplicationLoadingScreenElements applicationLoadingScreenElements = new ApplicationLoadingScreenElements();

    @Step("Проверка просмотр изображения на заставке")
    public void checkSplashscreenImageView() {
        applicationLoadingScreenElements.getSplashscreenImageView().check(matches(isDisplayed()));
    }

    @Step("Проверка текстовое представление заставки")
    public void checkSplashscreenTextView() {
        applicationLoadingScreenElements.getSplashscreenTextView().check(matches(isDisplayed()));
    }

    @Step("Проверка индикатора выполнения ")
    public void checkProgressIndicator() {
        applicationLoadingScreenElements.getProgressIndicator().check(matches(isDisplayed()));
    }
}
