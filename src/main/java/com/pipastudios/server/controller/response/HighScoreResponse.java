package com.pipastudios.server.controller.response;

import java.io.Serializable;
import java.util.List;

public class HighScoreResponse implements Serializable {

    private List<UserPositionResponse> highscores;

    public HighScoreResponse() {
    }

    public HighScoreResponse(List<UserPositionResponse> highScore) {
        this.highscores = highScore;
    }

    public List<UserPositionResponse> getHighscores() {
        return highscores;
    }

    public void setHighscores(List<UserPositionResponse> highscores) {
        this.highscores = highscores;
    }

    @Override
    public String toString() {
        return "HighScoreResponse{" +
                "highscores=" + highscores +
                '}';
    }
}
