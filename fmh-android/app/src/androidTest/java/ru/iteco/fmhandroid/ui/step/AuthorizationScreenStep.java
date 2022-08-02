package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidAuthInfo;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidLoginPasswordAuthInfo;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.AuthorizationScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationScreenStep {

    AuthorizationScreenElements authorizationScreenElements = new AuthorizationScreenElements();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    public void clickingTheExitProfileButton() {
        Allure.step("Нажатие кнопки выхода из профиля");
        ViewInteraction user = onView((withId(R.id.authorization_image_button)));
        user.perform(click());
        ViewInteraction exitButton = onView(withText("Log out"));
        exitButton.perform(click());
    }

    public void checkingTheNameOfTheAuthorizationScreen() {
        Allure.step("Проверка названия Экрана Авторизации");
        authorizationScreenElements.getAuthorization().check(matches(isDisplayed()));
    }

    public void checkingIdentifyingFieldNames() {
        Allure.step("Проверка идентифицирующих названий полей");
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
    }

    public void validLoginPassword(Helper.AuthInfo info) {
        Allure.step("Ввод валидного Login Password");
        checkingTheNameOfTheAuthorizationScreen();
        authorizationScreenElements.getLoginField().perform(typeText(info.getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    public void invalidAuthorization() {
        Allure.step("Попытка авторизации при вводе невалидного login или Password");
        checkingTheNameOfTheAuthorizationScreen();
        authorizationScreenElements.getLoginField().perform(typeText(invalidAuthInfo().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(invalidAuthInfo().getPassword())).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    public void invalidAuthorizationLoginPassword() {
        Allure.step("Попытка авторизации при вводе невалидного login и Password");
        checkingTheNameOfTheAuthorizationScreen();
        authorizationScreenElements.getLoginField().perform(typeText(invalidLoginPasswordAuthInfo().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(invalidLoginPasswordAuthInfo().getPassword())).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    public void validLanguage(String login, String password) {
        Allure.step("Ввод английских букв");
        authorizationScreenElements.getLoginField().perform(typeText(login));
        authorizationScreenElements.getPasswordField().perform(typeText(password)).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    public void invalidLanguage(String login, String password) {
        Allure.step("Ввод русских букв");
        authorizationScreenElements.getLoginField().perform(typeText(login));
        authorizationScreenElements.getPasswordField().perform(typeText(password)).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    public void checkingThePresenceOfTheEnteredDataInTheFields(String login, String password) {
        Allure.step("Проверка присутствия в полях введенных данных");
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
        authorizationScreenElements.getLoginField().check(matches(withText(login)));
        authorizationScreenElements.getPasswordField().check(matches(withText(password)));
    }

    public void checkingTheAbsenceOfTheEnteredDataInTheFields(String invalidLoginText, String invalidPasswordText) {
        Allure.step("Проверка отсутствия в полях введенных данных");
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
        authorizationScreenElements.getLoginField().check(matches(not(withText(invalidLoginText))));
        authorizationScreenElements.getPasswordField().check(matches(not(withText(invalidPasswordText))));
    }

    public void checkingTheLoginAndPasswordCannotBeEmpty(@NonNull AppActivity activity, int text) {
        Allure.step("Проверка появления предупреждающего сообщения Login and password cannot be empty");
        onView(withId(text)).inRoot(withDecorView(Matchers.not(is(activity.getWindow().getDecorView()))));
    }

    public void checkingTheWrongLoginOrPassword(@NonNull AppActivity activity, int text) {
        Allure.step("Проверка появления предупреждающего сообщения Wrong login or password");
        onView(withText(text)).inRoot(withDecorView(Matchers.not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}