package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Topic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**

 The UserMenuController class is responsible for controlling the user menu scene in the TopQuiz application.

 It initializes the UI components and handles user interaction events, such as starting a quiz, viewing scores, and returning to the login scene.
 */
public class UserMenuController implements Initializable {
    private final static QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private @FXML TableView<Topic> topicTableView;
    private @FXML TableColumn<Topic, String> nameTableCol;
    private @FXML TableColumn<Topic, String> qtyOfQuestionsTableCol;
    /**

     Starts a quiz with the selected topic and the entered username.
     @param event the ActionEvent triggered by the user clicking the "Start" button.
     @throws IOException if an I/O error occurs when loading the quiz question scene.
     */
    public void startTopic(ActionEvent event) throws IOException {
        ObservableList<Topic> selectedTopicsList = topicTableView.getSelectionModel().getSelectedItems();
        if (selectedTopicsList.size() > 0) {
            String selectedTopicName = selectedTopicsList.get(0).getName();
            TextInputDialog usernameInputDialog = new TextInputDialog();
            usernameInputDialog.setContentText("Enter username");
            usernameInputDialog.setHeaderText("");
            String userName = null;
            while (userName == null) {
                usernameInputDialog.setTitle("Enter username");
                Optional<String> userNameOptional = usernameInputDialog.showAndWait();
                if (userNameOptional.isEmpty()) {
                    return;
                }
                if (userNameOptional.get().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong username");
                    alert.setContentText("Please enter a username, it must not be an empty string!");
                    alert.showAndWait();
                } else {
                    userName = userNameOptional.get();
                }
            }

            // start the quiz
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("quizQuestion.fxml"));
            QuizQuestionController.setTopicName(selectedTopicName);
            QuizQuestionController.setUserName(userName);
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
    /**

     Displays the scores for the entered username in the selected score type.
     @param event the ActionEvent triggered by the user clicking the "View Scores" button.
     @throws IOException if an I/O error occurs when loading the score scene.
     */
    public void viewScores(ActionEvent event) throws IOException {
        TextInputDialog usernameInputDialog = new TextInputDialog();
        usernameInputDialog.setContentText("Enter username");
        usernameInputDialog.setHeaderText("");
        String userName = null;
            usernameInputDialog.setTitle("Enter username");
            Optional<String> userNameOptional = usernameInputDialog.showAndWait();
            if (userNameOptional.isEmpty()) {
                return;
            }
            if (userNameOptional.get().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong username");
                alert.setContentText("Please enter a valid username, it must not be an empty string!");
                alert.showAndWait();
                return;
            } else {
                userName = userNameOptional.get();
            }

        TextInputDialog scoresTypeInputDialog = new TextInputDialog();
        scoresTypeInputDialog.setContentText("Enter score type[text, bar graph, pie chart]:");
        scoresTypeInputDialog.setHeaderText("");
        scoresTypeInputDialog.setTitle("Score type");
        Optional<String> scoreTypeOptional = scoresTypeInputDialog.showAndWait();
        if (scoreTypeOptional.isEmpty()) {
            return;
        }
        String scoreType = scoreTypeOptional.get().toLowerCase();
        String resource;
        switch (scoreType) {
            case "text": {
                resource = "userTextScores.fxml";
                UserTextScoresController.setUserName(userName);
                break;
            }

            case "bar graph": {
                resource = "userBarChartScores.fxml";
                UserBarChartScoresController.setUserName(userName);
                break;
            }

            case "pie chart": {
                resource = "userPieChartScores.fxml";
                UserPieChartScoresController.setUserName(userName);
                break;
            }

            default: {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong score type");
                alert.setContentText("Please enter a valid score type, it must not be an empty string!");
                alert.showAndWait();
                return;
            }
        }

        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Returns the user to the login scene.
     @param event the ActionEvent triggered by the user clicking the "Back" button.
     @throws IOException if an I/O error occurs when loading the login scene.
     */
    public void backToLogin(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Initializes the table columns and populates the topic table with available quiz topics.
     @param url the location used to resolve relative paths for the root object.
     @param resourceBundle the resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTableCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyOfQuestionsTableCol.setCellValueFactory(new PropertyValueFactory<>("qtyOfQuestions"));

        topicTableView.setItems(FXCollections.observableList(quizApplication.getTopicList()));
    }
    /**

     Updates the topic table with the latest list of available quiz topics.
     */
    public void updateTopics() {
        topicTableView.setItems(FXCollections.observableList(quizApplication.getTopicList()));
    }
}
