package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.FilterNewsScreenElements;
import io.qameta.allure.kotlin.Step;

public class FilterNewsScreenStep {

    FilterNewsScreenElements filterNewsScreenElements = new FilterNewsScreenElements();

    @Step("Ввод данных для поиска")
    public void enteringSearchData(String category, String dateStartInput, String dateEndInput) {
        filterNewsScreenElements.getCategoryField().perform(replaceText(category));
        SystemClock.sleep(2000);
        filterNewsScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(2000);
        filterNewsScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    @Step("Нажатие на кнопку отмены поиска")
    public void clickingOnTheCancelSearchButton() {
        filterNewsScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на кнопку поиска")
    public void clickingOnTheSearchButton() {
        filterNewsScreenElements.getFilterButton().perform(click());
    }

    @Step("Ввод категории")
    public void enteringCategory(String category) {
        filterNewsScreenElements.getCategoryField().perform(replaceText(category));
    }

    @Step("Ввод начала даты")
    public void enteringTheStartDate(String dateStartInput) {
        filterNewsScreenElements.getDateStartField().perform(replaceText(dateStartInput));
    }

    @Step("Ввод конца даты")
    public void enteringTheEndOfTheDate(String dateEndInput) {
        filterNewsScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    @Step("Ввод дат")
    public void enteringDates(String dateStartInput, String dateEndInput) {
        filterNewsScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        filterNewsScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    @Step("Проверка названия экрана для поиска новостей")
    public void checkingTheScreenNameForNewsSearch() {
        filterNewsScreenElements.getNameFilterNews().check(matches(isDisplayed()));
    }

    @Step("Проверка идентифицирующих названий полей")
    public void checkingIdentifyingFieldNames() {
        filterNewsScreenElements.getFieldNameCategory().check(matches(isDisplayed()));
        filterNewsScreenElements.getFieldNameStartDate().check(matches(isDisplayed()));
        filterNewsScreenElements.getFieldNameEndDate().check(matches(isDisplayed()));
    }
}
