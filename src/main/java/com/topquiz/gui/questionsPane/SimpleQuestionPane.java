package com.topquiz.gui.questionsPane;

import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.SimpleQuestion;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
/**

 A class representing a pane for a simple question with a text field for the answer input.

 Extends the QuestionPane class.
 */
public class SimpleQuestionPane extends QuestionPane {
    private HBox questionHBox = new HBox();
    private TextField answerTextField;
    /**

     Constructs a SimpleQuestionPane object with the given Question object.
     Calls the init() method to initialize the question pane.
     @param question the Question object for the question pane.
     */
    public SimpleQuestionPane(Question question) {
        super(question);
        init();
    }
    /**

     Initializes the simple question pane with a text area to display the question and a text field for the answer input.

     The question and answer input elements are added to the questionHBox object.
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

        answerTextField = new TextField();
        questionHBox.getChildren().add(answerTextField);
        questionHBox.setVisible(true);
    }
    /**

     Returns true if the user input in the text field is equal to the correct answer for the simple question.
     The method compares the user input with the correct answer in a case-insensitive manner.
     @return true if the user input is equal to the correct answer, false otherwise.
     */
    public boolean isCorrectAnswer() {
        SimpleQuestion simpleQuestion = (SimpleQuestion) question;
        return answerTextField.getText().equalsIgnoreCase(simpleQuestion.getCorrectAnswer());
    }


    /**

     Returns the HBox object representing the simple question pane.
     @return the HBox object representing the simple question pane.
     */
    public Pane getPane() {
        return questionHBox;
    }
}
