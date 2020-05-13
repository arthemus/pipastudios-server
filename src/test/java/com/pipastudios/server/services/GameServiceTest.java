package com.pipastudios.server.services;

import com.pipastudios.server.commons.InMemoryDB;
import com.pipastudios.server.controller.response.UserPositionResponse;
import com.pipastudios.server.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameServiceTest {

    private final GameService gameService = new GameService(new InMemoryDB());

    @Test
    public void test_saving_points_without_user_id() throws Exception {
        try {
            this.gameService.doSavePoints(null, 0);
        } catch (Exception e) {
            throw new Exception("Points should be saved even with a userId null.");
        }
    }

    @Test
    public void test_saving_points_for_new_user() {
        this.gameService.doSavePoints(1, 10);
        int position = this.gameService.getPosition(1);
        Assertions.assertEquals(position, 1);
    }

    @Test
    public void test_get_user_not_null() {
        this.gameService.doSavePoints(1, 15);
        User user = this.gameService.getUser(1);
        Assertions.assertEquals(15, user.getScore());
    }

    @Test
    public void test_get_correct_position() {
        this.gameService.doSavePoints(1, 10);
        this.gameService.doSavePoints(2, 5);
        this.gameService.doSavePoints(3, 15);
        int position = this.gameService.getPosition(2);
        Assertions.assertEquals(3, position);
    }

    @Test
    public void test_getting_highscore_list() {
        this.gameService.doSavePoints(1, 10);
        this.gameService.doSavePoints(2, 30);
        this.gameService.doSavePoints(3, 20);
        this.gameService.doSavePoints(4, 5);
        List<UserPositionResponse> highScore = this.gameService.getHighScore();
        Assertions.assertNotNull(highScore);
        Assertions.assertEquals(2, highScore.get(0).getUserId());
        Assertions.assertEquals(1, highScore.get(0).getPosition());
    }

    @Test
    public void test_getting_empty_highscore_list() {
        List<UserPositionResponse> highScore = this.gameService.getHighScore();
        Assertions.assertNotNull(highScore);
        Assertions.assertTrue(highScore.isEmpty());
    }
}
