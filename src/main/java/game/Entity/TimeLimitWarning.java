package game.Entity;

/**
 * Created by Anand on 6/5/2017.
 */
public class TimeLimitWarning {
long secondsLeft;
String reason;

    public long getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(long secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

