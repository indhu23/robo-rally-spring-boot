package game.entity;

/**
 * Created by Indhu on 6/11/2017.
 */
public class GameJoinOutputWrapper {
    public GameJoinOutputWrapper(int status) {
        this.status = status;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;


}
