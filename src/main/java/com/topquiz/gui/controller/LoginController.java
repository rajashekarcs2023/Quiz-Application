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
public class LoginController {
    private Stage mainStage;
    /**

     Changes the scene to the specified FXML file.
     @param event the ActionEvent that triggered the scene change
     @param fxmlFileName the name of the FXML file to change to
     @throws IOException if there is an error loading the FXML file
     */
    public void changeToScene(ActionEvent event, String fxmlFileName) throws IOException {
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Changes the scene to the admin menu upon login as an admin.
     @param event the ActionEvent that triggered the login attempt
     @throws IOException if there is an error loading the admin menu FXML file
     */
    public void loginAsAdmin(ActionEvent event) throws IOException {
        changeToScene(event, "adminMenu.fxml");
    }
    /**

     Changes the scene to the user menu upon login as a user and updates the list of available topics.
     @param event the ActionEvent that triggered the login attempt
     @throws IOException if there is an error loading the user menu FXML file
     */
    public void loginAsUser(ActionEvent event) throws IOException {
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("userMenu.fxml"));
        UserMenuController userMenuController = fxmlLoader.getController();
        if (userMenuController != null) {
            userMenuController.updateTopics();
        }
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
}
