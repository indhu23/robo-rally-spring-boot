package game.Entity;

/**
 * Created by Anand on 6/3/2017.
 */
public class Player {
private PlayerPositions []playerPositions;
private Cards []drawPile=new Cards[9];

    public PlayerPositions[] getPlayerPositions() {
        return playerPositions;
    }

    public void setPlayerPositions(PlayerPositions[] playerPositions) {
        this.playerPositions = playerPositions;
    }

    public Cards[] getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(Cards[] drawPile) {
        this.drawPile = drawPile;
    }
}
