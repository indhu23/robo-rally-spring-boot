package game.Entity;

import java.util.List;

/**
 * Wrapper for holding list of games
 */
public class wrapper {

    public List<GameViewOutputWrapper> getGames() {
        return games;
    }

    public void setGames(List<GameViewOutputWrapper> games) {
        this.games = games;
    }

    private List<GameViewOutputWrapper> games;


}
