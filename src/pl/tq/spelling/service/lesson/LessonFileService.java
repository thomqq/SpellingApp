package pl.tq.spelling.service.lesson;

import com.google.gson.Gson;

import java.util.List;

public class LessonFileService implements LessonService {
    private final String contextPath;
    private static final String CONTEXT_FILE = "contex.obj";

    public LessonFileService(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public void checkAnswer(Lesson lesson, String answer) {

    }

    @Override
    public Lesson getLastLesson(int id) {
        LessonContext lessonContext = loadCurrentContext();
        if( lessonContext == null ) {
            startNewLesson(id);
        }
        Lesson lesson = lessonContext.getCurrentLesson();

        return null;
    }

    private LessonContext loadCurrentContext() {
        Gson gson = new Gson();
        return null;
    }

    @Override
    public void startNewLesson(int id) {
        List<Long> lessons = LessonRepository.getLessons(id);
        LessonContext lessonContext = createNewContext(lessons);
        saveCurrentContext(lessonContext);
    }

    private void saveCurrentContext(LessonContext lessonContext) {

    }

    private LessonContext createNewContext(List<Long> lessons) {
        return null;
    }

}
