package androidTest.java.ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CommentScreenElements;
import io.qameta.allure.kotlin.Step;

public class CommentScreenStep {

    CommentScreenElements commentScreenElements = new CommentScreenElements();

    @Step("Ввод неверного языкового текстового комментария")
    public void enteringAnIncorrectLanguageTextComment(String textComment) {
        commentScreenElements.getCommentFieldName().perform(typeText(textComment));
    }

    @Step("Ввод верного языкового текстового комментария")
    public void validLanguageTextComment(String textComment) {
        commentScreenElements.getCommentFieldName().perform(replaceText(textComment));
    }

    @Step("Нажатие на кнопку сохранения комментария")
    public void clickingOnTheSaveCommentButton() {
        commentScreenElements.getSaveButton().perform(click());
    }

    @Step("Нажатие на кнопку отмены добавления комментария")
    public void clickingOnTheButtonToCancelAddingComment() {
        commentScreenElements.getCancelButton().perform(click());
    }

    @Step("Проверка входа в экран комментария")
    public void checkingTheEntryToTheCommentScreen() {
        commentScreenElements.getSaveButton().check(matches(isDisplayed()));
        commentScreenElements.getCancelButton().check(matches(isDisplayed()));
        commentScreenElements.getCommentFieldName().check(matches(isDisplayed()));
    }

    @Step("Проверка поле не заполнено текстом")
    public void checkTheFieldIsNotFilledWithText() {
        commentScreenElements.getCommentFieldName().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    @Step("Проверка поле заполнено текстом")
    public void checkTheFieldIsFilledWithText(String textComment) {
        commentScreenElements.getCommentFieldName().check(matches(withText(textComment))).check(matches(isDisplayed()));
    }
}
