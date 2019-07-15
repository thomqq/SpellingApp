package pl.tq.spelling.service.mp3Provider;

public class Sentence {
    private String text;
    private PollyVoiceEnum pollyVoice;

    public Sentence(String text, PollyVoiceEnum pollyVoice) {
        this.text = text;
        this.pollyVoice = pollyVoice;
    }

    public String getText() {
        return text;
    }

    public PollyVoiceEnum getPollyVoice() {
        return pollyVoice;
    }
}
