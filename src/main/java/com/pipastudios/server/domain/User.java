package com.pipastudios.server.domain;

public final class User implements Comparable<User> {

    private final int userId;

    private final int score;

    public User(int userId, int score) {
        this.userId = userId;
        this.score = score;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.score = user.getScore();
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(User o) {
        if (this.equals(o))
            return 0;
        if (this.score > o.getScore())
            return -1;
        return 1;
    }
}
