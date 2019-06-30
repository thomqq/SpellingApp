package pl.tq.spelling.service.user;

public interface UserService {
    User checkAndGetUser(String login, String password);
}
