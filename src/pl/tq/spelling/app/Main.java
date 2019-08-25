package pl.tq.spelling.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.tq.apilimiter.config.ApiLimiterModule;
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
        Injector injector = Guice.createInjector(new ApiLimiterModule());

        UserService userService = new FakeUserServices();

        Config config = injector.getInstance(Config.class);

        String login = config.getValue("login");
        String password = config.getValue("password");
        User user = userService.checkAndGetUser(login, password);

        LessonService lessonService = new FakeLessonService();
        PollyMp3Provider pollyMp3Provider = injector.getInstance(PollyMp3Provider.class);
        SentenceService sentenceService = new SentencePollyService(new SentenceCache(config.getValue("mp3CacheDirectory")), pollyMp3Provider);

        SpellingApp spellingApp = new SpellingApp(config, user, lessonService, sentenceService, new AudioFXPanelDriver());
        spellingApp.run();
    }
}
