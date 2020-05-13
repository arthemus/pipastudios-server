package com.pipastudios.server.controller.request;

import java.io.Serializable;

public class ScoreRequest implements Serializable {

    private Integer userId;

    private Integer points;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "ScoreRequest{" +
                "userId=" + userId +
                ", points=" + points +
                '}';
    }
}
