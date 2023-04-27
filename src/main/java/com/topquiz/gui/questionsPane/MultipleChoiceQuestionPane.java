package com.topquiz.gui.questionsPane;

import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.SimpleQuestion;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
/**

 A class representing a multiple choice question pane. Displays the question and a list of possible answers, allowing the user to select one answer.
 */
public class MultipleChoiceQuestionPane extends QuestionPane {
    private HBox questionHBox = new HBox();
    private ListView<String> answersListView;
    /**

     Constructs a MultipleChoiceQuestionPane object.
     @param question The multiple choice question to be displayed.
     */
    public MultipleChoiceQuestionPane(Question question) {
        super(question);
        init();
    }
    /**

     Initializes the layout of the multiple choice question pane.
     */
    private void init() {
        questionHBox = new HBox();
        questionHBox.setStyle("-fx-background-color:#dfdede;");
        questionHBox.setAlignment(Pos.CENTER_LEFT);

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setFont(new Font(16));
        textArea.setEditable(false);
        textArea.setPrefSize(260, 300);
        textArea.setText(question.getQuestion());
        questionHBox.getChildren().add(textArea);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(260, 300);
        answersListView = new ListView<>();
        answersListView.setPrefSize(260, 300);
        answersListView.getItems().addAll(((SimpleQuestion) question).getAnswers());
        scrollPane.setContent(answersListView);
        questionHBox.getChildren().add(scrollPane);

        questionHBox.setVisible(true);
    }
    /**

     Checks if the selected answer is correct.
     @return True if the selected answer is correct, false otherwise.
     */
    public boolean isCorrectAnswer() {
        ObservableList<String> viewObservableList = answersListView.getSelectionModel().getSelectedItems();
        if (viewObservableList.size() > 0) {
            String selectedAnswer = viewObservableList.get(0);
            return selectedAnswer.equals(((SimpleQuestion) question).getCorrectAnswer());
        }
        return false;
    }
    /**

     Gets the multiple choice question pane as a JavaFX Pane object.
     @return The multiple choice question pane as a Pane object.
     */
    public Pane getPane() {
        return questionHBox;
    }
}
