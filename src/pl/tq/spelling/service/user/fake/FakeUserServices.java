package pl.tq.spelling.service.user.fake;

import pl.tq.spelling.service.user.User;
import pl.tq.spelling.service.user.UserService;

public class FakeUserServices implements UserService {
    @Override
    public User checkAndGetUser(String login, String password) {
        return new User(1);
    }
}
