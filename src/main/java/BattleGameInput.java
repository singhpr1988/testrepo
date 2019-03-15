
import java.util.List;


public class BattleGameInput {

    private String playerName;
    private List<String> targets;
    private Player opponent;

    public boolean areMovesLeft(int index) {
        return index <= targets.size() - 1 ? true : false;
    }


    public BattleGameInput(String playerName, List<String> targets, Player opponent) {
        this.playerName = playerName;
        this.targets = targets;
        this.opponent = opponent;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getTargets() {
        return targets;
    }

    public Player getOpponent() {
        return opponent;
    }
}
