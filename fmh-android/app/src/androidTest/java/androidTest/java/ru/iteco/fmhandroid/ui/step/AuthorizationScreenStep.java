package androidTest.java.ru.iteco.fmhandroid.ui.step;

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
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.invalidAuthInfo;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.invalidLoginPasswordAuthInfo;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.AuthorizationScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationScreenStep {

    AuthorizationScreenElements authorizationScreenElements = new AuthorizationScreenElements();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Step("Нажатие кнопки выхода из профиля")
    public void clickingTheExitProfileButton() {
        ViewInteraction user = onView((withId(R.id.authorization_image_button)));
        user.perform(click());
        ViewInteraction exitButton = onView(withText("Log out"));
        exitButton.perform(click());
    }

    @Step("Проверка названия Экрана Авторизации ")
    public void checkingTheNameOfTheAuthorizationScreen() {
        authorizationScreenElements.getAuthorization().check(matches(isDisplayed()));
    }

    @Step("Проверка идентифицирующих названий полей ")
    public void checkingIdentifyingFieldNames() {
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
    }

    @Step("Ввод валидного Login Password")
    public void validLoginPassword(Helper.AuthInfo info) {
        checkingTheNameOfTheAuthorizationScreen();
        authorizationScreenElements.getLoginField().perform(typeText(info.getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(info.getPassword())).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    @Step("Попытка авторизации при вводе невалидного login или Password")
    public void invalidAuthorization() {
        checkingTheNameOfTheAuthorizationScreen();
        authorizationScreenElements.getLoginField().perform(typeText(invalidAuthInfo().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(invalidAuthInfo().getPassword())).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    @Step("Попытка авторизации при вводе невалидного login и Password")
    public void invalidAuthorizationLoginPassword() {
        checkingTheNameOfTheAuthorizationScreen();
        authorizationScreenElements.getLoginField().perform(typeText(invalidLoginPasswordAuthInfo().getLogin()));
        authorizationScreenElements.getPasswordField().perform(typeText(invalidLoginPasswordAuthInfo().getPassword())).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    @Step("Ввод английских букв")
    public void validLanguage(String login, String password) {
        authorizationScreenElements.getLoginField().perform(typeText(login));
        authorizationScreenElements.getPasswordField().perform(typeText(password)).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    @Step("Ввод русских букв")
    public void invalidLanguage(String login, String password) {
        authorizationScreenElements.getLoginField().perform(typeText(login));
        authorizationScreenElements.getPasswordField().perform(typeText(password)).perform(closeSoftKeyboard());
        authorizationScreenElements.getButton().perform(click());
    }

    @Step("Проверка присутствия в полях введенных данных")
    public void checkingThePresenceOfTheEnteredDataInTheFields(String login, String password) {
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
        authorizationScreenElements.getLoginField().check(matches(withText(login)));
        authorizationScreenElements.getPasswordField().check(matches(withText(password)));
    }

    @Step("Проверка отсутствия в полях введенных данных")
    public void checkingTheAbsenceOfTheEnteredDataInTheFields(String invalidLoginText, String invalidPasswordText) {
        authorizationScreenElements.getLoginField().check(matches(isDisplayed()));
        authorizationScreenElements.getPasswordField().check(matches(isDisplayed()));
        authorizationScreenElements.getLoginField().check(matches(not(withText(invalidLoginText))));
        authorizationScreenElements.getPasswordField().check(matches(not(withText(invalidPasswordText))));
    }

    @Step("Проверка появления предупреждающего сообщения Login and password cannot be empty")
    public void checkingTheLoginAndPasswordCannotBeEmpty(@NonNull AppActivity activity, int text) {
        onView(withId(text)).inRoot(withDecorView(Matchers.not(is(activity.getWindow().getDecorView()))));
    }

    @Step("Проверка появления предупреждающего сообщения Wrong login or password")
    public void checkingTheWrongLoginOrPassword(@NonNull AppActivity activity, int text) {
        onView(withText(text)).inRoot(withDecorView(Matchers.not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}