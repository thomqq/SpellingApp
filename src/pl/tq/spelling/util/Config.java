package pl.tq.spelling.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Config {
    private final String configPath;

    public Config(String configPath) {
        this.configPath = configPath;
    }

    public String getValue( String key) {
        File file = new File(configPath);
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for( String line : lines) {
                if( line.trim().startsWith("#")) continue;
                String[] item = line.split("=");
                if( item.length < 2 ) {
                    return null;
                }
                if( item[0].trim().equals(key) ) {
                    return item[1].trim();
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}
