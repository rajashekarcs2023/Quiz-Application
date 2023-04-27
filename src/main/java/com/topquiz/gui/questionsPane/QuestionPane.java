package com.topquiz.gui.questionsPane;

import com.topquiz.logic.model.Question;
import javafx.scene.layout.Pane;

public abstract class QuestionPane {
    protected final Question question;

    public QuestionPane(Question question) {
        this.question = question;
    }

    public abstract boolean isCorrectAnswer();

    public abstract Pane getPane();
}
