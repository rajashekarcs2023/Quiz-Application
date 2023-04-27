package com.topquiz.logic.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import java.util.List;
import javafx.scene.image.WritableImage;

/**

 Represents a simple text question that has a single correct answer.

 This class extends the abstract class 'Question' and implements its abstract methods.
 */
public class SimpleQuestion extends Question {
    private List<String> answers;
    private String correctAnswer;
    private transient Image chosenImage;
    /**

     Creates a new instance of the class with the specified question, list of answers, and correct answer.
     Sets the question type to 'TEXT_SIMPLE'.
     @param question the text of the question
     @param answers a list of possible answers for the question
     @param correctAnswer the correct answer for the question
     */
    public SimpleQuestion(String question, List<String> answers, String correctAnswer) {
        super(question, QuestionType.TEXT_SIMPLE);
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    /**

     Creates a new instance of the class with the specified question, list of answers, correct answer, and question type.
     @param question the text of the question
     @param answers a list of possible answers for the question
     @param correctAnswer the correct answer for the question
     @param questionType the type of the question
     */
    public SimpleQuestion(String question, List<String> answers, String correctAnswer, QuestionType questionType) {
        super(question, questionType);
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    /**

     Returns the text of the question.
     @return the text of the question
     */
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    /**

     Returns a list of possible answers for the question.
     @return a list of possible answers for the question
     */
    public List<String> getAnswers() {
        return answers;
    }
    /**

     Sets the list of possible answers for the question.
     @param answers a list of possible answers for the question
     */
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
    /**

     Sets the type of the question.
     @param questionType the type of the question
     */
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
    /**
     Sets an image chosen for the question.
     @param chosenImage an image chosen for the question
     */
    public void setChosenImage(Image chosenImage) {
        this.chosenImage = chosenImage;
    }
    /**
     Returns the image chosen for the question.
     @return the image chosen for the question
     */
    public Image getChosenImage() {
        return chosenImage;
    }
/**

 Writes the object to the output stream.
 Overrides the default implementation to add the 'chosenImage' field to the serialization process.
* @param out the output stream to write to
 * @throws IOException if an I/O error occurs while writing
 */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeBoolean(chosenImage != null);
        if (chosenImage != null) {
            int w = (int) chosenImage.getWidth();
            int h = (int) chosenImage.getHeight();

            byte[] b = new byte[w * h * 4];
            chosenImage.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);

            out.writeInt(w);
            out.writeInt(h);
            out.write(b);
        }
    }
    /**

     Reads the state of the object from the input stream.

     Overrides the defaultReadObject method to read additional information for chosenImage

     @param in the input stream to read the object from

     @throws IOException if there's an error while reading from the stream

     @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        if (in.readBoolean()) {
            int w = in.readInt();
            int h = in.readInt();

            byte[] b = new byte[w * h * 4];
            in.readFully(b);

            WritableImage wImage = new WritableImage(w, h);
            wImage.getPixelWriter().setPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);

            chosenImage = wImage;
        }
    }
}
