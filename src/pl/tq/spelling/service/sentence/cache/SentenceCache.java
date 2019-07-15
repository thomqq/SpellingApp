package pl.tq.spelling.service.sentence.cache;

import pl.tq.spelling.service.mp3Provider.Sentence;
import pl.tq.spelling.service.sentence.util.MD5Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class SentenceCache {
    private String cacheDirectory;

    public SentenceCache(String cacheDirectory) {
        this.cacheDirectory = cacheDirectory;
        if( !this.cacheDirectory.endsWith("/")) {
            this.cacheDirectory += "/";
        }
    }

    public boolean contain(Sentence sentence) {
        File file = new File(preparePath(sentence, "mp3"));
        return Files.exists(file.toPath(), LinkOption.NOFOLLOW_LINKS);
    }

    public String preparePath(Sentence sentence, String suffix) {
        String md5 = MD5Util.MD5(sentence.getText() + "_" + sentence.toString());
        return cacheDirectory + md5 + "_" + sentence.getPollyVoice() + "." + suffix;
    }

    public void add(Sentence sentence, String path) throws IOException {
        File fileSource = new File(path);
        File fileDestination = new File(preparePath(sentence, "mp3"));

        Files.copy(fileSource.toPath(), fileDestination.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});

        File sentenceFile = new File(preparePath(sentence, "txt"));
        Files.write(sentenceFile.toPath(), Arrays.asList(sentence.getText()));
    }
}
