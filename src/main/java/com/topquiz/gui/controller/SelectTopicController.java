package com.topquiz.gui.controller;

import com.topquiz.gui.TopQuizApplication;
import com.topquiz.logic.QuizApplication;
import com.topquiz.logic.model.Topic;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import java.util.ResourceBundle;
/**

 Controller for the "select topic" view, which allows users to choose a topic to view questions for.
 */
public class SelectTopicController implements Initializable {
    private final QuizApplication quizApplication = QuizApplication.getQuizApplication();
    @FXML
    protected TableView<String> topicsTable;
    @FXML
    protected TableColumn<String, String> nameColumn;
    /**

     Event handler for the "view questions" button. Opens the "topic questions" view and passes the name of the selected
     topic to the controller for that view.
     @param event the event triggered by the user clicking the button
     @throws IOException if there is an error loading the "topic questions" view
     */
    public void viewQuestions(ActionEvent event) throws IOException {
        String selectedTopicName = topicsTable.getSelectionModel().getSelectedItems().get(0);
        if (selectedTopicName != null) {
            // pass selectedTopicName to the TopicQuestionsController and open appropriate fxml
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("topicQuestions.fxml"));
            TopicQuestionsController.setSelectedTopicName(selectedTopicName);
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
    /**

     Event handler for the "back" button. Returns the user to the "question bank" view.
     @param event the event triggered by the user clicking the button
     @throws IOException if there is an error loading the "question bank" view
     */
    public void backToQuestionBank(ActionEvent event) throws IOException {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("questionBank.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }
/**

 Initializes the controller by setting up the topic list table.
 @param url the location used to resolve relative paths for the root object
 @param resourceBundle the resource bundle to be used by this controller*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        topicsTable.setItems(FXCollections.observableArrayList(parseTopicList()));
    }
    /**

     Parses the list of topics from the QuizApplication and returns a list of topic names.
     @return the list of topic names
     */
    private List<String> parseTopicList(){
        List<String> result = new ArrayList<>();
        for(Topic topic : quizApplication.getTopicList()) {
            result.add(topic.getName());
        }
        return result;
    }
}
