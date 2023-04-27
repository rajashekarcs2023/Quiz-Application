package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Score;
import com.topquiz.logic.model.Topic;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
/**

 This class controls the user's pie chart scores, which displays the user's scores

 for each topic in a pie chart. It implements the Initializable interface.
 */
public class UserPieChartScoresController implements Initializable {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String userName;
    private @FXML PieChart scoresPieChart;
    /**

     Initializes the pie chart with the user's scores for each topic.

     @param url The location used to resolve relative paths for the root object, or null if the

     location is not known.

     @param resourceBundle The resources used to localize the root object, or null if the root

     object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Map<Topic, Score> topicScoreMap = quizApplication.getUserTopicScores(userName);
        List<PieChart.Data> dataList = new ArrayList<>();
        for(var entry : topicScoreMap.entrySet()) {
            dataList.add(new PieChart.Data(entry.getKey().getName(), entry.getValue().getScore()));
        }
        scoresPieChart.setData(FXCollections.observableList(dataList));
    }
    /**

     Changes the scene to the user menu view.

     @param event The ActionEvent that triggered this method.

     @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public void backToUserMenu(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("userMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Sets the user's name.
     @param userName The user's name.
     */
    public static void setUserName(String userName) {
        UserPieChartScoresController.userName = userName;
    }
}
