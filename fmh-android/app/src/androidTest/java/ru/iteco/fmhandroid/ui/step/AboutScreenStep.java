package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.screenElements.AboutScreenElements;
import io.qameta.allure.kotlin.Allure;

public class AboutScreenStep {
    AboutScreenElements aboutScreenElements = new AboutScreenElements();

    public void checkScreenNameAbout() {
        Allure.step("Проверка экрана About {version} и {namVersion}");
        aboutScreenElements.getVersion().check(matches(isDisplayed()));
        aboutScreenElements.getNumVersion().check(matches(isDisplayed()));
    }

    public void checkNamePrivacyPolicy() {
        Allure.step("Проверка названия {privacyPolicy}");
        aboutScreenElements.getPrivacyPolicy().check(matches(isDisplayed()));
    }

    public void checkNameLinkPrivacyPolicy() {
        Allure.step("Проверка названия ссылки {linkPrivacyPolicy}");
        aboutScreenElements.getLinkPrivacyPolicy().check(matches(isDisplayed()));
    }

    public void checkNameTermsOfUse() {
        Allure.step("Проверка названия {termsOfUse}");
        aboutScreenElements.getTermsOfUse().check(matches(isDisplayed()));
    }

    public void checkNameLinkTermsOfUse() {
        Allure.step("Проверка названия ссылки {linkTermsOfUse}");
        aboutScreenElements.getLinkTermsOfUse().check(matches(isDisplayed()));
    }

    public void checkingTheLinksClickabilityLinkTermsOfUse() {
        Allure.step("Проверка кликабельности ссылки");
        aboutScreenElements.getLinkTermsOfUse().check(matches(isClickable()));
    }

    public void checkingTheLinksClickabilityLinkPrivacyPolicy() {
        Allure.step("Проверка кликабельности ссылки {linkPrivacyPolicy}");
        aboutScreenElements.getLinkPrivacyPolicy().check(matches(isDisplayed()));
    }

    public void clickAboutExitButton() {
        Allure.step("Нажатие на кнопку ввиде галочки для выхода из экрана About");
        aboutScreenElements.getAboutExitButton().perform(click());
    }

    public void checkingTheManufacturersName() {
        Allure.step("Проверка названия производителя");
        aboutScreenElements.getTeco().check(matches(isDisplayed()));
    }
}
