package game.Entity;


/**
 * Created by Anand on 6/15/2017.
 */
public class BoardLayout {
    Board board;
    Robot []availableRobots;
    AvailableStartingPositions[] availableStartingPositions;
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Robot[] getAvailableRobots() {
        return availableRobots;
    }

    public void setAvailableRobots(Robot[] availableRobots) {
        this.availableRobots = availableRobots;
    }

    public AvailableStartingPositions[] getAvailableStartingPositions() {
        return availableStartingPositions;
    }

    public void setAvailableStartingPositions(AvailableStartingPositions[] availableStartingPositions) {
        this.availableStartingPositions = availableStartingPositions;
    }
}
