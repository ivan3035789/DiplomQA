package androidTest.java.ru.iteco.fmhandroid.ui.test;

import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Rand.randomClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.Text.textSymbol;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidTest.java.ru.iteco.fmhandroid.ui.data.Helper;
import androidTest.java.ru.iteco.fmhandroid.ui.step.AuthorizationScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ClaimsScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.CommentScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CommentScreenTest {
    MainScreenStep mainScreenStep = new MainScreenStep();
    AuthorizationScreenStep authorizationScreenStep = new AuthorizationScreenStep();
    ClaimsScreenStep claimsScreenStep = new ClaimsScreenStep();
    CommentScreenStep commentScreenStep = new CommentScreenStep();

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenStep.checkNameMainScreen();
        } catch (NoMatchingViewException e) {
            authorizationScreenStep.validLoginPassword(Helper.authInfo());
            SystemClock.sleep(5000);
        }
    }

    @After
    public void setUp() {
        SystemClock.sleep(3000);
    }

    @Test
    @DisplayName("Must enter the comment creation section")
    @Description("В этом тест кейсе мы проверяем, что при клике на кнопку \"+\" в поле \"Add comment\" пользователь переходит в раздел создания коментария ")
    public void mustEnterTheCommentCreationSection() {
        int position = randomClaims(1, 2, 3);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(5000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        commentScreenStep.checkingTheEntryToTheCommentScreen();
    }

    @Test
    @DisplayName("Fields should not be filled in with Russian letters")
    @Description("В этом тест кейсе мы проверяем, что поле \"comment\", не заполняется русскими буквами")
    public void fieldsShouldNotBeFilledInWithRussianLetters() {
        int position = randomClaims(1, 2, 3);
        String invalidLanguageTextComment = "Привет мир";

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        try {
            commentScreenStep.enteringAnIncorrectLanguageTextComment(invalidLanguageTextComment);
        } catch (RuntimeException e) {

        } finally {
            commentScreenStep.checkTheFieldIsNotFilledWithText();
        }
    }

    @Test
    @DisplayName("The fields must be filled in with English letters")
    @Description("В этом тест кейсе мы проверяем, что поле \"comment\", заполняется английскими буквами")
    public void theFieldsMustBeFilledInWithEnglishLetters() {
        int position = randomClaims(1, 2, 3);
        String validTextComment = textSymbol(5);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        commentScreenStep.validLanguageTextComment(validTextComment);
        commentScreenStep.checkTheFieldIsFilledWithText(validTextComment);
    }

    @Test
    @DisplayName("A comment should be added")
    @Description("В этом тест кейсе мы проверяем что после заполнения поля \"comment\" коментарием, и после нажатия на кнопку \"SAVE\", должен добавиться коментарий")
    public void commentShouldBeAdded() {
        int position = randomClaims(1, 2, 3);
        String validTextComment = textSymbol(5);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        SystemClock.sleep(3000);
        commentScreenStep.validLanguageTextComment(validTextComment);
        SystemClock.sleep(3000);
        commentScreenStep.clickingOnTheSaveCommentButton();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);

        claimsScreenStep.checkingCommentShouldBeAdded(validTextComment);
    }

    @Test
    @DisplayName("Canceling adding a comment")
    @Description("В этом тест кейсе мы проверяем что после заполнения поля \"comment\" коментарием, и после нажатия на кнопку \"CANCEL\", не должен добавиться коментарий")
    public void cancelingAddingComment() {
        int position = randomClaims(1, 2, 3);
        String validTextComment = textSymbol(5);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);

        claimsScreenStep.clickingOnTheAddCommentButton();
        SystemClock.sleep(3000);
        commentScreenStep.validLanguageTextComment(validTextComment);
        SystemClock.sleep(3000);
        commentScreenStep.clickingOnTheButtonToCancelAddingComment();
        SystemClock.sleep(3000);
        claimsScreenStep.checkingTheCommentShouldNotBeCreated(validTextComment);
    }

    @Test
    @DisplayName("A warning message should appear when the comment field is empty")
    @Description("В этом тест кейсе мы проверяем что при незаполнении поля \"comment\", после нажатия на кнопку \"SAVE\", появляется предупреждающая надпись \"The field cannot be empty\" ")
    public void warningMessageShouldAppearWhenTheCommentFieldIsEmpty() {
        int position = randomClaims(1, 2, 3);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        commentScreenStep.clickingOnTheSaveCommentButton();
        commentScreenStep.checkingTheFieldCannotBeEmpty(ActivityTestRule.getActivity(), "The field cannot be empty.");
    }

    @Test
    @DisplayName("The comment in the claim should be edited")
    @Description("В этом тест кейсе мы проверяем что при нажатии на кнопку с иконкой \"блокнот с карандашом\" пользователь попадает в раздел создания, редактирования коментариев, имеется возможность отредактировать коментарий")
    public void theCommentInTheClaimShouldBeEdited() {
        int position = randomClaims(1, 2, 3);
        String validTextComment = textSymbol(5);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        SystemClock.sleep(3000);
        claimsScreenStep.clickingOnTheButtonToEnterTheCommentEditingScreen();
        SystemClock.sleep(3000);
        commentScreenStep.validLanguageTextComment(validTextComment);
        SystemClock.sleep(3000);
        commentScreenStep.clickingOnTheSaveCommentButton();
        SystemClock.sleep(3000);
        claimsScreenStep.checkingTheCommentBeforeEditingAndAfter(validTextComment);
    }

    @Test
    @DisplayName("Two comments should be added")
    @Description("В этом тест кейсе мы проверяем что имеется возможность добавить несколько коментариев в одину \"претензию\" ")
    public void twoCommentsShouldBeAdded() {
        int position = randomClaims(1, 2, 3);
        String validTextComment1 = textSymbol(5);
        String validTextComment2 = textSymbol(5);

        mainScreenStep.clickingOnTheAllClaimsTextLink();
        claimsScreenStep.choosingRandomClaim(position);
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(5000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        SystemClock.sleep(3000);
        commentScreenStep.validLanguageTextComment(validTextComment1);
        SystemClock.sleep(3000);
        commentScreenStep.clickingOnTheSaveCommentButton();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(5000);
        claimsScreenStep.checkingTheAddedComment(validTextComment1);
        SystemClock.sleep(5000);
        claimsScreenStep.clickingOnTheAddCommentButton();
        SystemClock.sleep(3000);
        commentScreenStep.validLanguageTextComment(validTextComment2);
        SystemClock.sleep(3000);
        commentScreenStep.clickingOnTheSaveCommentButton();
        SystemClock.sleep(3000);
        Helper.Swipes.swipeToBottom();
        SystemClock.sleep(5000);
        claimsScreenStep.checkingTheAddedComment(validTextComment2);
    }
}
