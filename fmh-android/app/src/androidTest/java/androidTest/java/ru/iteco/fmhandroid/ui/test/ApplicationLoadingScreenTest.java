package androidTest.java.ru.iteco.fmhandroid.ui.test;

import android.os.SystemClock;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidTest.java.ru.iteco.fmhandroid.ui.step.ApplicationLoadingScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ApplicationLoadingScreenTest {

    ApplicationLoadingScreenStep applicationLoadingScreenStep = new ApplicationLoadingScreenStep();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(1000);
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("the picture should be displayed")
    @Description("В этом тест кейсе мы проверяем, что  во время загрузки приложения появляется картинка (картинки каждый раз при загрузке меняются)")
    public void thePictureShouldBeDisplayed() {
        applicationLoadingScreenStep.checkSplashscreenImageView();
    }

    @Test
    @DisplayName("the text should be displayed")
    @Description("В этом тест кейсе мы проверяем, что  во время загрузки приложения появляется  цитата (цитаты при входе каждый раз меняются)")
    public void theTextShouldBeDisplayed() {
        applicationLoadingScreenStep.checkSplashscreenTextView();
    }

    @Test
    @DisplayName("the download icon should be displayed")
    @Description("В этом тест кейсе мы проверяем, что  во время загрузки приложения появляется индекатор загрузки)")
    public void theDownloadIconShouldBeDisplayed() {
        applicationLoadingScreenStep.checkProgressIndicator();
    }
}
