package com.topquiz.logic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**

 The abstract class Question represents a question in a quiz.

 It provides a way to store and retrieve the question, type and a list of questions.
 */
public abstract class Question implements Serializable {
    /**

     The enumeration class QuestionType specifies the type of the question.

     It contains four different types: TEXT_SIMPLE, TEXT_MULTIPLE_CHOICE, SINGLE_IMAGE, MULTIPLE_IMAGES.
     */
    public enum QuestionType {
        TEXT_SIMPLE,
        TEXT_MULTIPLE_CHOICE,
        SINGLE_IMAGE,
        MULTIPLE_IMAGES;
        /**

         Returns the QuestionType object based on the string input.

        * @param questionTypeStr the string representation of the QuestionType object

         * @return the QuestionType object or null if the string representation does not match any of the values.
         */
        public static QuestionType getQuestionTypeByStr(String questionTypeStr) {
            for (QuestionType questionType : values()) {
                if (questionType.name().equalsIgnoreCase(questionTypeStr)) {
                    return questionType;
                }
            }

            return null;
        }
    }

    protected String question;
    private ArrayList<Question> questionList=new ArrayList<Question>();
    protected QuestionType questionType;
    /**

     Constructs a Question object with the specified question and question type.
     @param question the question to be asked
     @param questionType the type of the question
     */
    public Question(String question, QuestionType questionType) {
        this.question = question;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }




    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
