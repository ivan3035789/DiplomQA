package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.authInfo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationScreenTest {

    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    MainScreenStep mainScreenStep = new MainScreenStep();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            authorizationScreenStep.checkingTheNameOfTheAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.clickingTheExitProfileButton();
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("there should be a name authorization")
    @Description("В этом тест кейсе мы проверяем название страницы для авторизации пользователя (страница \"Authorization\")")
    public void thereShouldBeNameAuthorization() {
        authorizationScreenStep.checkingTheNameOfTheAuthorizationScreen();
    }

    @Test
    @DisplayName("login And Password Fields Should Be Displayed")
    @Description("В этом тест кейсе мы проверяем, что в полях для ввода, присудствуют идентифицирующие названия полей  (login,password), соответствующие вводимым данным")
    public void loginAndPasswordFieldsShouldBeDisplayed() {
        authorizationScreenStep.checkingIdentifyingFieldNames();
    }

    @Test
    @DisplayName("The fields must be filled in with English letters")
    @Description("В этом тест кейсе мы проверяем, что поля заполняются латинскими буквами")
    public void theFieldsMustBeFilledInWithEnglishLetters() {
        String login = "loginLogin";
        String password = "passwordPassword";

        authorizationScreenStep.validLanguage(login, password);
        authorizationScreenStep.checkingThePresenceOfTheEnteredDataInTheFields(login, password);
    }

    @Test
    @DisplayName("Fields should not be filled in with Russian letters")
    @Description("В этом тест кейсе мы проверяем, что поля незаполняются нелатинскими буквами")
    public void fieldsShouldNotBeFilledInWithRussianLetters() {
        String invalidLoginText = "привет мир";
        String invalidPasswordText = "привет мир";

        try {
            authorizationScreenStep.invalidLanguage(invalidLoginText, invalidPasswordText);
        } catch (RuntimeException expected) {

        } finally {
            authorizationScreenStep.checkingTheAbsenceOfTheEnteredDataInTheFields(invalidLoginText, invalidPasswordText);
        }
    }

    @Test
    @DisplayName("must Log In")
    @Description("В этом тест кейсе мы проверяем, что при вводе правильного логина и пароля пользователь входит в систему")
    public void mustLogIn() {
        authorizationScreenStep.validLoginPassword(authInfo());
        SystemClock.sleep(5000);
        mainScreenStep.checkNameMainScreen();
    }

    @Test
    @DisplayName("a Warning Message Should Appear If The Fields Are Blank")
    @Description("В этом тест кейсе мы проверяем, что при невводе логина или пароля появляется предупреждающая надпись")
    public void aWarningMessageShouldAppearIfTheFieldsAreBlank() {
        authorizationScreenStep.invalidAuthorization();
        SystemClock.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty"))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("warning Messages Should Appear When Entering An Incorrect Password")
    @Description("В этом тест кейсе мы проверяем, что при вводе неправильного логина или пароля пользователь не входит в систему, появляется надпись Wrong login or password")
    public void warningMessagesShouldAppearWhenEnteringAnIncorrectPassword() {
        authorizationScreenStep.invalidAuthorizationLoginPassword();
        SystemClock.sleep(1000);
        onView(withText(R.string.wrong_login_or_password))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Wrong login or password"))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("warning Messages Should Appear When You Enter Space")
    @Description("В этом тест кейсе мы проверяем, что при невведенном логине и пароле появлется предупреждающая надпись")
    public void warningMessagesShouldAppearWhenYouEnterSpace() {
        authorizationScreenStep.invalidAuthorization();
        SystemClock.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty"))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("warning Messages Should Appear When The Password Field Is Blank")
    @Description("В этом тест кейсе мы проверяем, что при невведенном пароле появлется предупреждающая надпись")
    public void warningMessagesShouldAppearWhenThePasswordFieldIsBlank() {
        authorizationScreenStep.invalidAuthorization();
        SystemClock.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty"))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("warning Messages Should Appear When The Login Field Is Empty")
    @Description("В этом тест кейсе мы проверяем, что при невведенном логине появлется предупреждающая надпись")
    public void warningMessagesShouldAppearWhenTheLoginFieldIsEmpty() {
        authorizationScreenStep.invalidAuthorization();
        SystemClock.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(ActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText("Login and password cannot be empty"))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("must Log Out Of Profile")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку выхода и нажатии log out, пользователь выходит из профиля")
    public void mustLogOutOfProfile() {
        authorizationScreenStep.validLoginPassword(authInfo());
        SystemClock.sleep(5000);
        mainScreenStep.checkNameMainScreen();
        authorizationScreenStep.clickingTheExitProfileButton();
        authorizationScreenStep.checkingTheNameOfTheAuthorizationScreen();
    }
}
