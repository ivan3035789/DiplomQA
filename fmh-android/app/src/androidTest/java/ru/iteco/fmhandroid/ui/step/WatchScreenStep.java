package ru.iteco.fmhandroid.ui.step;

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
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomHour23;
import static ru.iteco.fmhandroid.ui.data.Helper.DateTime.randomMinute59;

import androidx.annotation.NonNull;

import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class WatchScreenStep {
    WatchScreenElements watchScreenElements = new WatchScreenElements();

    public void pressingTheButtonToChangeTheWatchType() {
        Allure.step("Нажатие на кнопку смены вида часов");
        watchScreenElements.getButtonToChangeTheTypeOfClock().perform(click());
    }

    public void settingTheHourSelectedRandomly(String hour) {
        Allure.step("Установка часа выбранного случайным образом время валидное");
        watchScreenElements.getInputHour().perform(replaceText(hour), closeSoftKeyboard());
    }

    public void settingTheMinutesSelectedRandomly(String minute) {
        Allure.step("Установка минут выбранных случайным образом время валидное");
        watchScreenElements.getInputMinute().perform(replaceText(minute), closeSoftKeyboard());
    }

    public void settingTheHourToAnInvalidValue(String invalidHour) {
        Allure.step("Установка часа невалидного значения");
        watchScreenElements.getInputHour().perform(replaceText(invalidHour), closeSoftKeyboard());
    }

    public void settingTheMinutesToAnInvalidValue(String invalidMinute) {
        Allure.step("Установка минут невалидного значения");
        watchScreenElements.getInputMinute().perform(replaceText(invalidMinute), closeSoftKeyboard());
    }

    public void clickingOnTheConfirmationButton() {
        Allure.step("Нажатие на кнопку подтверждения");
        watchScreenElements.getOkButton().perform(scrollTo(), click());
    }

    public void pressingTheCancelTimeSettingButton() {
        Allure.step("Нажатие на кнопку отмены установки времени");
        watchScreenElements.getCancelButton().perform(scrollTo(), click());
    }

    public void settingRandomlySelectedHour() {
        Allure.step("Установка случайно выбранного часа");
        watchScreenElements.getInputHour().perform(replaceText(randomHour23()), closeSoftKeyboard());
    }

    public void settingRandomlySelectedMinute() {
        Allure.step("Установка случайно выбранной минуты");
        watchScreenElements.getInputMinute().perform(replaceText(randomMinute59()), closeSoftKeyboard());
    }

    public void checkingTheSetTime(String hour, String minute, String timeAfter, String timeBefore) {
        Allure.step("Проверка выставленного времени");
        assertEquals(hour + ":" + minute, timeAfter);
        assertNotEquals(timeBefore, timeAfter);
    }

    public void checkingTheTypeOfDigitalClock() {
        Allure.step("Проверка вида цифровых часов");
        watchScreenElements.getTextTime().check(matches(withText("Set time"))).check(matches(isDisplayed()));
    }

    public void checkingTheClockReadingsBeforeInstallationAndAfterCancelingTheInstallation(String timeBefore, String timeAfter) {
        Allure.step("Проверка показаний часов до  установки и после отмены установки");
        assertEquals(timeBefore, timeAfter);
    }

    public void checkingEnterValidTime(@NonNull AppActivity activity, String text) {
        Allure.step("Проверка появления предупреждающего сообщения Enter a valid time");
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
