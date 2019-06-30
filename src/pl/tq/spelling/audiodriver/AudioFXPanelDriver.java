package pl.tq.spelling.audiodriver;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AudioFXPanelDriver implements AudioDriver {
    private static final JFXPanel fxPanel = new JFXPanel();

    public void play(InputStream streamToPlay) throws Exception {
        byte[] buffer = new byte[streamToPlay.available()];
        streamToPlay.read(buffer);

        File tempFile = File.createTempFile("qqq", "qqq");
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        fileOutputStream.write(buffer);
        fileOutputStream.flush();
        fileOutputStream.close();

        play(tempFile.getAbsolutePath());
    }

    @Override
    public void play(String path) throws Exception {
        new Thread(() -> {
            File file = new File(path);
            Media media = new Media(file.toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(() -> {
                mediaPlayer.play();
                try {
                    System.out.println("Dlusogc: " +  mediaPlayer.getTotalDuration().toMillis());
                    Thread.sleep((long) mediaPlayer.getTotalDuration().toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }
}

