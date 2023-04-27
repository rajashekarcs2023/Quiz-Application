package com.topquiz.logic.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * A question that has multiple possible answer images and one correct answer image.
 */
public class MultipleImageQuestion extends Question {
    private transient List<Image> possibleAnswerImageList;
    private transient Image correctAnswerImage;

    /**
     * Constructs a new MultipleImageQuestion with the given question, question type, possible answer images, and correct answer image.
     * @param question the question text
     * @param questionType the question type
     * @param possibleAnswerImageList the list of possible answer images
     * @param correctAnswerImage the correct answer image
     */
    public MultipleImageQuestion(String question, QuestionType questionType, List<Image> possibleAnswerImageList, Image correctAnswerImage) {
        super(question, questionType);
        this.possibleAnswerImageList = possibleAnswerImageList;
        this.correctAnswerImage = correctAnswerImage;
    }

    /**
     * Returns the correct answer image.
     * @return the correct answer image
     */
    public Image getCorrectAnswerImage() {
        return correctAnswerImage;
    }

    /**
     * Returns the list of possible answer images.
     * @return the list of possible answer images
     */
    public List<Image> getPossibleAnswerImageList() {
        return possibleAnswerImageList;
    }

    /**
     * Serializes the object by writing its fields to the output stream.
     * @param out the output stream to write to
     * @throws IOException if an I/O error occurs
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        int w = (int) correctAnswerImage.getWidth();
        int h = (int) correctAnswerImage.getHeight();

        byte[] b = new byte[w * h * 4];
        correctAnswerImage.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);

        out.writeInt(w);
        out.writeInt(h);
        out.write(b);

        out.writeInt(possibleAnswerImageList.size());
        for(int i = 0; i < possibleAnswerImageList.size(); i++) {
            Image imageToSave = possibleAnswerImageList.get(i);
            int w2 = (int) imageToSave.getWidth();
            int h2 = (int) imageToSave.getHeight();

            byte[] b2 = new byte[w2 * h2 * 4];
            imageToSave.getPixelReader().getPixels(0, 0, w2, h2, PixelFormat.getByteBgraInstance(), b2, 0, w2 * 4);

            out.writeInt(w2);
            out.writeInt(h2);
            out.write(b2);
        }
    }

    /**
     * Deserializes the object by reading its fields from the input stream.
     * @param in the input stream to read from
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        int w1 = in.readInt();
        int h1 = in.readInt();

        byte[] b1 = new byte[w1 * h1 * 4];
        in.readFully(b1);

        WritableImage wImage1 = new WritableImage(w1, h1);
        wImage1.getPixelWriter().setPixels(0, 0, w1, h1, PixelFormat.getByteBgraInstance(), b1, 0, w1 * 4);

        correctAnswerImage = wImage1;

        int listSize = in.readInt();
        possibleAnswerImageList = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            int w = in.readInt();
            int h = in.readInt();

            byte[] b = new byte[w * h * 4];
            in.readFully(b);

            WritableImage wImage = new WritableImage(w, h);
            wImage.getPixelWriter().setPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), b, 0, w * 4);

            Image answerImage = wImage;
            possibleAnswerImageList.add(answerImage);
        }
    }
}
