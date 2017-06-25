package game.Entity;

public class Game {
    private int gameId;
    private String gameName;
    private String gameStatus;
    public Game(){}

    public Game(int gameId, String gameName, String gameStatus) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameStatus = gameStatus;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
