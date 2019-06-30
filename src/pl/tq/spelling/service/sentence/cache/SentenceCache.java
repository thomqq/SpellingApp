package pl.tq.spelling.service.sentence.cache;

import pl.tq.spelling.service.sentence.util.MD5Util;
import pl.tq.spelling.util.Config;

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

    public boolean contain(String sentence) {
        File file = new File(prepareMp3Path(sentence));
        return Files.exists(file.toPath(), LinkOption.NOFOLLOW_LINKS);
    }

    public String prepareMp3Path(String sentence) {
        String md5 = MD5Util.MD5(sentence);
        return cacheDirectory + md5 + ".mp3";
    }

    public String prepareTxtPath(String sentence) {
        String md5 = MD5Util.MD5(sentence);
        return cacheDirectory + md5 + ".txt";
    }

    public void add(String sentence, String path) throws IOException {
        File fileSource = new File(path);
        File fileDestination = new File(prepareMp3Path(sentence));

        Files.copy(fileSource.toPath(), fileDestination.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});

        File sentenceFile = new File(prepareTxtPath(sentence));
        Files.write(sentenceFile.toPath(), Arrays.asList(sentence));
    }
}
