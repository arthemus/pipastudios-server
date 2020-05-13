package com.pipastudios.server.controller;

import com.pipastudios.server.controller.request.ScoreRequest;
import com.pipastudios.server.controller.response.HighScoreResponse;
import com.pipastudios.server.controller.response.UserPositionResponse;
import com.pipastudios.server.domain.User;
import com.pipastudios.server.services.GameService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/score",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> onPostScore(@RequestBody ScoreRequest scoreRequest) {
        log.info("Posting /score for user {}, points {}.", scoreRequest.getUserId(), scoreRequest.getPoints());
        try {
            this.gameService.doSavePoints(scoreRequest.getUserId(), scoreRequest.getPoints());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{userId}/position", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> onGetPosition(@PathVariable Integer userId) {
        log.info("Requesting /{}/position", userId);
        try {
            User user = this.gameService.getUser(userId);
            if (user == null)
                return ResponseEntity.ok().build();
            int position = this.gameService.getPosition(userId);
            return ResponseEntity.ok().body(new UserPositionResponse(user, position));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/highscorelist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> onGetHighScore() {
        log.info("Requesting /highscorelist");
        try {
            List<UserPositionResponse> highScore = this.gameService.getHighScore();
            return ResponseEntity.ok().body(new HighScoreResponse(highScore));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
