package pl.tq.spelling.service.lesson.fake;

import pl.tq.spelling.service.lesson.Lesson;
import pl.tq.spelling.service.lesson.LessonService;

public class FakeLessonService implements LessonService {
    @Override
    public void checkAnswer(Lesson lesson, String answer) {

    }

    @Override
    public Lesson getLastLesson(int id) {
        return new Lesson();
    }
}
