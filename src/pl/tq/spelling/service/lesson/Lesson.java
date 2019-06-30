package pl.tq.spelling.service.lesson;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private List<String> sentences;

    public Lesson() {
        sentences = new ArrayList<>();
        sentences.add("I like summettime.");
    }


    public String getSentence() {
        return sentences.get(0);
    }
}
