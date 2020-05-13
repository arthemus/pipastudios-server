package com.pipastudios.server.services;

import com.pipastudios.server.commons.InMemoryDB;
import com.pipastudios.server.controller.response.UserPositionResponse;
import com.pipastudios.server.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GameService {

    private final InMemoryDB db;

    @Autowired
    public GameService(InMemoryDB db) {
        this.db = db;
    }

    public void doSavePoints(Integer userId, Integer points) {
        if (userId != null && (points != null && points > 0)) {
            User user = this.getUser(userId);
            if (user == null) {
                this.db.save(new User(userId, points));
            } else {
                this.db.save(new User(userId, user.getScore() + points));
            }
        }
    }

    public User getUser(Integer userId) {
        if (userId == null)
            return null;
        return this.db.findById(userId);
    }

    public int getPosition(Integer userId) {
        if (userId == null)
            return 0;
        final User[] users = this.db.fetchAll().toArray(new User[0]);
        return this.getPosition(users, userId);
    }

    private int getPosition(User[] users, int userId) {
        OptionalInt position = IntStream.range(0, users.length)
                .filter(i -> userId == users[i].getUserId())
                .findFirst();
        if (position.isPresent())
            return position.getAsInt() + 1;
        return 0;
    }

    public List<UserPositionResponse> getHighScore() {
        final Collection<User> users = this.db.fetchAll();
        final User[] array = users.toArray(new User[0]);
        return users.stream().map(o -> {
            int position = getPosition(array, o.getUserId());
            return new UserPositionResponse(o, position);
        }).collect(Collectors.toList());
    }
}
