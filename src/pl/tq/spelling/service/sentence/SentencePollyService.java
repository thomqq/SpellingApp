package pl.tq.spelling.service.sentence;

import pl.tq.spelling.service.mp3Provider.Mp3Provider;
import pl.tq.spelling.service.sentence.cache.SentenceCache;
import java.io.IOException;

public class SentencePollyService implements SentenceService {
    private final SentenceCache sentenceCache;
    private final Mp3Provider mp3Provider;

    public SentencePollyService(SentenceCache sentenceCache, Mp3Provider mp3Provider) {
        this.sentenceCache = sentenceCache;
        this.mp3Provider = mp3Provider;
    }


    @Override
    public String getAudio(String sentence) {
        try {
            if (!sentenceCache.contain(sentence)) {
                String path = mp3Provider.getPathToMp3Sentence(sentence);
                sentenceCache.add(sentence, path);
            }
            return sentenceCache.prepareMp3Path(sentence);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
