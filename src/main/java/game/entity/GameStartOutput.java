package game.entity;

/**
 * Created by Indhu on 6/4/2017.
 */
public class GameStartOutput {
    public GameStartOutput(String selectedBot) {
        this.selectedBot = selectedBot;
    }

    public String getSelectedBot() {
        return selectedBot;
    }

    public void setSelectedBot(String selectedBot) {
        this.selectedBot = selectedBot;
    }

    private String selectedBot;
}
