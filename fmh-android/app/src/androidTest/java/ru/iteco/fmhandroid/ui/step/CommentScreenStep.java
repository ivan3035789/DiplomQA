package ru.iteco.fmhandroid.ui.step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

import androidx.annotation.NonNull;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.ui.screenElements.CommentScreenElements;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CommentScreenStep {

    CommentScreenElements commentScreenElements = new CommentScreenElements();

    public void enteringAnIncorrectLanguageTextComment(String textComment) {
        Allure.step("Ввод неверного языкового текстового комментария");
        commentScreenElements.getCommentFieldName().perform(typeText(textComment));
    }

    public void validLanguageTextComment(String textComment) {
        Allure.step("Ввод верного языкового текстового комментария");
        commentScreenElements.getCommentFieldName().perform(replaceText(textComment));
    }

    public void clickingOnTheSaveCommentButton() {
        Allure.step("Нажатие на кнопку сохранения комментария");
        commentScreenElements.getSaveButton().perform(click());
    }

    public void clickingOnTheButtonToCancelAddingComment() {
        Allure.step("Нажатие на кнопку отмены добавления комментария");
        commentScreenElements.getCancelButton().perform(click());
    }

    public void checkingTheEntryToTheCommentScreen() {
        Allure.step("Проверка входа в экран комментария");
        commentScreenElements.getSaveButton().check(matches(isDisplayed()));
        commentScreenElements.getCancelButton().check(matches(isDisplayed()));
        commentScreenElements.getCommentFieldName().check(matches(isDisplayed()));
    }

    public void checkTheFieldIsNotFilledWithText() {
        Allure.step("Проверка поле не заполнено текстом");
        commentScreenElements.getCommentFieldName().check(matches(withText(""))).check(matches(isDisplayed()));
    }

    public void checkTheFieldIsFilledWithText(String textComment) {
        Allure.step("Проверка поле заполнено текстом");
        commentScreenElements.getCommentFieldName().check(matches(withText(textComment))).check(matches(isDisplayed()));
    }

    public void checkingTheFieldCannotBeEmpty(@NonNull AppActivity activity, String text) {
        Allure.step("Проверка появления предупреждающего сообщения The field cannot be empty.");
        onView(withText(text)).inRoot(withDecorView(Matchers.not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
