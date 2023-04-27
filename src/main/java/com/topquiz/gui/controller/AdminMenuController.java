package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**

 The AdminMenuController class is responsible for controlling the behavior of the admin menu view.

 This view displays options for maintaining the question bank and returning to the login page.
 */
public class AdminMenuController {
    private Stage mainStage;

    /**

     Changes the current scene to the specified FXML file.
     @param event the event that triggered the scene change
     @param fxmlFileName the name of the FXML file to load
     @throws IOException if there is an error loading the FXML file
     */
    private void changeToScene(ActionEvent event, String fxmlFileName) throws IOException {
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Changes the current scene to the question bank view.
     @param event the event that triggered the scene change
     @throws IOException if there is an error loading the question bank FXML file
     */
    public void maintainQuestionBank(ActionEvent event) throws IOException {
        changeToScene(event, "questionBank.fxml");
    }
    /**

     Changes the current scene back to the login view.
     @param event the event that triggered the scene change
     @throws IOException if there is an error loading the login FXML file
     */
    public void backToLogin(ActionEvent event) throws IOException {
        changeToScene(event, "login.fxml");
    }
}
