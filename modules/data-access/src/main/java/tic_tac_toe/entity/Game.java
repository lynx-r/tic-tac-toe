package tic_tac_toe.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import tic_tac_toe.enums.GameResult;
import tic_tac_toe.enums.GameStatus;

/**
 * Сущность игра
 */
@Entity
@Table(name = "games")
@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"id", "crossPlayer", "naughtPlayer"})
@EqualsAndHashCode(of = "id")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "cross_player_id")
    private Player crossPlayer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "naught_player_id")
    private Player naughtPlayer;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private GameStatus gameStatus;

    @Column(name = "result")
    @Enumerated(EnumType.ORDINAL)
    private GameResult gameResult;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    private List<Move> moves = new ArrayList<>();
}
