package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ApplicationLoadingScreenElements {
    private final ViewInteraction splashscreenImageView = onView(withId(R.id.splashscreen_image_view));

    private final ViewInteraction splashscreenTextView = onView(withId(R.id.splashscreen_text_view));

    private final ViewInteraction progressIndicator = onView(withId(R.id.splash_screen_circular_progress_indicator));

    public ViewInteraction getSplashscreenImageView() {
        return splashscreenImageView;
    }

    public ViewInteraction getSplashscreenTextView() {
        return splashscreenTextView;
    }

    public ViewInteraction getProgressIndicator() {
        return progressIndicator;
    }
}
