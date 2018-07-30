package tic_tac_toe.rest.data.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataProvider {
    private final RepositoriesContainer repositories;

    public void clear() {
        repositories.clearAll();
    }
}
