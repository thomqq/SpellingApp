package pl.tq.spelling.service.sentence;

import pl.tq.spelling.service.mp3Provider.PollyVoiceEnum;

public interface SentenceService {
    String getAudio(String sentence);
    void setPollyVoice(PollyVoiceEnum pollyVoice);
}
