package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomCategory;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.os.SystemClock;

import org.hamcrest.core.IsNot;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CreatingNewsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class CreatingNewsScreenStep {

    CreatingNewsScreenElements creatingNewsScreenElements = new CreatingNewsScreenElements();

    @Step("Нажатие на поле категория")
    public void clickingOnTheCategoryField() {
        creatingNewsScreenElements.getCategoryFieldNews().perform(click()).perform(closeSoftKeyboard());
    }

    @Step("Нажатие на поле дата")
    public void clickingOnTheDateField() {
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
    }

    @Step("Нажатие на поле время")
    public void clickingOnTheTimeField() {
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
    }

    @Step("Нажатие на кнопку выхода из создания новости")
    public void clickingOnTheExitButtonFromNewsCreation() {
        creatingNewsScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на кнопку подтверждения выхода из создания новости")
    public void clickingOnTheConfirmationButtonToExitTheNewsCreation() {
        creatingNewsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    @Step("Нажатие на кнопку сохранения новости")
    public void clickingOnTheSaveNewsButton() {
        creatingNewsScreenElements.getSaveButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены создания новости")
    public void clickingOnTheCancelNewsCreationButton() {
        creatingNewsScreenElements.getCancelButton2().perform(scrollTo(), click());
    }

    @Step("Нажатие на кнопку подтверждения отмены создания новости")
    public void clickingOnTheConfirmationButtonToCancelTheCreationOfTheNews() {
        creatingNewsScreenElements.getOkButton().perform(scrollTo(), click());
    }

    @Step("Ввод валидного языка")
    public void validLanguage(String category, String title, String description) {
        creatingNewsScreenElements.getTitleFieldNews().perform(typeText(title));
        creatingNewsScreenElements.getCategoryFieldNews().perform(typeText(category));
        creatingNewsScreenElements.getDescriptionFieldNews().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    @Step("Ввод невалидного языка")
    public void invalidLanguage(String title, String description) {
        creatingNewsScreenElements.getTitleFieldNews().perform(typeText(title));
        creatingNewsScreenElements.getDescriptionFieldNews().perform(typeText(description)).perform(closeSoftKeyboard());
    }

    @Step("Заполнение полей валидными данными")
    public void fillingInFieldsWithValidData(String text) {
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenStep calendarScreenStep = new CalendarScreenStep();

        creatingNewsScreenElements.getCategoryFieldNews().perform(replaceText(randomCategory())).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getTitleFieldNews().perform(replaceText(textSymbol(5)), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
        calendarScreenStep.clickingOnTheConfirmButton();
        creatingNewsScreenElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
    }

    @Step("Заполнение поля Category")
    public void fillingInTheCategoryField(String text) {
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
                                                                      //randomCategory()
        creatingNewsScreenElements.getCategoryFieldNews().perform(replaceText(text)).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getTitleFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getDescriptionFieldNews().perform(click());
        creatingNewsScreenElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
    }

    @Step("Выбор новости")
    public void choosingNews(int position) {
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();
        controlPanelScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Проверка названия экрана Creating News")
    public void checkingTheNameOfTheCreatingNewsScreen() {
        creatingNewsScreenElements.getCreatingNameScreen().check(matches(isDisplayed()));
        creatingNewsScreenElements.getNewsNameScreen().check(matches(isDisplayed()));
    }

    @Step("Проверка идентифицирующих названий полей для заполнения")
    public void checkNameFieldInCreatingNews() {
        creatingNewsScreenElements.getCategoryName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getTitleName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getPublicationDateName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getTimeName().check(matches(isDisplayed()));
        creatingNewsScreenElements.getDescriptionName().check(matches(isDisplayed()));
    }

    @Step("Проверка на отсутствие в полях слов из русских букв")
    public void checkingForTheAbsenceOfWordsFromRussianLettersInTheFields(String invalidLanguageText) {
        creatingNewsScreenElements.getTitleFieldNews().check(matches(IsNot.not(withText(invalidLanguageText)))).check(matches(isDisplayed()));
        creatingNewsScreenElements.getDescriptionFieldNews().check(matches(IsNot.not(withText(invalidLanguageText)))).check(matches(isDisplayed()));
    }

    @Step("Проверка на присудствие в полях слов из английских букв")
    public void checkingForThePresenceOfWordsFromEnglishLettersInTheFields(String text) {
        creatingNewsScreenElements.getCategoryFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        creatingNewsScreenElements.getTitleFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
        creatingNewsScreenElements.getDescriptionFieldNews().check(matches(withText(text))).check(matches(isDisplayed()));
    }

    @Step("Сравнение данных созданной новости с данными новости первой из списка")
    public void comparingTheDataOfTheCreatedNewsWithTheDataOfTheFirstNewsFromTheList(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        assertNotEquals(nameNewsItWas, nameNewsItWasHasBecomes);

        if (publicationDateNewsItWas.equals(publicationDateNewsItWasHasBecomes)) {
            assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        } else {
            assertNotEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        }
        if (creationDateNewsItWas.equals(creationDateNewsItWasHasBecomes)) {
            assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        } else {
            assertNotEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        }
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertNotEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    @Step("Проверка данные первой новости из списка должны совпадать после отмены создания новости")
    public void checkingTheDataOfTheFirstNewsFromTheListMustMatchAfterCancelingTheCreationOfTheNews(
            String nameNewsItWas, String nameNewsItWasHasBecomes, String publicationDateNewsItWas, String publicationDateNewsItWasHasBecomes,
            String creationDateNewsItWas, String creationDateNewsItWasHasBecomes, String authorNewsItWas, String authorNewsItWasHasBecomes,
            String descriptionNewsItWas, String descriptionNewsItWasHasBecomes) {
        assertEquals(nameNewsItWas, nameNewsItWasHasBecomes);
        assertEquals(publicationDateNewsItWas, publicationDateNewsItWasHasBecomes);
        assertEquals(creationDateNewsItWas, creationDateNewsItWasHasBecomes);
        assertEquals(authorNewsItWas, authorNewsItWasHasBecomes);
        assertEquals(descriptionNewsItWas, descriptionNewsItWasHasBecomes);
    }

    public String nameNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public String publicationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0)));
    }

    public String creationDateNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_create_date_text_view), 0)));
    }

    public String authorNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_author_name_text_view), 0)));
    }

    public String descriptionNews() {
        return Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), 0)));
    }

}
