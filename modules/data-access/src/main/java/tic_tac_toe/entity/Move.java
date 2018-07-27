package tic_tac_toe.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import tic_tac_toe.enums.GameSymbol;

/**
 * Сущность ход
 */
@Entity
@Table(name = "moves")
@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"id", "moveNumber", "horizontalPosition", "verticalPosition"})
@EqualsAndHashCode(of = "id")
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @Min(1)
    @Max(9)
    @Column(name = "move_number")
    private int moveNumber;

    @Min(1)
    @Max(3)
    @Column(name = "horizontal_position")
    private int horizontalPosition;

    @Min(1)
    @Max(3)
    @Column(name = "vertical_position")
    private int verticalPosition;

    @Column(name = "symbol")
    @Enumerated(EnumType.ORDINAL)
    private GameSymbol gameSymbol;
}
