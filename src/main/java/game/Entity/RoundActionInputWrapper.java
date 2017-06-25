package game.Entity;

import java.util.List;

/**
 *  Wrapper for holding the details of robot positions and actions
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
