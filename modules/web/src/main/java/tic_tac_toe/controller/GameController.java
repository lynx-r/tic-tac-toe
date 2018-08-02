package tic_tac_toe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tic_tac_toe.GameService;
import tic_tac_toe.config.Urls.Game.GameFlow;
import tic_tac_toe.config.Urls.Game.GameFlow.Field;
import tic_tac_toe.config.Urls.Game.GameFlow.Status;
import tic_tac_toe.domain.field.FieldResponse;
import tic_tac_toe.domain.game.GameStatusResponse;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.domain.move.MoveResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(GameFlow.FULL)
public class GameController {
    private final GameService gameService;

    @PostMapping
    public MoveResponse makeMove(@PathVariable Long id, @RequestBody MoveRequest request) {
        return gameService.makeMove(id, request);
    }

    @GetMapping(Field.PART)
    public FieldResponse getFieldState(@PathVariable Long id) {
        return gameService.getFieldState(id);
    }

    @GetMapping(Status.FULL)
    public GameStatusResponse getGameStatus(@PathVariable Long id) {
        return gameService.getGameStatus(id);
    }
}
