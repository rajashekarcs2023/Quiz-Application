package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Score;
import com.topquiz.logic.model.Topic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
/**

 The UserBarChartScoresController class is responsible for controlling the user bar chart scores view.

 It displays the user's scores for each topic in a bar chart.
 */
public class UserBarChartScoresController implements Initializable {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String userName;
    private @FXML BarChart<String, Integer> userScoresBarChart;

    /**

     Handles the back button click event, and redirects the user to the user menu view.
     @param event the ActionEvent triggered by the back button click.
     @throws IOException if an error occurs while loading the user menu view.
     */
    public void backToUserMenu(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("userMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
    /**

     Initializes the user bar chart scores view, and populates the bar chart with the user's scores for each topic.

     @param url the location used to resolve relative paths for the root object, or null if the location is not known.

     @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userScoresBarChart.getXAxis().setLabel("Topic");
        userScoresBarChart.getYAxis().setLabel("Score");

        Map<Topic, Score> userTopicScoreMap = quizApplication.getUserTopicScores(userName);

        //Configuring Series for XY chart
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (Map.Entry<Topic, Score> entry : userTopicScoreMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey().getName(), entry.getValue().getScore()));
        }

        //Adding series to the barchart
        userScoresBarChart.getData().add(series);
    }

    public static void setUserName(String userName) {
        UserBarChartScoresController.userName = userName;
    }
}
