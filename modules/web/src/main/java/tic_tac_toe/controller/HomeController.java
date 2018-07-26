package tic_tac_toe.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tic_tac_toe.entity.Player;
import tic_tac_toe.repository.PlayerRepository;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
    private final PlayerRepository playerRepository;

    @GetMapping
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @PostMapping
    public void create(@RequestBody String login) {
        playerRepository.save(new Player().setLogin(login));
    }
}
