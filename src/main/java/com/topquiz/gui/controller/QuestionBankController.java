package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**

 The LoginController class is responsible for controlling the login screen of the TopQuiz application.

 It contains methods to change the scene upon user login as an admin or user.
 */
public class QuestionBankController {
    private Stage mainStage;
    private void changeToScene(ActionEvent event, String fxmlFileName) throws IOException {
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void createTopic (ActionEvent event) throws IOException {
        changeToScene(event, "createTopic.fxml");
    }

    public void chooseTopic (ActionEvent event) throws IOException {
        changeToScene(event, "selectTopic.fxml");
    }

    public void backToAdminMenu(ActionEvent event) throws IOException {
        changeToScene(event, "adminMenu.fxml");
    }
}
