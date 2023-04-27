package com.topquiz.gui;

import com.topquiz.logic.QuizApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TopQuizApplication extends Application {
    private final static String SAVE_FILE_PATH = "db.txt";
    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(TopQuizApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 440);
        stage.setTitle("TopQuiz");
        stage.setScene(scene);
        stage.show();
        QuizApplication.load(SAVE_FILE_PATH);
        stage.setOnCloseRequest( event -> {
            QuizApplication.save(SAVE_FILE_PATH);
        } );
    }



    public static void main(String[] args) {
        launch();
    }
}
