package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.startsWith;

import android.os.SystemClock;

import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.AdvancedNewsSearchScreenElements;
import io.qameta.allure.kotlin.Step;

public class AdvancedNewsSearchScreenStep {
    AdvancedNewsSearchScreenElements advancedNewsSearchScreenElements = new AdvancedNewsSearchScreenElements();

    @Step("Проверка названия экрана расширенного поиска")
    public void checkingTheNameOfTheAdvancedSearchScreen() {
        advancedNewsSearchScreenElements.getNamePage().check(matches(isDisplayed()));
    }

    @Step("Заполнение полей для поиска по дате")
    public void fillingInFieldsForDateSearch(String dateStartInput, String dateEndInput) {
        advancedNewsSearchScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    @Step("Заполнение поля категории")
    public void fillingInTheCategoryField(String category) {
        advancedNewsSearchScreenElements.getFieldNameCategory().perform(replaceText(category));
    }

    @Step("Проверка видимости идентифицирующих названий полей")
    public void checkingTheVisibilityOfIdentifyingFieldNames() {
        advancedNewsSearchScreenElements.getFieldNameCategory().check(matches(isDisplayed()));
        advancedNewsSearchScreenElements.getFieldNameStartDate().check(matches(isDisplayed()));
        advancedNewsSearchScreenElements.getFieldNameEndDate().check(matches(isDisplayed()));
    }

    @Step("Проверка видимости названий чек боксов")
    public void checkingTheVisibilityOfTheNamesOfCheckBoxes() {
        advancedNewsSearchScreenElements.getCheckBoxActive().check(matches(isDisplayed()));
        advancedNewsSearchScreenElements.getCheckBoxNotActive().check(matches(isDisplayed()));
    }

    @Step("Заполнение полей данными для поиска")
    public void fillingInFieldsWithSearchData(String category, String dateStartInput, String dateEndInput) {
        advancedNewsSearchScreenElements.getFieldNameCategory().perform(replaceText(category));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getFieldNameStartDate().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getFieldNameEndDate().perform(replaceText(dateEndInput));
    }

    @Step("Заполнение полей для даты")
    public void fillingInTheFieldsForTheDate(String dateStartInput, String dateEndInput) {
        advancedNewsSearchScreenElements.getDateStartField().perform(replaceText(dateStartInput));
        SystemClock.sleep(3000);
        advancedNewsSearchScreenElements.getDateEndField().perform(replaceText(dateEndInput));
    }

    @Step("Проверка текста при ненайденных новостях")
    public void checkingTheTextWhenNewsIsNotFound() {
        onView(withText(startsWith("There is nothing here yet"))).check(matches(isDisplayed()));
    }

    @Step("Нажатие на кнопку filter поиска новостей")
    public void clickingOnTheFilterButtonToSearchForNews() {
        advancedNewsSearchScreenElements.getFilterButton().perform(click());
    }

    @Step("Нажатие на кнопку Cancel отмена поиска")
    public void clickingOnTheCancelSearchButton() {
        advancedNewsSearchScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на чек бокс Active")
    public void clickingOnTheActiveCheckBox() {
        advancedNewsSearchScreenElements.getCheckBoxNotActive().perform(click());
    }

    @Step("Нажатие на чек бокс Not Active")
    public void clickingOnTheNotActiveCheckBox() {
        advancedNewsSearchScreenElements.getCheckBoxActive().perform(click());
    }
}
