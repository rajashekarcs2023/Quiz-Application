package com.topquiz.logic;

import com.topquiz.logic.model.Score;
import com.topquiz.logic.model.Topic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreTracker implements Serializable {
    private final Map<Topic, List<Score>> scoreList = new HashMap<>();

    public ScoreTracker() {

    }
    /** It assigns the score to each topic.  This method takes a Topic object and a Score object as parameters and assigns the score to the specified topic. It checks if the scoreList map already contains the topic. If the topic is not present, it creates a new list for the topic and adds the score to the list.
     *  If the topic already exists, it retrieves the list from the map and adds the score to the list. */
    public void addScore(Topic topic, Score score) {
        if(!scoreList.containsKey(topic)) {
            scoreList.put(topic, new ArrayList<>());
        }
        scoreList.get(topic).add(score);
    }
    /**This method takes a Topic object as a parameter and returns a list of all the scores for the specified topic. It first checks if the topic exists in the scoreList map. If the topic does not exist, it creates a new empty list for the topic and returns it.
     *  If the topic already exists, it retrieves the list of scores from the map and returns it. */
    public List<Score> getScores(Topic topic) {
        if(!scoreList.containsKey(topic)) {
            scoreList.put(topic, new ArrayList<>());
        }
        return scoreList.get(topic);
    }
/** This method takes a user name as a parameter and returns a map of topics and their respective scores for
 *  the specified user. It iterates over each entry in the scoreList map, and for each topic,
 *  it iterates over the list of scores for that topic. If it finds a score for the specified user, it adds the topic and score to a new map and returns it at the end.*/
    public Map<Topic, Score> getUserScores(String userName) {
        Map<Topic, Score> topicScoreMap = new HashMap<>();
        for (Map.Entry<Topic, List<Score>> topicListEntry : scoreList.entrySet()) {
            for (Score score : topicListEntry.getValue()) {
                if (score.getUserName().equals(userName)) {
                    topicScoreMap.put(topicListEntry.getKey(), score);
                }
            }
        }
        return topicScoreMap;
    }
}
