package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.startsWith;

import android.os.SystemClock;

import ru.iteco.fmhandroid.ui.screenElements.AdvancedNewsSearchScreenElements;
import io.qameta.allure.kotlin.Allure;

public class AdvancedNewsSearchScreenStep {
    AdvancedNewsSearchScreenElements advancedNewsSearchScreenElements = new AdvancedNewsSearchScreenElements();

    public void checkingTheNameOfTheAdvancedSearchScreen() {
        Allure.step("Проверка названия экрана расширенного поиска");
        advancedNewsSearchScreenElements.getNamePage().check(matches(isDisplayed()));
    }

    public void fillingInFieldsForDateSearch(String dateStartInput, String dateEndInput) {
        Allure.step("Заполнение полей для поиска по дате");
        advancedNewsSearchScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    public void fillingInTheCategoryField(String category) {
        Allure.step("Заполнение поля категории");
        advancedNewsSearchScreenElements.getFieldNameCategory().perform(replaceText(category));
    }

    public void checkingTheVisibilityOfIdentifyingFieldNames() {
        Allure.step("Проверка видимости идентифицирующих названий полей");
        advancedNewsSearchScreenElements.getFieldNameCategory().check(matches(isDisplayed()));
        advancedNewsSearchScreenElements.getFieldNameStartDate().check(matches(isDisplayed()));
        advancedNewsSearchScreenElements.getFieldNameEndDate().check(matches(isDisplayed()));
    }

    public void checkingTheVisibilityOfTheNamesOfCheckBoxes() {
        Allure.step("Проверка видимости названий чек боксов");
        advancedNewsSearchScreenElements.getCheckBoxActive().check(matches(isDisplayed()));
        advancedNewsSearchScreenElements.getCheckBoxNotActive().check(matches(isDisplayed()));
    }

    public void fillingInFieldsWithSearchData(String category, String dateStartInput, String dateEndInput) {
        Allure.step("Заполнение полей данными для поиска");
        advancedNewsSearchScreenElements.getFieldNameCategory().perform(replaceText(category));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getFieldNameStartDate().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getFieldNameEndDate().perform(replaceText(dateEndInput));
    }

    public void fillingInTheFieldsForTheDate(String dateStartInput, String dateEndInput) {
        Allure.step("Заполнение полей для даты");
        advancedNewsSearchScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    public void checkingTheTextWhenNewsIsNotFound() {
        Allure.step("Проверка текста при ненайденных новостях");
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
    }

    public void clickingOnTheFilterButtonToSearchForNews() {
        Allure.step("Нажатие на кнопку filter поиска новостей");
        advancedNewsSearchScreenElements.getFilterButton().perform(click());
    }

    public void clickingOnTheCancelSearchButton() {
        Allure.step("Нажатие на кнопку Cancel отмена поиска");
        advancedNewsSearchScreenElements.getCancelButton().perform(click());
    }

    public void clickingOnTheActiveCheckBox() {
        Allure.step("Нажатие на чек бокс Active");
        advancedNewsSearchScreenElements.getCheckBoxNotActive().perform(click());
    }

    public void clickingOnTheNotActiveCheckBox() {
        Allure.step("Нажатие на чек бокс Not Active");
        advancedNewsSearchScreenElements.getCheckBoxActive().perform(click());
    }
}
