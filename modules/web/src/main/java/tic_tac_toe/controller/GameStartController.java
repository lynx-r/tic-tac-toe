package tic_tac_toe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tic_tac_toe.GameStartService;
import tic_tac_toe.config.Urls.Game.GameStart;
import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.domain.game.start.GameStartResponse;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(GameStart.PART)
public class GameStartController {
    private final GameStartService gameStartService;

    @PostMapping
    public GameStartResponse startGame(@RequestBody GameStartRequest request) {
        return gameStartService.startGame(request);
    }

    @GetMapping
    public GameStartResponse checkGameStarting(@RequestParam Long gameId) {
        return gameStartService.startGame(gameId);
    }
}
