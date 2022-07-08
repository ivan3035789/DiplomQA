package androidTest.java.ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep.enterCreateClaimsActionButton;
import static androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep.enterCreateClaimsAllClaims;
import static androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep.enterCreateClaimsButtonPlus;
import static androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep.enterCreateNews1;
import static androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep.enterCreateNews2;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CalendarScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.ControlPanelScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CreatingClaimsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.CreatingNewsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.EditingNewsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.FilteringWindowScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.MainScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.NewsScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.screenElements.WatchScreenElements;
import androidTest.java.ru.iteco.fmhandroid.ui.step.ControlPanelScreenStep;
import androidTest.java.ru.iteco.fmhandroid.ui.step.MainScreenStep;
import io.bloco.faker.Faker;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class Helper {

    private Helper() {
    }

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    public static class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo authInfo() {
        String login = "login2";
        String password = "password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidAuthInfo() {
        Faker faker = new Faker();
        Random random = new Random();
        String[] invalidLogin = {"login2", "", " ", faker.name.firstName() + "@" + faker.name.lastName()};
        String[] emptyString = {"", " "};
        String[] invalidPassword = {" ", ""};
        String randomEmptyString = emptyString[random.nextInt(emptyString.length)];
        String randomPassword = invalidPassword[random.nextInt(invalidPassword.length)];
        String randomLogin = invalidLogin[random.nextInt(invalidLogin.length)];
        String login = (randomLogin.equals("login2") ? randomEmptyString : invalidLogin[0]);
        String password = (randomPassword.equals("") ? randomEmptyString : invalidPassword[0]);
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidLoginPasswordAuthInfo() {
        Faker faker = new Faker();
        Random random = new Random();
        String[] login = {"login", "login1", "///////", "./..", faker.name.firstName(), faker.name.lastName()};
        String[] password = {"pass", "password", "@@@@@@", "121587"};
        String invalidLogin = login[random.nextInt(login.length)];
        String invalidPassword = password[random.nextInt(password.length)];
        return new AuthInfo(invalidLogin, invalidPassword);
    }

    public static void clickingNextMonth(int quantity) {
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
        for (int i = 0; i < quantity; i++) {
            calendarScreenElements.getNextMonthButton().perform(scrollTo(), click());
        }
    }

    public static void swipeNextMonth(int quantity) {
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
        for (int i = 0; i < quantity; i++) {
            calendarScreenElements.getCalendarMonthView().perform(swipeRight());

        }
    }

    public static void swipePreviousMonth(int quantity) {
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
        for (int i = 0; i < quantity; i++) {
            calendarScreenElements.getCalendarMonthView().perform(swipeLeft());
        }
    }

    public static void clickingPreviousMonth(int quantity) {
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
        for (int i = 0; i < quantity; i++) {
            calendarScreenElements.getPreviousMonthButton().perform(scrollTo(), click());
        }
    }

    public static void createClaims(int quantity) {
        for (int i = 0; i < quantity; i++) {
            createClaim();
        }
    }

    public static void createClaim() {
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
        CreatingClaimsScreenElements creatingClaimsScreenElements = new CreatingClaimsScreenElements();
        MainScreenElements mainScreenElements = new MainScreenElements();

        SystemClock.sleep(2000);
        mainScreenElements.getCreateClaimsButton().perform(click());
        String titleText = Text.textSymbol(5);

        creatingClaimsScreenElements.getTitleClaimField().perform(replaceText(titleText), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getDateClaimField().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getTimeClaimField().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getDescriptionClaimField().perform(replaceText(Text.textSymbol(10)), closeSoftKeyboard());
        SystemClock.sleep(2000);
        creatingClaimsScreenElements.getSaveButton().perform(click());
    }

    public static void createNewsForCategory(String text, String category) {
        MainScreenStep mainScreenStep = new MainScreenStep();
        NewsScreenElements newsScreenElements = new NewsScreenElements();
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();
        CreatingNewsScreenElements creatingNewsScreenElements = new CreatingNewsScreenElements();
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenElements.getEditButton().perform(click());
        controlPanelScreenElements.getCreateNewsButton().perform(click());

        creatingNewsScreenElements.getCategoryFieldNews().perform(replaceText(category)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getTitleFieldNews().perform(replaceText(category), click()).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        creatingNewsScreenElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getSaveButton().perform(click());
    }

    public static void createNews(String text, String category) {
        MainScreenStep mainScreenStep = new MainScreenStep();
        NewsScreenElements newsScreenElements = new NewsScreenElements();
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();
        CreatingNewsScreenElements creatingNewsScreenElements = new CreatingNewsScreenElements();
        WatchScreenElements watchScreenElements = new WatchScreenElements();
        CalendarScreenElements calendarScreenElements = new CalendarScreenElements();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenElements.getEditButton().perform(click());
        controlPanelScreenElements.getCreateNewsButton().perform(click());

        creatingNewsScreenElements.getCategoryFieldNews().perform(replaceText(category)).perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getTitleFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getPublicationDateFieldNews().perform(click());
        watchScreenElements.getOkButton().perform(scrollTo(), click());
        SystemClock.sleep(3000);
        creatingNewsScreenElements.getTimeFieldNews().perform(click());
        calendarScreenElements.getOkButton().perform(scrollTo(), click());
        creatingNewsScreenElements.getDescriptionFieldNews().perform(replaceText(text), click()).perform(closeSoftKeyboard());
        creatingNewsScreenElements.getSaveButton().perform(click());
    }

    public static void deletingNewsUpToTheNumberOfTenControlPanelScreen(int position) {
        MainScreenStep mainScreenStep = new MainScreenStep();
        ControlPanelScreenStep controlPanelScreenStep = new ControlPanelScreenStep();
        mainScreenStep.switchingToTheControlPanel();
        int positionNews = position;

        while (true) {
            try {
                controlPanelScreenStep.clickingOnRandomlySelectedNewsItem(positionNews);
                String newsName = controlPanelScreenStep.nameNews();
                controlPanelScreenStep.clickingOnTheDeleteNewsButtonPosition(newsName);
                SystemClock.sleep(2000);
                controlPanelScreenStep.clickingOnTheConfirmationButtonToDeleteTheNews();
                SystemClock.sleep(2000);
            } catch (RuntimeException exception) {
                break;
            }
            positionNews += 1;
        }
    }


    public static class Rand {

        private static final Random rand = new Random();

        @SafeVarargs
        public static int randomClaims(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        @SafeVarargs
        public static int randomNews(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        @SafeVarargs
        public static int random(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        public static String randomExecutor() {
            String[] executor = {
                    "Смирнов Николай Петрович",
                    "Лебедев Данил Александрович",
                    "Прохорова Зоя Альфредовна",
                    "Смирнов Николай Петрович",
                    "Смирнов Николай Петрович",
                    "Смирнов Николай Петрович",
                    "Редькин Юрий Алексеевич",
                    "Кобелев Игорь Николаевич",
                    "Крайнев Павел Владимирович"
            };
            return executor[rand.nextInt(executor.length)];
        }

        public static String randomCategory() {
            String[] category = {
                    "Объявление",
                    "День рождения",
                    "Зарплата",
                    "Профсоюз",
                    "Праздник",
                    "Массаж",
                    "Благодарность",
                    "Нужна помощь"
            };
            return category[rand.nextInt(category.length)];
        }

        public static int randomDay() {
            int day;
            int localDateDay = LocalDate.now().getDayOfMonth();
            int min = 1;
            int max = 30;
            max -= min;
            int randomDay = (int) ((Math.random() * ++max) + min);
            if (randomDay < localDateDay || randomDay + localDateDay > 28) {
                day = 0;
            } else {
                day = randomDay;
            }
            return day;
        }

        public static int randomMonth() {
            int min = 1;
            int max = 12;
            max -= min;
            return (int) ((Math.random() * ++max) + min);
        }

        public static void randomCheckBox() {
            FilteringWindowScreenElements filteringWindowScreenElements = new FilteringWindowScreenElements();

            int min = 0;
            int max = 3;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);

            if (random == 0) {
                filteringWindowScreenElements.getCheckBoxOpen().perform(click());
            } else if (random == 1) {
                filteringWindowScreenElements.getCheckBoxInProgress().perform(click());
            } else if (random == 2) {
                filteringWindowScreenElements.getCheckBoxExecuted().perform(click());
            } else {
                filteringWindowScreenElements.getCheckBoxCancelled().perform(click());
            }
        }

        public static void randomLogInToClaimsCreation() {
            int min = 1;
            int max = 3;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);

            if (random == 1) {
                enterCreateClaimsAllClaims();
            } else if (random == 2) {
                enterCreateClaimsActionButton();
            } else {
                enterCreateClaimsButtonPlus();
            }
        }

        public static void randomLogInToNewsCreation() {
            int min = 1;
            int max = 2;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);

            if (random == 1) {
                enterCreateNews1();
            } else {
                enterCreateNews2();
            }
        }
    }

    public static class Swipes {

        static void swiper(int start, int end, int delay) {
            long downTime = SystemClock.uptimeMillis();
            long eventTime = SystemClock.uptimeMillis();
            Instrumentation inst = getInstrumentation();

            MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 500, start, 0);
            inst.sendPointerSync(event);
            eventTime = SystemClock.uptimeMillis() + delay;
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 500, end, 0);
            inst.sendPointerSync(event);
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 500, end, 0);
            inst.sendPointerSync(event);
            SystemClock.sleep(2000);
        }

        public static void swipeToBottom() {
            swiper(1000, 100, 0);
        }

        public static void scrollSlowlyDown() {
            swiper(775, 100, 100);
        }

        public static void swipeToTop() {
            swiper(100, 1000, 0);
        }

        public static void scrollSlowlyUp() {
            swiper(100, 775, 100);
        }

    }


    public static void setUpStatusNewsNotActive(int position) {
        ControlPanelScreenElements controlPanelScreenElements = new ControlPanelScreenElements();
        EditingNewsScreenElements editingNewsScreenElements = new EditingNewsScreenElements();
        MainScreenStep mainScreenStep = new MainScreenStep();
        NewsScreenElements newsScreenElements = new NewsScreenElements();

        mainScreenStep.clickingOnTheActionMenuButton();
        mainScreenStep.clickingOnTheNewsName();
        newsScreenElements.getEditButton().perform(click());
        controlPanelScreenElements.getRecyclerView().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
        String nameNewsItWas = Text.getText(controlPanelScreenElements.getNewsItemTitle());
        String statusBefore = Text.getText(onView(allOf(withId(R.id.news_item_published_text_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))));
        onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(nameNewsItWas)))))))).perform(click());
        SystemClock.sleep(3000);
        if (statusBefore.toUpperCase().trim().equals("ACTIVE")) {
            editingNewsScreenElements.getCheckBox().perform(click());
            editingNewsScreenElements.getSaveButton().perform(click());
        } else {
            editingNewsScreenElements.getCancelButton().perform(click());
            editingNewsScreenElements.getOkButton().perform(click());
        }
    }

    public static void checkStatus(String statusClaims) {
        ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
        String[] status = {"Open", "In progress", "Executed", "Cancelled"};

        if (statusClaims.equals("Open")) {
            claimsScreenElements.getStatus().check(matches(withText(status[0])));
        } else {
            claimsScreenElements.getStatus().check(matches(not(withText(status[0]))));
        }
        if (statusClaims.equals("In progress")) {
            claimsScreenElements.getStatus().check(matches(withText(status[1])));
        } else {
            claimsScreenElements.getStatus().check(matches(not(withText(status[1]))));
        }
        if (statusClaims.equals("Executed")) {
            claimsScreenElements.getStatus().check(matches(withText(status[2])));
        } else {
            claimsScreenElements.getStatus().check(matches(not(withText(status[2]))));
        }
        if (statusClaims.equals("Cancelled")) {
            claimsScreenElements.getStatus().check(matches(withText(status[3])));
        } else {
            claimsScreenElements.getStatus().check(matches(not(withText(status[3]))));
        }
    }

    public static class Search {

        public static String searchForAnUncreatedComment(String text) {
            ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();

            boolean notFound = true;
            int position = 0;
            String commentTextString = Text.getText(onView(allOf(withId(R.id.comment_description_text_view),
                    withParent(withParent(withIndex(withId(R.id.claim_comments_list_recycler_view), position))))));
            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException ignore) {
                }
                try {
                    claimsScreenElements.getBlockComment().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                    position += 1;
                } catch (PerformException e) {
                    break;
                }
            }
            return commentTextString;
        }

        public static void textSearchClaims(String text) {
            ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();

            boolean notFound = true;
            int position = 0;
            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException ignored) {
                }
                claimsScreenElements.getBlockClaims().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                position += 1;
            }
            SystemClock.sleep(3000);
            claimsScreenElements.getBlockClaims().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position - 1, click()));
        }

        public static String searchForAnUncreatedClaim(String text) {
            ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();

            boolean notFound = true;
            int position = 0;

            String commentTextString = Text.getText(onView(allOf(withId(R.id.description_material_text_view),
                    withParent(withParent(withIndex(withId(R.id.claim_list_card), position))))));
            while (notFound) {
                try {
                    onView(withText(text)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException ignore) {
                }
                try {
                    claimsScreenElements.getBlockClaims().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                    position += 1;
                } catch (PerformException e) {
                    break;
                }
            }
            return commentTextString;
        }
    }

    public static class DateTime {
        public static ViewInteraction headerCalendarDate(String dayOfWeek, String month, String day) {
            return onView(allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText(dayOfWeek + ", " + month + " " + day),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    0),
                            1)));
        }

        public static ViewInteraction headerCalendarYear(String year) {
            return onView(allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText(year),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.LinearLayout")),
                                    0),
                            0)));
        }

        public static void settingTheDate(int year, int month, int day) {
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day)).perform(click());
        }

        public static Date dateFormat(String date) throws ParseException {
            final SimpleDateFormat format = new SimpleDateFormat(
                    "dd.MM.yyyy", Locale.US);
            format.setLenient(false);
            return format.parse(date);
        }

        public static String localDate() {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String invalidGeneratorDate() {
            LocalDate startDate = LocalDate.now().plusYears(3);
            long start = startDate.getDayOfMonth();
            LocalDate endDate = LocalDate.now().plusYears(3);
            long end = endDate.lengthOfYear();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            return startDate.plusDays(randomEpochDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generatorDate() {
            LocalDate startDate = LocalDate.now().plusDays(3);
            long start = startDate.getDayOfMonth();
            LocalDate endDate = LocalDate.now().plusYears(1);
            long end = endDate.lengthOfYear();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            return startDate.plusDays(randomEpochDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generatorDate2() {
            LocalDate startDate = LocalDate.now().minusYears(1);
            long start = startDate.getDayOfMonth();
            LocalDate endDate = LocalDate.now().plusDays(3);
            long end = endDate.lengthOfYear();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            return startDate.plusDays(randomEpochDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String randomHour23() {
            String hour;
            int min = 0;
            int max = 9;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            if (random < 10) {
                hour = "0" + random;
            } else {
                hour = String.valueOf(random);
            }
            return hour;
        }

        public static String randomMinute59() {
            String minute;
            int min = 0;
            int max = 59;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            if (random < 10) {
                minute = "0" + random;
            } else {
                minute = String.valueOf(random);
            }
            return minute;
        }

        public static String invalidHour() {
            int min = 24;
            int max = 99;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            return String.valueOf(random);
        }

        public static String invalidMinute() {
            String minute;
            int min = 0;
            int max = 99;
            max -= min;
            int random = (int) ((Math.random() * ++max) + min);
            if (random < 10) {
                minute = "0" + random;
            } else {
                minute = String.valueOf(random);
            }
            return minute;
        }
    }

    public static class Text {

        public static String text() {
            Faker faker = new Faker();
            return faker.name.lastName();
        }

        public static String firstUpperCase(String text) {
            if (text == null || text.isEmpty())
                return "";
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }

        public static String textSymbol(int numberOfLetters) {
            Random random = new Random();
            String randomText;
            String[] texts = {
                    " ", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                    "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "!", "@", "#", "$", "%", "^", "&",
                    "*", "(", ")", "_", "+"
            };
            randomText = texts[random.nextInt(texts.length)];
            for (int i = 1; i < numberOfLetters; i++) {
                randomText = randomText.concat(texts[random.nextInt(texts.length)]);
            }
            return randomText;
        }

        public static String text51Symbol() {
            return "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=+_)(*&";
        }


        public static String getText(ViewInteraction matcher) {
            final String[] text = new String[1];
            ViewAction viewAction = new ViewAction() {

                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(TextView.class);
                }

                @Override
                public String getDescription() {
                    return "Text of the view";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    TextView textView = (TextView) view;
                    text[0] = textView.getText().toString();
                }
            };

            matcher.perform(viewAction);

            return text[0];
        }
    }

    public static Matcher<View> childAtPosition(Matcher<View> matcher, final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}