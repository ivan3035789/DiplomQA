package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomHour23;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomMinute59;

import androidx.annotation.NonNull;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class WatchScreenStep {
    WatchScreenElements watchScreenElements = new WatchScreenElements();

    @Step("Нажатие на кнопку смены вида часов")
    public void pressingTheButtonToChangeTheWatchType() {
        watchScreenElements.getButtonToChangeTheTypeOfClock().perform(click());
    }

    @Step("Установка часа выбранного случайным образом время валидное")
    public void settingTheHourSelectedRandomly(String hour) {
        watchScreenElements.getInputHour().perform(replaceText(hour), closeSoftKeyboard());
    }

    @Step("Установка минут выбранных случайным образом время валидное")
    public void settingTheMinutesSelectedRandomly(String minute) {
        watchScreenElements.getInputMinute().perform(replaceText(minute), closeSoftKeyboard());
    }

    @Step("Установка часа невалидного значения")
    public void settingTheHourToAnInvalidValue(String invalidHour) {
        watchScreenElements.getInputHour().perform(replaceText(invalidHour), closeSoftKeyboard());
    }

    @Step("Установка минут невалидного значения")
    public void settingTheMinutesToAnInvalidValue(String invalidMinute) {
        watchScreenElements.getInputMinute().perform(replaceText(invalidMinute), closeSoftKeyboard());
    }

    @Step("Нажатие на кнопку подтверждения")
    public void clickingOnTheConfirmationButton() {
        watchScreenElements.getOkButton().perform(scrollTo(), click());
    }

    @Step("Нажатие на кнопку отмены установки времени")
    public void pressingTheCancelTimeSettingButton() {
        watchScreenElements.getCancelButton().perform(scrollTo(), click());
    }

    @Step("Установка случайно выбранного часа")
    public void settingRandomlySelectedHour() {
        watchScreenElements.getInputHour().perform(replaceText(randomHour23()), closeSoftKeyboard());
    }

    @Step("Установка случайно выбранной минуты")
    public void settingRandomlySelectedMinute() {
        watchScreenElements.getInputMinute().perform(replaceText(randomMinute59()), closeSoftKeyboard());
    }

    @Step("Проверка выставленного времени")
    public void checkingTheSetTime(String hour, String minute, String timeAfter, String timeBefore) {
        assertEquals(hour + ":" + minute, timeAfter);
        assertNotEquals(timeBefore, timeAfter);
    }

    @Step("Проверка вида цифровых часов ")
    public void checkingTheTypeOfDigitalClock() {
        watchScreenElements.getTextTime().check(matches(withText("Set time"))).check(matches(isDisplayed()));
    }

    @Step("Проверка показаний часов до  установки и после отмены установки")
    public void checkingTheClockReadingsBeforeInstallationAndAfterCancelingTheInstallation(String timeBefore, String timeAfter) {
        assertEquals(timeBefore, timeAfter);
    }

    @Step("Проверка появления предупреждающего сообщения Enter a valid time")
    public void checkingEnterValidTime(@NonNull AppActivity activity, String text) {
        onView(withText(text))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(withText("Enter a valid time"))).check(matches(isDisplayed()));
    }

    public String timeBefore() {
        return Helper.Text.getText(onView(withId(R.id.time_in_plan_text_input_edit_text)));
    }

    public String timeAfter() {
        return Helper.Text.getText(onView(withId(R.id.time_in_plan_text_input_edit_text)));
    }
}
