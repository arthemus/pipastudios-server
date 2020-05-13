package com.pipastudios.server.commons;

import com.pipastudios.server.domain.User;

import java.util.*;

public final class InMemoryDB {

    private final Map<Integer, User> set;

    public InMemoryDB() {
        this.set = Collections.synchronizedMap(new LinkedHashMap<>());
    }

    public Collection<User> fetchAll() {
        return new TreeSet<>(this.set.values());
    }

    public void save(User user) {
        this.set.put(user.getUserId(), user);
    }

    public User findById(int userId) {
        return this.set.get(userId);
    }
}
