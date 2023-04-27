package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.gui.questionsPane.*;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.Score;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
/**

 The QuizQuestionController class is responsible for controlling the quiz questions displayed to the user.

 It initializes the timer, iterates through the list of questions for a given topic, and updates the UI with

 the current question. It also keeps track of the user's score, and saves it once the quiz is finished.
 */
public class QuizQuestionController implements Initializable {
    private static final int MAX_TIMER_MINUTES = 20;
    private static final QuizApplication quizApp = QuizApplication.getQuizApplication();
    private @FXML BorderPane mainBorderPane;
    private @FXML Label topicNameLabel;
    private @FXML Label timerLabel;
    private Iterator<Question> questionListIterator;
    private static String topicName;
    private static String userName;
    private QuestionPane currentQuestionPane;
    private int score = 0;

    private int timeSeconds = MAX_TIMER_MINUTES * 60;
    private Timeline timeline;

    /**

     This function is called when the user clicks the "Next" button to move to the next question.
     It checks if the current question's answer is correct and updates the score if it is.
     If there are no more questions left, it displays the final score and saves it to the user's score history for the topic.
     It then navigates back to the user menu.
     @param event the action event representing the user clicking the "Next" button
     @throws IOException if an I/O error occurs while loading the user menu FXML file
     */
    public void nextQuestion(ActionEvent event) throws IOException {
        if (currentQuestionPane != null) {
            if (currentQuestionPane.isCorrectAnswer()) {
                score++;
            }
        }
        if (!addNextQuestionPane()) {
            // save score, quit
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Topic result");
            alert.setHeaderText("Final score: " + score);
            alert.showAndWait();
            quizApp.createScore(new Score(score, userName), quizApp.getTopicByName(topicName));
            score = 0;
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("userMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
    /**

     Initializes the controller after its root element has been completely processed.
     It sets up the list of questions for the given topic, shuffles it, and sets the first question.
     It also initializes the timer.
     @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       List<Question> questions1 = new ArrayList<>(quizApp.getTopicQuestions(topicName));
        Collections.shuffle(questions1);
        questionListIterator = questions1.subList(0, Math.min(20, questions1.size())).iterator();
       // questionListIterator = quizApp.getTopicQuestions(topicName).iterator();
        topicNameLabel.setText(topicName);
        addNextQuestionPane();
        initTimer();
    }
    /**

     Initializes the timer by creating a Timeline object, setting the cycle count to INDEFINITE, and
     adding a KeyFrame with a Duration of 1 second. The KeyFrame event handler updates the timerLabel
     with the remaining time, and stops the timer when time runs out.
     */
    private void initTimer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        // KeyFrame event handler
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds--;
                                // update timerLabel
                                int minutes = (timeSeconds % 3600) / 60;
                                int seconds = timeSeconds % 60;
                                timerLabel.setText(
                                        String.format("%d:%02d", minutes, seconds));
                                if (timeSeconds <= 0) {
                                    timeline.stop();
                                    endQuit();
                                }
                            }
                        }));
        timeline.playFromStart();
    }
    /**

     Adds the next question pane to the UI. It gets the next question from the questionListIterator,

     creates the corresponding QuestionPane, and sets it as the center of the mainBorderPane.

     @return True if there are more questions to display, false otherwise.
     */
    private boolean addNextQuestionPane() {
        if (!questionListIterator.hasNext()) {
            return false;
        }
        Question question = questionListIterator.next();
        switch (question.getQuestionType()) {
            case TEXT_SIMPLE: {
                currentQuestionPane = new SimpleQuestionPane(question);
                break;
            }

            case TEXT_MULTIPLE_CHOICE: {
                currentQuestionPane = new MultipleChoiceQuestionPane(question);
                break;
            }

            case SINGLE_IMAGE: {
                currentQuestionPane = new SingleImageQuestionPane(question);
                break;
            }

            case MULTIPLE_IMAGES: {
                currentQuestionPane = new MultipleImagesQuestionPane(question);
                break;
            }
        }
        mainBorderPane.setCenter(currentQuestionPane.getPane());
        return true;
    }

    private void endQuit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Topic result");
        alert.setHeaderText("Final score: " + score);
        alert.show();
        quizApp.createScore(new Score(score, userName), quizApp.getTopicByName(topicName));
        score = 0;
        Stage mainStage = (Stage) mainBorderPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("userMenu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void setTopicName(String topicName) {
        QuizQuestionController.topicName = topicName;
    }

    public static void setUserName(String userName) {
        QuizQuestionController.userName = userName;
    }

}
