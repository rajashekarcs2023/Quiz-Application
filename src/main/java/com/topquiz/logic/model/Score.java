package com.topquiz.logic.model;

import java.io.Serializable;

/**

 The Score class represents a player's score in a game.

 It contains the player's score and their username.
 */
public class Score implements Serializable {
    private int score;
    private String userName;
    /**

     Constructs a new Score object with the specified score and username.
     @param score the score of the player
     @param userName the username of the player
     */
    public Score(int score, String userName) {
        this.score = score;
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
