package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import ru.iteco.fmhandroid.ui.screenElements.FilterNewsScreenElements;
import io.qameta.allure.kotlin.Allure;

public class FilterNewsScreenStep {

    FilterNewsScreenElements filterNewsScreenElements = new FilterNewsScreenElements();

    public void enteringSearchData(String category, String dateStartInput, String dateEndInput) {
        Allure.step("Ввод данных для поиска");
        filterNewsScreenElements.getCategoryField().perform(replaceText(category));
        SystemClock.sleep(2000);
        filterNewsScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(2000);
        filterNewsScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    public void clickingOnTheCancelSearchButton() {
        Allure.step("Нажатие на кнопку отмены поиска");
        filterNewsScreenElements.getCancelButton().perform(click());
    }

    public void clickingOnTheSearchButton() {
        Allure.step("Нажатие на кнопку поиска");
        filterNewsScreenElements.getFilterButton().perform(click());
    }

    public void enteringCategory(String category) {
        Allure.step("Ввод категории");
        filterNewsScreenElements.getCategoryField().perform(replaceText(category));
    }

    public void enteringTheStartDate(String dateStartInput) {
        Allure.step("Ввод начала даты");
        filterNewsScreenElements.getDateStartField().perform(replaceText(dateStartInput));
    }

    public void enteringTheEndOfTheDate(String dateEndInput) {
        Allure.step("Ввод конца даты");
        filterNewsScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    public void enteringDates(String dateStartInput, String dateEndInput) {
        Allure.step("Ввод дат");
        filterNewsScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        filterNewsScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    public void checkingTheScreenNameForNewsSearch() {
        Allure.step("Проверка названия экрана для поиска новостей");
        filterNewsScreenElements.getNameFilterNews().check(matches(isDisplayed()));
    }

    public void checkingIdentifyingFieldNames() {
        Allure.step("Проверка идентифицирующих названий полей");
        filterNewsScreenElements.getFieldNameCategory().check(matches(isDisplayed()));
        filterNewsScreenElements.getFieldNameStartDate().check(matches(isDisplayed()));
        filterNewsScreenElements.getFieldNameEndDate().check(matches(isDisplayed()));
    }
}
