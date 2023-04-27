package com.topquiz.logic;

import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.SimpleQuestion;
import com.topquiz.logic.model.Score;
import com.topquiz.logic.model.Topic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**this class manages topics, questions, and scores for a quiz application,
 * and provides methods to add, remove, and retrieve information about them. */
public class QuizApplication implements Serializable {
    private static QuizApplication quizApplication = new QuizApplication();
    private final QuestionBank questionBank = new QuestionBank();
    private final ScoreTracker tracker = new ScoreTracker();
    private final List<Topic> topicList = new ArrayList<>();

    private QuizApplication() {

    }

    public static QuizApplication getQuizApplication() {
        return quizApplication;
    }
  /** This method takes a Score object and a Topic object as parameters and adds the score to the
   * specified topic using the addScore() method of the ScoreTracker class.*/
    public void createScore(Score score, Topic topic) {
        tracker.addScore(topic, score);
    }

    /** This method takes a Topic object as a parameter and adds it to the topicList if it doesn't already exist in the list.
     * It returns true if the topic was added successfully, false if it already existed. */
    public boolean addTopic(Topic topic) {
        if (topicList.contains(topic)) {
            return false;
        }
        topicList.add(topic);
        return true;
    }
    /**This method returns the list of topics in the topicList. */
    public List<Topic> getTopicList() {
        return topicList;
    }
    /** This method takes a topic name as a parameter and returns a list of questions for the specified topic using the getTopicQuestions() method of the QuestionBank class.
     *  If the topic does not exist in the topicList, it returns null.*/
    public List<Question> getTopicQuestions(String topicName) {
        Topic topic = getTopicByName(topicName);
        if (topic != null) {
            return questionBank.getTopicQuestions(topic);
        }

        return null;
    }
    /**This method takes a topic name and a Question object as parameters and adds the question to the specified topic using the addQuestion() method of the QuestionBank class.
     * If the topic does not exist in the topicList, it returns false, otherwise, it returns true.*/
    public boolean addTopicQuestion(String topicName, Question question) {
        Topic topic = getTopicByName(topicName);
        if (topic == null) {
            return false;
        }

        questionBank.addQuestion(topic, question);
        return true;
    }
    /**This method takes a user name as a parameter and returns a map of topics and their respective scores for the specified user using the getUserScores()
     *  method of the ScoreTracker class. */
    public Map<Topic, Score> getUserTopicScores(String userName) {
        return tracker.getUserScores(userName);
    }

    /**This method takes a topic name as a parameter and returns the Topic object with the specified name from the topicList.
     *  If the topic does not exist in the topicList, it returns null. */
    public Topic getTopicByName(String topicName) {
        for (Topic topic : topicList) {
            if (topic.getName().equals(topicName)) {
                return topic;
            }
        }
        return null;
    }
	/** This method takes a topic name and a Question object as parameters and removes the question from the specified topic using the deleteQuestion() method of the QuestionBank class.
     *  If the topic does not exist in the topicList, it returns false, otherwise, it returns true.*/
	public boolean removeTopicQuestion(String topicName, Question question) {
        Topic topic = getTopicByName(topicName);
        if (topic == null) {
            return false;
        }

        questionBank.deleteQuestion(topic, question);
        return true;
    }
    /**  This method takes a topic name as a parameter and returns a list of scores for the specified topic
     *  using the getScores() method of the ScoreTracker class.*/
    public List<Score> getTopicScores(String topicName) {
        return tracker.getScores(new Topic(topicName));
    }
    /**This method takes a file path as a parameter and saves the QuizApplication object to
     *  the specified file using the save() method of the FileReader class.*/
    public static void save(String filePath) {
        FileReader.save(quizApplication, filePath);
    }
    /**This method takes a file path as a parameter and loads the QuizApplication object
     * from the specified file using the load() method of the FileReader class. */
    public static void load(String filePath) {
        quizApplication = FileReader.load(filePath);
    }
}
