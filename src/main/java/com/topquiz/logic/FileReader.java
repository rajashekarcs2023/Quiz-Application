package com.topquiz.logic;

import java.io.*;

/**
 A utility class for reading and writing QuizApplication objects to and from files.
 */

/**

 Writes a QuizApplication object to a file at the specified file path.
 Parameter :  quizApplication the QuizApplication object to be saved
 Parameter: saveFilePath the file path where the QuizApplication object should be saved
 */
public class FileReader {
    public static void save(QuizApplication quizApplication, String saveFilePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            oos.writeObject(quizApplication);
        } catch (IOException e) {
            System.err.println("Failed to save in file: " + saveFilePath);
        }
    }

    /**

     Reads a QuizApplication object from a file at the specified file path.
     parameter : loadFilePath the file path where the QuizApplication object is saved
     @return the QuizApplication object loaded from the file
     */
    public static QuizApplication load(String loadFilePath) {
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(loadFilePath))) {
            return (QuizApplication) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to open file: " + loadFilePath);
        }
        return QuizApplication.getQuizApplication();
    }
}
