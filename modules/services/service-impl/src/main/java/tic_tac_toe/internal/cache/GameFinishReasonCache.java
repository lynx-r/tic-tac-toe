package tic_tac_toe.internal.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class GameFinishReasonCache {
    private boolean draw;

    public void initCache() {
        draw = false;
    }
}
