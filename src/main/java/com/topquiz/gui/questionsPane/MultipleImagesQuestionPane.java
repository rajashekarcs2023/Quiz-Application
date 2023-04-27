package com.topquiz.gui.questionsPane;

import com.topquiz.logic.model.MultipleImageQuestion;
import com.topquiz.logic.model.Question;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
/**

 Represents a pane for displaying a multiple images question in a quiz.

 Extends the QuestionPane class.
 */
public class MultipleImagesQuestionPane extends QuestionPane {
    private VBox mainVBox = new VBox();
    private ListView<ImageView> imageListView;
    private MultipleImageQuestion multipleImageQuestion;
    /**

     Constructs a MultipleImagesQuestionPane with the specified question.
     @param question The multiple image question to be displayed in the pane.
     */
    public MultipleImagesQuestionPane(Question question) {
        super(question);
        multipleImageQuestion = (MultipleImageQuestion) question;
        init();
    }
    /**

     Initializes the UI components and adds them to the pane.
     */
    private void init() {
        mainVBox = new VBox();
        //mainVBox.setStyle("-fx-background-color:#dfdede;");
        mainVBox.setAlignment(Pos.CENTER_LEFT);

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setFont(new Font(16));
        textArea.setStyle("-fx-text-inner-color: black;");
        textArea.setEditable(false);
        textArea.setPrefSize(720, 72);
        textArea.setText(question.getQuestion());
        mainVBox.getChildren().add(textArea);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(720, 228);
        imageListView = new ListView<>();
        imageListView.setPrefSize(720, 228);

        List<ImageView> imageViewList = new ArrayList<>();
        for (Image image : multipleImageQuestion.getPossibleAnswerImageList()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            imageViewList.add(imageView);
        }

        imageListView.getItems().addAll(imageViewList);


        scrollPane.setContent(imageListView);
        mainVBox.getChildren().add(scrollPane);
        mainVBox.setVisible(true);
    }
    /**

     Determines if the selected answer is the correct answer for this question.
     @return true if the selected image is the correct answer, false otherwise.
     */
    public boolean isCorrectAnswer() {
        ObservableList<ImageView> viewObservableList = imageListView.getSelectionModel().getSelectedItems();
        if (viewObservableList.size() > 0) {
            Image selectedImage = viewObservableList.get(0).getImage();
            Image correctAnswerImage = multipleImageQuestion.getCorrectAnswerImage();
            boolean result = selectedImage.getHeight() == correctAnswerImage.getHeight() &&
                    selectedImage.getWidth() == correctAnswerImage.getWidth();
            if (result) {
                for (int i = 0; i < correctAnswerImage.getWidth(); i++) {
                    for (int j = 0; j < correctAnswerImage.getHeight(); j++) {
                        if (correctAnswerImage.getPixelReader().getArgb(i, j) != selectedImage.getPixelReader().getArgb(i, j)) return false;
                    }
                }
            }
            return result;
        }
        return false;

    }

    /**

     Returns the main VBox pane of this multiple images question pane.
     @return The main VBox pane of this multiple images question pane.
     */
    public Pane getPane() {
        return mainVBox;
    }
}





