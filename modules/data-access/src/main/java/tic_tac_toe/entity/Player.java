package tic_tac_toe.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Сущность игрок
 */
@Entity
@Table(name = "players")
@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"id", "login"})
@EqualsAndHashCode(of = "id")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "crossPlayer", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    private List<Game> gamesByCross = new ArrayList<>();

    @OneToMany(mappedBy = "naughtPlayer", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    private List<Game> gamesByNaught = new ArrayList<>();
}
