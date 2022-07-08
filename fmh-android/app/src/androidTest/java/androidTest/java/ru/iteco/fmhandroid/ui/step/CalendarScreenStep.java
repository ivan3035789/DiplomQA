package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.headerCalendarDate;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.headerCalendarYear;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.DateTime.settingTheDate;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.firstUpperCase;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.clickingNextMonth;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.clickingPreviousMonth;

import androidx.test.espresso.ViewInteraction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import io.qameta.allure.kotlin.Step;

public class CalendarScreenStep {

    CalendarScreenElements calendarScreenElements = new CalendarScreenElements();

    @Step("Нажатие на кнопку подтвердить")
    public void clickingOnTheConfirmButton() {
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
    }

    @Step("Нажатие на кнопку для выбора года")
    public void pressingTheButtonToSelectTheYear() {
        calendarScreenElements.getButtonOfTheYear().perform(click());
    }

    @Step("Нажатие на кнопку перехода к следующему месяцу {randomMonth} раз")
    public void pressingTheButtonToGoToTheNextMonthTwelveTimes(int randomMonth) {
        clickingNextMonth(randomMonth);
    }

    @Step("Нажатие на кнопку перехода к предыдущему месяцу {randomMonth} раз")
    public void clickingOnTheButtonToGoToThePreviousMonthTwelveTimes(int randomMonth) {
        clickingPreviousMonth(randomMonth);
    }

    @Step("Нажатие на кнопку отмены установки года")
    public void clickingOnTheCancelYearSettingButton() {
        calendarScreenElements.getButtonToCancelTheYearSetting().perform(scrollTo(), click());
    }

    @Step("Установка даты")
    public void settingDate(int yearInt, int monthInt, int dayInt) {
        settingTheDate(yearInt, monthInt, dayInt);
    }

    @Step("Установка года")
    public void settingTheYear(int randomYear) {
        calendarScreenElements.getYear().atPosition(randomYear).perform(scrollTo(), click());
    }

    @Step("Проверка отображения в Header календаря изменения месяца, числа")
    public void checkingTheDisplayInTheCalendarHeaderOfTheMonthNameChange(ViewInteraction dateFromTheCalendarHeader, String dayOfWeek, String month, String day, String dateFromTheCalendar, String dayOfWeekPlusMonth, String monthPlusTwoMonth, String dayPlusYearPlusMonth) {
        dateFromTheCalendarHeader.check(matches(isDisplayed())).check(matches(withText(firstUpperCase(dayOfWeekPlusMonth) + ", " + firstUpperCase(monthPlusTwoMonth) + " " + dayPlusYearPlusMonth)));
        assertEquals(firstUpperCase(dayOfWeek) + ", " + firstUpperCase(month) + " " + day, dateFromTheCalendar);
    }

    @Step("Проверка отображения в Header календаря изменения года")
    public void checkingTheYearChangeCalendarDisplayInTheHeader(ViewInteraction yearFromTheCalendarHeader, String yearNumberOfMonths, String year) {
        yearFromTheCalendarHeader.check(matches(withText(year))).check(matches(isDisplayed()));
        assertEquals(yearNumberOfMonths, year);
    }

    public String dateFormatting(int yearPlusYears, int monthNow, int dayOfMonthNow) {
        return LocalDate.of(yearPlusYears, monthNow, dayOfMonthNow).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public ViewInteraction dateFromTheCalendarHeaderBeforeReturnViewInteraction(String dayOfWeek, String month, String day) {
        return headerCalendarDate(firstUpperCase(dayOfWeek), firstUpperCase(month), day);
    }

    public String dateFromTheCalendarHeaderReturnString(String dayOfWeek, String month, String day) {
        return Helper.Text.getText(headerCalendarDate(firstUpperCase(dayOfWeek), firstUpperCase(month), day));
    }

    public String yearFromTheCalendarHeaderReturnString(String year) {
        return Helper.Text.getText(headerCalendarYear(year));
    }

    public ViewInteraction yearFromTheCalendarHeaderReturnViewInteraction(String year) {
        return headerCalendarYear(year);
    }
}
