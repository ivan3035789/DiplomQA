package androidTest.java.ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;
import static androidTest.java.ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class ClaimsScreenElements {

    private final ViewInteraction screenNameClaims = onView(withText("Claims"));
    private final ViewInteraction filtering = onView(withId(R.id.claim_filter_dialog_title));

    private final ViewInteraction creating = onView(withId(R.id.custom_app_bar_title_text_view));

    private final ViewInteraction createClaimsButton = onView(allOf(withId(R.id.add_new_claim_material_button), withContentDescription("Add new claim button")));

    private final ViewInteraction statusInProgress = onView(
            allOf(withId(R.id.status_label_text_view), withText("In progress"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction statusOpen = onView(
            allOf(withId(R.id.status_label_text_view), withText("Open"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction statusExecuted = onView(
            allOf(withId(R.id.status_label_text_view), withText("Executed"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                    isDisplayed()));

    private final ViewInteraction statusCancelled = onView(
            allOf(withId(R.id.status_label_text_view), withText("Canceled"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                    isDisplayed()));

    private final ViewInteraction exitButton = onView(
            allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("com.google.android.material.card.MaterialCardView")),
                                    0),
                            23)));

    private final ViewInteraction status = onView(withId(R.id.status_label_text_view));

    private final ViewInteraction blockClaims = onView(
            allOf(withId(R.id.claim_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.all_claims_cards_block_constraint_layout),
                            4)));

    private final ViewInteraction filteringButton = onView(
            allOf(withId(R.id.filters_material_button)));

    private final ViewInteraction executorText = onView(withId(R.id.executor_name_text_view));
    private final ViewInteraction planDateText = onView(withId(R.id.plane_date_text_view));
    private final ViewInteraction timeText = onView(withId(R.id.plan_time_text_view));
    private final ViewInteraction authorText = onView(withId(R.id.author_name_text_view));
    private final ViewInteraction descriptionText = onView(withId(R.id.description_text_view));

    private final ViewInteraction title = onView(
            allOf(withId(R.id.title_label_text_view), withText("Title"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction executorLabel = onView(
            allOf(withId(R.id.executor_name_label_text_view), withText("Executor"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction planDateLabel = onView(
            allOf(withId(R.id.plane_date_label_text_view), withText("Plan date"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction authorLabel = onView(
            allOf(withId(R.id.author_label_text_view), withText("Author"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction createdLabel = onView(
            allOf(withId(R.id.create_data_label_text_view), withText("Created"),
                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));

    private final ViewInteraction buttonChangeStatus = onView(
            allOf(withId(R.id.status_processing_image_button), withContentDescription("button change status"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("com.google.android.material.card.MaterialCardView")),
                                    0),
                            24),
                    isDisplayed()));

    private final ViewInteraction addComment = onView(withId(R.id.add_comment_image_button));
    private final ViewInteraction commentField = onView(withId(R.id.editText));

    private final ViewInteraction closeImageButton = onView(
            allOf(withId(R.id.close_image_button), withContentDescription("button close"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withClassName(is("com.google.android.material.card.MaterialCardView")),
                                    0),
                            23)));

    private final ViewInteraction takeToWork = onView(
            allOf(withId(android.R.id.title), withText("take to work"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                                    0),
                            0),
                    isDisplayed()));

    private final ViewInteraction cancelButton = onView(
            allOf(withId(android.R.id.title), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                                    0),
                            0),
                    isDisplayed()));

    private final ViewInteraction ThrowOff = onView(
            allOf(withId(android.R.id.title), withText("Throw off"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                                    0),
                            0)));

    private final ViewInteraction toExecute = onView(
            allOf(withId(android.R.id.title), withText("To execute"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                                    0),
                            0)));

    private final ViewInteraction okButtonAddComment = onView(withId(android.R.id.button1));

    private final ViewInteraction cancelAddCommentButton = onView(
            allOf(withId(android.R.id.button2), withText("Cancel"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(androidx.appcompat.R.id.buttonPanel),
                                    0),
                            2)));

    private final ViewInteraction editClaimsButton = onView(withId(R.id.edit_processing_image_button));
    private final ViewInteraction editCommentButton = onView(
            allOf(withId(R.id.edit_comment_image_button), withContentDescription("button edit comment"),
    childAtPosition(
            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), withId(R.id.claim_comments_list_recycler_view),
                                        0),
                                                1)));

    private final ViewInteraction commentStatus = onView(withIndex(withId(R.id.claim_comments_list_recycler_view), 0));
    private final ViewInteraction blockComment = onView(withId(R.id.claim_comments_list_recycler_view));

    public ViewInteraction getFiltering() {
        return filtering;
    }

    public ViewInteraction getStatusInProgress() {
        return statusInProgress;
    }

    public ViewInteraction getStatusOpen() {
        return statusOpen;
    }

    public ViewInteraction getStatusExecuted() {
        return statusExecuted;
    }

    public ViewInteraction getStatusCancelled() {
        return statusCancelled;
    }

    public ViewInteraction getBlockComment() {
        return blockComment;
    }

    public ViewInteraction getCommentField() {
        return commentField;
    }

    public ViewInteraction getExecutorText() {
        return executorText;
    }

    public ViewInteraction getEditCommentButton() {
        return editCommentButton;
    }

    public ViewInteraction getDescriptionText() {
        return descriptionText;
    }

    public ViewInteraction getPlanDateLabel() {
        return planDateLabel;
    }

    public ViewInteraction getPlanDateText() {
        return planDateText;
    }

    public ViewInteraction getTimeText() {
        return timeText;
    }

    public ViewInteraction getAuthorText() {
        return authorText;
    }

    public ViewInteraction getToExecute() {
        return toExecute;
    }

    public ViewInteraction getOkButtonAddComment() {
        return okButtonAddComment;
    }

    public ViewInteraction getCommentStatus() {
        return commentStatus;
    }

    public ViewInteraction getCancelAddCommentButton() {
        return cancelAddCommentButton;
    }

    public ViewInteraction getThrowOff() {
        return ThrowOff;
    }

    public ViewInteraction getAddComment() {
        return addComment;
    }

    public ViewInteraction getCloseImageButton() {
        return closeImageButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getButtonChangeStatus() {
        return buttonChangeStatus;
    }

    public ViewInteraction getTakeToWork() {
        return takeToWork;
    }

    public ViewInteraction getEditClaimsButton() {
        return editClaimsButton;
    }

    public ViewInteraction getExitButton() {
        return exitButton;
    }

    public ViewInteraction getStatus() {
        return status;
    }

    public ViewInteraction getTitle() {
        return title;
    }

    public ViewInteraction getExecutorLabel() {
        return executorLabel;
    }

    public ViewInteraction getAuthorLabel() {
        return authorLabel;
    }

    public ViewInteraction getCreatedLabel() {
        return createdLabel;
    }

    public ViewInteraction getBlockClaims() {
        return blockClaims;
    }

    public ViewInteraction getFilteringButton() {
        return filteringButton;
    }

    public ViewInteraction getCreateClaimsButton() {
        return createClaimsButton;
    }

    public ViewInteraction getScreenNameClaims() {
        return screenNameClaims;
    }

    public ViewInteraction getCreating() {
        return creating;
    }
}
