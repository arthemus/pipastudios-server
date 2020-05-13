package com.pipastudios.server.controller.response;

import com.pipastudios.server.domain.User;

import java.io.Serializable;

public class UserPositionResponse implements Serializable {

    private Integer userId;

    private Integer score;

    private Integer position;

    public UserPositionResponse() {
    }

    public UserPositionResponse(User user, int position) {
        this.userId = user.getUserId();
        this.score = user.getScore();
        this.position = position;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UserPositionResponse{" +
                "userId=" + userId +
                ", score=" + score +
                ", position=" + position +
                '}';
    }
}
