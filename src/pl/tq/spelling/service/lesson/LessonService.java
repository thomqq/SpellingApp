package pl.tq.spelling.service.lesson;

public interface LessonService {
    void checkAnswer(Lesson lesson, String answer);

    Lesson getLastLesson(int id);
}

