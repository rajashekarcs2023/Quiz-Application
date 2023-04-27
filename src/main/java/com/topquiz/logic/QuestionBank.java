package com.topquiz.logic;

import com.topquiz.logic.model.Question;
import com.topquiz.logic.model.Topic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionBank implements Serializable {
    private Map<Topic, List<Question>> topicListHashMap = new HashMap<>();

    /**adds questions to the question bank. Takes the topic object and question object as input and adds question
     * under the respective topic*/
    public void addQuestion(Topic topic, Question question) {
        if (!topicListHashMap.containsKey(topic)) {
            topicListHashMap.put(topic, new ArrayList<>());
        }
        topicListHashMap.get(topic).add(question);
        topic.addQuestion();
    }
    /**Removes the question under a particular topic.Takes the topic object and the selected question object as input */
     public void deleteQuestion(Topic topic, Question selectedQuestion) {
         if (topicListHashMap.containsKey(topic)) {
             topicListHashMap.get(topic).remove(selectedQuestion);
             topic.removeQuestion();
         }
    }
    /** Under each topic in the questionbank,this function is used to show the list of questions. It takes
     * an object of type Topic as input */
    public List<Question> getTopicQuestions(Topic topic) {
        if (!topicListHashMap.containsKey(topic)) {
            topicListHashMap.put(topic, new ArrayList<>());
        }
        return topicListHashMap.get(topic);
    }
}
