package tic_tac_toe.controller.tools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/game")
public class GameRedirectController {
    @GetMapping
    public String get(@RequestParam("gameId") Long gameId) {
        return "game.html";
    }
}
