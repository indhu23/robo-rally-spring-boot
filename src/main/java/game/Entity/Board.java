package game.Entity;

import java.util.List;

public class Board {
    private BoardSize size;

    public BoardSize getSize() {
        return size;
    }

    public void setSize(BoardSize size) {
        this.size = size;
    }

    public List<BoardPosition> getPosition() {
        return position;
    }

    public void setPosition(List<BoardPosition> position) {
        this.position = position;
    }

    private List<BoardPosition> position;
}
