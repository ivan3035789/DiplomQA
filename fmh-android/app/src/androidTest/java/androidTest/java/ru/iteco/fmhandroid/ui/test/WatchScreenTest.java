package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidHour;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.invalidMinute;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomHour23;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomMinute59;
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
import androidTest.java.ru.iteco.fmhandroid.ui.step.CreatingClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.WatchScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class WatchScreenTest {

    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    MainScreenStep mainScreenStep = new MainScreenStep();
    WatchScreenStep watchScreenStep = new WatchScreenStep();
    CreatingClaimsScreenStep creatingClaimsScreenStep = new CreatingClaimsScreenStep();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenStep.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.validLoginPassword(authInfo());
            SystemClock.sleep(5000);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(5000);
    }

    @Test
    @DisplayName("The type of watch should change")
    @Description("В этом тест кейсе мы проверяем возможность выбора типа часов, при нажатии на кнопку с иконкой \"клавиатура\" должен поменяться вид часов        (раздел \"Creating News\" или \"Creating Claims\" или \"Еditing News\" ) (вид часов цифровой с полями для ввода, стрелочный)")
    public void theTypeOfWatchShouldChange() {
        mainScreenStep.randomTransitionToCreatingClaims();
        SystemClock.sleep(3000);
        creatingClaimsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();
    }

    @Test
    @DisplayName("Canceling the time selection and setting")
    @Description("В этом тест кейсе мы проверяем, что при нажатии на кнопку \"Cancel\", происходит отмена установки выбранного пользоваителем времени, часы закрываются  ")
    public void cancelingTheTimeSelectionAndSetting() {
        mainScreenStep.randomTransitionToCreatingClaims();
        SystemClock.sleep(3000);
        String timeBefore = watchScreenStep.timeBefore();
        SystemClock.sleep(3000);
        creatingClaimsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();

        watchScreenStep.settingRandomlySelectedHour();
        SystemClock.sleep(3000);
        watchScreenStep.settingRandomlySelectedMinute();

        SystemClock.sleep(3000);
        watchScreenStep.pressingTheCancelTimeSettingButton();
        String timeAfter = watchScreenStep.timeAfter();
        watchScreenStep.checkingTheClockReadingsBeforeInstallationAndAfterCancelingTheInstallation(timeBefore, timeAfter);
    }

    @Test
    @DisplayName("The time must be set when entering correct data in the clock with input fields")
    @Description("В этом тест кейсе мы проверяем, что при вводе в поля корректного времени и после нажатия на кнопку \"ок\" в поле \"Time\" появляется введенное пользователем время")
    public void theTimeMustBeSetWhenEnteringCorrectDataInTheClockWithInputFields() {
        String hour = randomHour23();
        String minute = randomMinute59();

        mainScreenStep.randomTransitionToCreatingClaims();
        SystemClock.sleep(3000);
        String timeBefore = watchScreenStep.timeBefore();
        SystemClock.sleep(3000);
        creatingClaimsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();

        watchScreenStep.settingTheHourSelectedRandomly(hour);
        SystemClock.sleep(3000);
        watchScreenStep.settingTheMinutesSelectedRandomly(minute);

        SystemClock.sleep(3000);
        watchScreenStep.clickingOnTheConfirmationButton();
        String timeAfter = watchScreenStep.timeAfter();
        watchScreenStep.checkingTheSetTime(hour, minute, timeAfter, timeBefore);
    }

    @Test
    @DisplayName("A warning message should appear when trying to set an incorrect time in the clock with input fields")
    @Description("В этом тест кейсе мы проверяем, что при вводе в поля некорректного времени и после нажатия на кнопку \"ок\" пользователь остается в окне часов, время не устанавливается, появляется предупреждающее сообщение \"Enter a valid time\"")
    public void aWarningMessageShouldAppearWhenTryingToSetAnIncorrectTimeInTheClockWithInputFields() {
        mainScreenStep.randomTransitionToCreatingClaims();
        String invalidHour = invalidHour();
        String invalidMinute = invalidMinute();

        SystemClock.sleep(3000);
        creatingClaimsScreenStep.clickingOnTheTimeField();
        watchScreenStep.pressingTheButtonToChangeTheWatchType();
        watchScreenStep.checkingTheTypeOfDigitalClock();

        watchScreenStep.settingTheHourToAnInvalidValue(invalidHour);
        SystemClock.sleep(3000);
        watchScreenStep.settingTheMinutesToAnInvalidValue(invalidMinute);

        SystemClock.sleep(3000);
        watchScreenStep.clickingOnTheConfirmationButton();
        watchScreenStep.checkingEnterValidTime(ActivityTestRule.getActivity(), "Enter a valid time");
    }
}
