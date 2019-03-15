public class BattleArea {

    private int width;
    private int height;

    private BattleShipCell[][] battleShipCells;
    private int numberOfHitsCanTake;

    public BattleArea(int width, int height) {
        this.width = width;
        this.height = height;
        battleShipCells = new BattleShipCell[height][width];
    }

    protected boolean isHit(Location location) {
        if (battleShipCells[location.getX()][location.getY()] == null) {
            return false;
        } else {
            BattleShipCell battleShipCell = battleShipCells[location.getX()][location.getY()];
            battleShipCell.takeHit();
            this.numberOfHitsCanTake--;
            if (battleShipCell.isCellDestroyed()) {
                battleShipCells[location.getX()][location.getY()] = null;
            }
            return true;
        }
    }

    protected void populateBattleShipCell(BattleShipCell battleShipCell) {
        this.numberOfHitsCanTake += battleShipCell.getNumberOfHitsCanTake();
        battleShipCells[battleShipCell.getLocation().getX()][battleShipCell.getLocation().getY()] = battleShipCell;
    }

    public boolean isBattleAreaDestroyed() {
        return numberOfHitsCanTake == 0 ? true : false;
    }
}
