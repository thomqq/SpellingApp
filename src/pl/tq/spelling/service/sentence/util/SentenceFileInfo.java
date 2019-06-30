package pl.tq.spelling.service.sentence.util;

public class SentenceFileInfo {
    private String path;
    private String text;
    private String md5;

    public SentenceFileInfo(String path, String text, String md5) {
        this.path = path;
        this.text = text;
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public String getText() {
        return text;
    }

    public String getMd5() {
        return md5;
    }
}
