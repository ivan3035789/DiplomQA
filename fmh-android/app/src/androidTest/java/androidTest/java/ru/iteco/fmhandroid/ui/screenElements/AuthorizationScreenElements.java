package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationScreenElements {
    private final ViewInteraction authorization = onView(
            allOf(withText("Authorization"),
                    withParent(withParent(withId(R.id.nav_host_fragment)))));

    private final ViewInteraction loginField = onView(
            allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));

    private final ViewInteraction passwordField = onView(
            allOf(withHint("Password"),
                    withParent(withParent(withId(R.id.password_text_input_layout)))));

    private final ViewInteraction button = onView(withText("SIGN IN"));

    public ViewInteraction getAuthorization() {
        return authorization;
    }

    public ViewInteraction getLoginField() {
        return loginField;
    }

    public ViewInteraction getPasswordField() {
        return passwordField;
    }

    public ViewInteraction getButton() {
        return button;
    }
}