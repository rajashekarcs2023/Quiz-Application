package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.MultipleImageQuestion;
import com.topquiz.logic.model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**

 Controller class for adding a multiple images question to a topic in the QuizApplication.
 */
public class AddMultiplePictureQuestionController {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String topicName;
    private @FXML TextArea questionTextArea;
    private @FXML TextField imageNameTextField;
    private @FXML ListView<String> possibleAnswerImageNameList;
    private final List<Image> possibleAnswerImageList = new ArrayList<>();
    private Image chosenImage;
    /**

     Opens a file chooser dialog for the user to choose an image file, and sets the chosen image to the field.
     @param event the action event triggered by the user clicking the "Choose Image" button
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

     Opens a file chooser dialog for the user to choose an image file to add as a possible answer, and adds it to the list of possible answer images.
     @param event the action event triggered by the user clicking the "Add Possible Answer Image" button
     */
    public void addPossibleAnswerImage(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            possibleAnswerImageList.add(new Image(file.toURI().toString()));
            possibleAnswerImageNameList.getItems().add(file.getName());
        }
    }
    /**

     Creates a new MultipleImageQuestion object with the entered question prompt, list of possible answer images, and the chosen image as the correct answer.
     Adds the question to the selected topic in the QuizApplication and navigates back to the topic questions screen.
     @param event the action event triggered by the user clicking the "Create Question" button
     @throws IOException if there is an error navigating to the topic questions screen
     */
    public void createQuestion(ActionEvent event) throws IOException {
        String questionStr = questionTextArea.getText().strip();


        Question question = new MultipleImageQuestion(questionStr, Question.QuestionType.MULTIPLE_IMAGES, possibleAnswerImageList, chosenImage);
        if (quizApplication.addTopicQuestion(topicName, question)) {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("topicQuestions.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }

    }
    /**

     Sets the name of the topic that the question is being added to.
     @param topicName the name of the selected topic
     */
    public static void setTopicName(String topicName) {
        AddMultiplePictureQuestionController.topicName = topicName;
    }
}
