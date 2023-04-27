package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Score;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**

 The TopicScoresController class is responsible for controlling the topic scores view,

 which displays a table of all the users and their scores for a specific topic.
 */
public class TopicScoresController implements Initializable {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String topicName;
    private @FXML Label topicNameLabel;
    private @FXML TableView<Score> topicScoreTableView;
    private @FXML TableColumn<Score, String> usernameTableCol;
    private @FXML TableColumn<Score, String> scoreTableCol;
    /**

     Navigates back to the select topic view.
     @param event The event that triggered the navigation.
     @throws IOException if an I/O error occurs.
     */
    public void backToSelectTopic(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("selectTopic.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**

     Initializes the topic scores view by setting up the table and displaying the topic name.

     Retrieves the scores for the topic and displays them in the table.

     @param url The location used to resolve relative paths for the root object.

     @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topicNameLabel.setText(topicName);

        usernameTableCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        scoreTableCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        topicScoreTableView.setItems(FXCollections.observableList(quizApplication.getTopicScores(topicName)));
    }
/**

 Sets the name of the topic whose scores will be displayed.
 @param topicName The name of the topic.*/
    public static void setTopicName(String topicName) {
        TopicScoresController.topicName = topicName;
    }
}
