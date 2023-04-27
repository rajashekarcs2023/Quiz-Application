package com.topquiz.gui.questionsPane;

import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.SimpleQuestion;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class SingleImageQuestionPane extends QuestionPane {

    private HBox questionHBox = new HBox();
    private ListView<String> questionListView;

    public SingleImageQuestionPane(Question question) {
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

        VBox questionWithImageBox = new VBox();
        questionWithImageBox.setAlignment(Pos.CENTER_LEFT);
        questionWithImageBox.setPrefSize(280, 300);

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setFont(new Font(16));
        textArea.setEditable(false);
        textArea.setPrefSize(200, 100);
        textArea.setText(question.getQuestion());
        questionHBox.getChildren().add(textArea);

        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(((SimpleQuestion) question).getChosenImage());
        //Setting the image view parameters
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        questionWithImageBox.getChildren().add(imageView);

        questionHBox.getChildren().add(questionWithImageBox);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(280, 300);
        questionListView = new ListView<>();
        questionListView.setPrefSize(280, 300);
        List<String> answerList = ((SimpleQuestion) question).getAnswers();
        if (answerList.size() > 0) {
            questionListView.setItems(FXCollections.observableList(answerList));
        }
        scrollPane.setContent(questionListView);
        questionHBox.getChildren().add(scrollPane);
        questionHBox.setVisible(true);
    }
    /**

     Returns true if the user input in the text field is equal to the correct answer for the simple question.
     The method compares the user input with the correct answer in a case-insensitive manner.
     @return true if the user input is equal to the correct answer, false otherwise.
     */
    public boolean isCorrectAnswer() {
        String selectedAnswer = questionListView.getSelectionModel().getSelectedItems().get(0);
        return selectedAnswer.equalsIgnoreCase(((SimpleQuestion) question).getCorrectAnswer());
    }

    public Pane getPane() {
        return questionHBox;
    }
}
