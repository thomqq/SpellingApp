package pl.tq.spelling.service.sentence;

import pl.tq.spelling.service.mp3Provider.Mp3Provider;
import pl.tq.spelling.service.mp3Provider.PollyVoiceEnum;
import pl.tq.spelling.service.mp3Provider.Sentence;
import pl.tq.spelling.service.sentence.cache.SentenceCache;
import java.io.IOException;

public class SentencePollyService implements SentenceService {
    private final SentenceCache sentenceCache;
    private final Mp3Provider mp3Provider;
    private PollyVoiceEnum pollyVoice;

    public SentencePollyService(SentenceCache sentenceCache, Mp3Provider mp3Provider) {
        this.sentenceCache = sentenceCache;
        this.mp3Provider = mp3Provider;
        this.pollyVoice = PollyVoiceEnum.EN;
    }


    @Override
    public String getAudio(String text) {
        Sentence sentence = new Sentence(text, pollyVoice);
        try {
            if (!sentenceCache.contain(sentence)) {
                String path = mp3Provider.getPathToMp3Sentence(sentence);
                sentenceCache.add(sentence, path);
            }
            return sentenceCache.preparePath(sentence, "mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setPollyVoice(PollyVoiceEnum pollyVoice) {
        this.pollyVoice = pollyVoice;
    }
}
