package pl.tq.spelling.service.lesson;

public class Lesson {

    private final String sentence;
    private final String answer;

    public Lesson(String sentence, String answer) {
        this.sentence = sentence;
        this.answer = answer;
    }

    public String getSentence() {
        return sentence;
    }

    public String getAnswer() {
        return answer;
    }
}
