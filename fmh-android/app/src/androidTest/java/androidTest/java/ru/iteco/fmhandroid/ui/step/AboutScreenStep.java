package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.AboutScreenElements;
import io.qameta.allure.kotlin.Step;

public class AboutScreenStep {
    AboutScreenElements aboutScreenElements = new AboutScreenElements();

    @Step("Проверка экрана About {version} и {namVersion}")
    public void checkScreenNameAbout() {
        aboutScreenElements.getVersion().check(matches(isDisplayed()));
        aboutScreenElements.getNumVersion().check(matches(isDisplayed()));
    }

    @Step("Проверка названия {privacyPolicy}")
    public void checkNamePrivacyPolicy() {
        aboutScreenElements.getPrivacyPolicy().check(matches(isDisplayed()));
    }

    @Step("Проверка названия ссылки {linkPrivacyPolicy}")
    public void checkNameLinkPrivacyPolicy() {
        aboutScreenElements.getLinkPrivacyPolicy().check(matches(isDisplayed()));
    }

    @Step("Проверка названия {termsOfUse}")
    public void checkNameTermsOfUse() {
        aboutScreenElements.getTermsOfUse().check(matches(isDisplayed()));
    }

    @Step("Проверка названия ссылки {linkTermsOfUse}")
    public void checkNameLinkTermsOfUse() {
        aboutScreenElements.getLinkTermsOfUse().check(matches(isDisplayed()));
    }

    @Step("Проверка кликабельности ссылки")
    public void checkingTheLinksClickabilityLinkTermsOfUse() {
        aboutScreenElements.getLinkTermsOfUse().check(matches(isClickable()));
    }

    @Step("Проверка кликабельности ссылки {linkPrivacyPolicy}")
    public void checkingTheLinksClickabilityLinkPrivacyPolicy() {
        aboutScreenElements.getLinkPrivacyPolicy().check(matches(isDisplayed()));
    }

    @Step("Нажатие на кнопку ввиде галочки для выхода из экрана About")
    public void clickAboutExitButton() {
        aboutScreenElements.getAboutExitButton().perform(click());
    }

    @Step("Проверка названия производителя")
    public void checkingTheManufacturersName() {
        aboutScreenElements.getTeco().check(matches(isDisplayed()));
    }
}
