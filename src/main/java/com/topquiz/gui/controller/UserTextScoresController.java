package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.gui.model.Item;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Score;
import com.topquiz.logic.model.Topic;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
/**

 The UserTextScoresController class is responsible for controlling the user's text-based score view.
 */
public class UserTextScoresController implements Initializable {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String userName;
    private @FXML TableView<Item<Topic, Score>> topicScoreTableView;
    private @FXML TableColumn<Item<Topic, Score>, String> topicNameTableCol;
    private @FXML TableColumn<Item<Topic, Score>, String> userScoreTableCol;
    /**

     Initializes the user's text-based score view with topic names and scores.

     Retrieves the user's scores for each topic and populates the table view with the data.

     @param url The location used to resolve relative paths for the root object

     @param resourceBundle The resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Map<Topic, Score> topicScoreMap = quizApplication.getUserTopicScores(userName);
        List<Item<Topic, Score>> itemList = new ArrayList<>();
        for(var entry : topicScoreMap.entrySet()) {
            itemList.add(new Item<>(entry.getKey(), entry.getValue()));
        }

        topicNameTableCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue1().getName()));
        userScoreTableCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getValue2().getScore())));

        topicScoreTableView.getItems().addAll(FXCollections.observableList(itemList));
    }
    /**

     Switches back to the user menu view.
     @param event The action event triggered by clicking the 'Back' button
     @throws IOException If an error occurs while loading the user menu view
     */
    public void backToUserMenu(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("userMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Sets the username of the user whose scores are being displayed.
     @param userName The username of the user whose scores are being displayed
     */
    public static void setUserName(String userName) {
        UserTextScoresController.userName = userName;
    }
}
