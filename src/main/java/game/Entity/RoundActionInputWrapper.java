package game.Entity;

import java.util.List;

/**
 * Created by Indhu on 6/15/2017.
 */
public class RoundActionInputWrapper {
    private List<RobotPostionDetail> position;
    private List<RobotActionDetail> action;

    public List<RobotActionDetail> getAction() {
        return action;
    }

    public void setAction(List<RobotActionDetail> action) {
        this.action = action;
    }

    public List<RobotPostionDetail> getPosition() {
        return position;
    }

    public void setPosition(List<RobotPostionDetail> position) {
        this.position = position;
    }

}
