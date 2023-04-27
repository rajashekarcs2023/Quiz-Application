package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.SimpleQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**The controller class for the Add Picture Question view.

        Allows users to create a new SimpleQuestion with a single image.
        */
public class AddPictureQuestionController {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String topicName;
    private @FXML TextArea questionTextArea;
    private @FXML TextArea answersTextArea;
    private @FXML TextArea correctAnswerTextArea;
    private @FXML TextField imageNameTextField;
    private Image chosenImage;
    /**

     Event handler for the "Choose Image" button.
     Opens a file chooser dialog for the user to select an image file.
     Sets the chosen image to the selected file, and displays the filename in the text field.
     @param event The event that triggered this action
     */
    public void chooseImage(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            chosenImage = new Image(file.toURI().toString());
            imageNameTextField.setText(file.getName());
        }
    }
/**

 Event handler for the "Create Question" button.

 Creates a new SimpleQuestion object with the inputted question text, answers, and correct answer.

 Sets the chosen image to the selected image file.

 Adds the question to the selected topic in the QuizApplication, and switches the scene to the Topic Questions view.

 @param event The event that triggered this action

 @throws IOException If an I/O error occurs while loading the Topic Questions view
 */
    public void createQuestion(ActionEvent event) throws IOException {
        String questionStr = questionTextArea.getText().strip();
        List<String> answers = List.of(answersTextArea.getText().strip().split("\\s*,\\s*"));
        String correctAnswer = correctAnswerTextArea.getText().strip();

        SimpleQuestion question = new SimpleQuestion(questionStr, answers, correctAnswer, SimpleQuestion.QuestionType.SINGLE_IMAGE);
        question.setChosenImage(chosenImage);
        if (quizApplication.addTopicQuestion(topicName, question)) {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("topicQuestions.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
    /**

     Sets the topic name for the controller.
     @param topicName The name of the selected topic
     */
    public static void setTopicName(String topicName) {
        AddPictureQuestionController.topicName = topicName;
    }
}
