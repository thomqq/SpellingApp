package pl.tq.spelling.app;

import pl.tq.spelling.app.menu.Menu;
import pl.tq.spelling.app.menu.MenuItem;
import pl.tq.spelling.audiodriver.AudioDriver;
import pl.tq.spelling.service.lesson.Lesson;
import pl.tq.spelling.service.lesson.LessonService;
import pl.tq.spelling.service.mp3Provider.PollyVoiceEnum;
import pl.tq.spelling.service.sentence.SentenceService;
import pl.tq.spelling.service.user.User;
import pl.tq.spelling.util.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpellingApp {
    private Config config;
    private final User user;
    private final LessonService lessonService;
    private final SentenceService sentenceService;
    private AudioDriver audioDriver;

    private Lesson currentLesson;
    private Menu menu;

    public SpellingApp(Config config, User user, LessonService lessonService, SentenceService sentenceService, AudioDriver audioDriver) throws Exception {
        this.config = config;
        this.user = user;
        this.lessonService = lessonService;
        this.sentenceService = sentenceService;
        this.audioDriver = audioDriver;
    }

    public void run() {
        currentLesson = lessonService.getLastLesson(user.getId());
        prepareMenu();

        for (; ; ) {
            try {
                printMenu();
                String choosePos = getStringFromConsole();
                int pos = Integer.parseInt(choosePos);
                menu.executePos(pos);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void prepareMenu() {
        menu = new Menu();
        menu.add(new MenuItem(1, "Czytaj", () -> {
            String mp3Sentence = sentenceService.getAudio(currentLesson.getSentence());
            audioDriver.play(mp3Sentence);
        }));

        menu.add(new MenuItem(2, "Odpowiadam", () -> {
            String answer = getStringFromConsole();
            lessonService.checkAnswer(currentLesson, answer);
        }));

        menu.add(new MenuItem(3, "Powiedz", () -> {
            String sentence = getStringFromConsole();
            String mp3Sentence = sentenceService.getAudio(sentence);
            audioDriver.play(mp3Sentence);
        }));

        menu.add(new MenuItem(4, "Ustaw Angielski", () -> {
            sentenceService.setPollyVoice(PollyVoiceEnum.EN);
        }));

        menu.add(new MenuItem(5, "Ustaw Polski", () -> {
            sentenceService.setPollyVoice(PollyVoiceEnum.PL);
        }));

        menu.add(new MenuItem(0, "Koniec", () -> {
            System.exit(0);
        }));
    }

    private void printMenu() {
        menu.printMenu(System.out);
    }

    private String getStringFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = br.readLine();
        return result;
    }
}
