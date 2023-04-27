package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.SimpleQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
/**

 This class is responsible for controlling the "Add Question" view in the TopQuiz application.

 It allows the user to create and add a new question to a specific topic.
 */

public class AddQuestionController {
    private static final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String topicName;
    private @FXML TextArea questionTextArea;
    private @FXML TextArea answersTextArea;
    private @FXML TextArea correctAnswerTextArea;

    public static void setTopicName(String topicName) {
        AddQuestionController.topicName = topicName;
    }
/**

 Creates a new question and adds it to the selected topic.
 @param event the event triggered by clicking the "Add Question" button
 @throws IOException if there is an error loading the "Topic Questions" view
 */
    public void createQuestion(ActionEvent event) throws IOException {
        String questionStr = questionTextArea.getText().strip();
        List<String> answers = List.of(answersTextArea.getText().strip().split("\\s*,\\s*"));
        String correctAnswer = correctAnswerTextArea.getText().strip();
        SimpleQuestion.QuestionType questionType;
        if (answers.size() > 1) {
            questionType = SimpleQuestion.QuestionType.TEXT_MULTIPLE_CHOICE;
        } else {
            questionType = SimpleQuestion.QuestionType.TEXT_SIMPLE;
        }
        SimpleQuestion question = new SimpleQuestion(questionStr, answers, correctAnswer, questionType);
        if (quizApplication.addTopicQuestion(topicName, question)) {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("topicQuestions.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
}
