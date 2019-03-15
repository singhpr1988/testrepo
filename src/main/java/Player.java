public class Player {

    private int playerNumber;
    private BattleArea battleArea;

    public Player(int playerNumber, BattleArea battleArea) {
        this.playerNumber = playerNumber;
        this.battleArea = battleArea;
    }

    protected String getPlayerName() {
        return "Player-" + this.playerNumber;
    }

    protected BattleArea getBattleArea() {
        return battleArea;
    }
}
