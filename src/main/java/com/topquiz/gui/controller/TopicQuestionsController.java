package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.SimpleQuestion;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
/**The TopicQuestionsController class handles the logic for displaying and manipulating questions for a specific topic.
        */
public class TopicQuestionsController implements Initializable {
    private final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    private static String selectedTopicName;
    private @FXML TableView<Question> questionsTable;
    private @FXML TableColumn<Question, String> questionColumn;
    private @FXML TableColumn<Question, String> questionTypeColumn;

    /**

     Sets the name of the selected topic.
     @param newSelectedTopicName the new name of the selected topic.
     */
    public static void setSelectedTopicName(String newSelectedTopicName) {
        selectedTopicName = newSelectedTopicName;
    }
    /**

     Handles adding a new question to the selected topic.
     @param event the action event triggered by the user.
     @throws IOException if there is an error loading the appropriate fxml file.
     */
    public void addQuestion(ActionEvent event) throws IOException {
        TextInputDialog questionTypeInputDialog = new TextInputDialog();
        questionTypeInputDialog.setContentText("Choose question type");
        questionTypeInputDialog.setHeaderText("Question types: " + Arrays.toString(SimpleQuestion.QuestionType.values()));
        Optional<String> questionTypeOptional = questionTypeInputDialog.showAndWait();
        if (questionTypeOptional.isEmpty()) {
            return;
        }
        SimpleQuestion.QuestionType selectedQuestionType = SimpleQuestion.QuestionType.getQuestionTypeByStr(questionTypeOptional.get());
        if (selectedQuestionType == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong questionType");
            alert.setContentText("The type of question entered is incorrect!");
            alert.showAndWait();
            return;
        }

        String questionTypeResource = null;
        switch (selectedQuestionType) {
            case TEXT_SIMPLE:
            case TEXT_MULTIPLE_CHOICE: {
                questionTypeResource = "addQuestion.fxml";
                AddQuestionController.setTopicName(selectedTopicName);
                break;
            }

            case SINGLE_IMAGE: {
                questionTypeResource = "AddPictureQuestion.fxml";
                AddPictureQuestionController.setTopicName(selectedTopicName);
                break;
            }

            case MULTIPLE_IMAGES: {
                questionTypeResource = "AddMultiplePictureQuestion.fxml";
                AddMultiplePictureQuestionController.setTopicName(selectedTopicName);
                break;
            }
        }

        if (questionTypeResource != null) {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource(questionTypeResource));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
    /**

     Handles editing a selected question for the selected topic.
     @param event the action event triggered by the user.
     */
    public void editQuestion(ActionEvent event) {

    }
/**

 Handles removing a selected question from the selected topic.*/
    public void removeQuestion(ActionEvent event) {
        ObservableList<Question> observableList = questionsTable.getSelectionModel().getSelectedItems();
        if (observableList.size() > 0) {
            Question selectedQuestion = observableList.get(0);
            quizApplication.removeTopicQuestion(selectedTopicName, selectedQuestion);
            questionsTable.setItems(FXCollections.observableList(quizApplication.getTopicQuestions(selectedTopicName)));
        }
    }

    public void backToSelectTopics(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("selectTopic.fxml"));
        AddQuestionController.setTopicName(selectedTopicName);
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        questionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("questionType"));

        questionsTable.setItems(FXCollections.observableList(quizApplication.getTopicQuestions(selectedTopicName)));
    }
}
