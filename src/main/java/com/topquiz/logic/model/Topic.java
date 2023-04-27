package com.topquiz.logic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**

 A class representing a topic in a quiz.

 Each topic has a name and a number of questions associated with it.
 */
public class Topic implements Serializable {
    private String name;
    private int qtyOfQuestions = 0;

    public Topic(String name) {
        this.name = name;
    }
    /**

     Increases the number of questions associated with the topic by 1.
     */
    public void addQuestion() {
        qtyOfQuestions++;
    }
    /**

     Decreases the number of questions associated with the topic by 1.
     */
    public void removeQuestion() {
        qtyOfQuestions--;
    }
    /**

     Returns the name of the topic.
     @return the name of the topic.
     */
    public String getName() {
        return name;
    }
    /**

     Sets the name of the topic.
     @param name the new name of the topic.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Returns the number of questions associated with the topic.
     @return the number of questions associated with the topic.
     */
    public int getQtyOfQuestions() {
        return qtyOfQuestions;
    }
    /**

     Compares this Topic object to another object for equality.
     @param o the object to compare to.
     @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return name.equals(topic.name);
    }
    /**

     Returns the hash code of this Topic object.
     @return the hash code of this Topic object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
