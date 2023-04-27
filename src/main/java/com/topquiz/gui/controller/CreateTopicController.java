package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Topic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
/**

 The CreateTopicController class is responsible for controlling the functionality of the UI for creating a new topic.
 */
public class CreateTopicController {
    private final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    @FXML
    protected TextField topicNameTextField;
    /**

     Creates a new topic with the name provided in the topicNameTextField and adds it to the quiz application.
     If the topic name is blank, the method simply returns without doing anything.
     If the topic is successfully added, the method changes the scene to the question bank view.
     If the topic already exists, the method does nothing.
     @param event The ActionEvent that triggered the method call.
     @throws IOException If there is an error loading the question bank view.
     */
    public void createTopic(ActionEvent event) throws IOException {
        String topicName = topicNameTextField.getText();
        if (topicName.isBlank()) {

            return;
        }
        if (quizApplication.addTopic(new Topic(topicName))) {
            System.out.println("it was successfully added");
            // it was successfully added
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("questionBank.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } else {
            // topic already exists
        }
    }
}
