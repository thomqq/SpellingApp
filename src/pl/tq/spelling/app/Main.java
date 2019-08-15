package pl.tq.spelling.app;

import pl.tq.spelling.audiodriver.AudioFXPanelDriver;
import pl.tq.spelling.service.lesson.LessonService;
import pl.tq.spelling.service.lesson.fake.FakeLessonService;
import pl.tq.spelling.service.mp3Provider.PollyMp3Provider;
import pl.tq.spelling.service.sentence.SentencePollyService;
import pl.tq.spelling.service.sentence.SentenceService;
import pl.tq.spelling.service.sentence.cache.SentenceCache;
import pl.tq.spelling.service.user.User;
import pl.tq.spelling.service.user.UserService;
import pl.tq.spelling.service.user.fake.FakeUserServices;
import pl.tq.spelling.util.Config;

public class Main {

    public static void main(String[] args) throws Exception {
        UserService userService = new FakeUserServices();

        String pathToConfig = System.getenv().get("SPELLING_CONFIG") + "AudioCfg.txt";
        Config config = new Config(pathToConfig);

        String login = config.getValue("login");
        String password = config.getValue("password");
        User user = userService.checkAndGetUser(login, password);


        LessonService lessonService = new FakeLessonService();
        SentenceService sentenceService = new SentencePollyService(new SentenceCache(config.getValue("mp3CacheDirectory")), new PollyMp3Provider(config));

        SpellingApp spellingApp = new SpellingApp(config, user, lessonService, sentenceService, new AudioFXPanelDriver());
        spellingApp.run();
    }
}
