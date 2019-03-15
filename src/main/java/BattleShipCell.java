public class BattleShipCell {

    private int numberOfHitsCanTake;
    private Location location;
    private BattleShipType battleShipType;

    public BattleShipCell(Location location, BattleShipType battleShipType) {
        this.location = location;
        this.battleShipType = battleShipType;
        this.numberOfHitsCanTake = battleShipType.getNumberOfHitsToDestroyCell();
    }

    protected boolean isCellDestroyed() {
        return numberOfHitsCanTake == 0 ? true : false;
    }

    protected void takeHit() {
        if (this.numberOfHitsCanTake > 0) {
            this.numberOfHitsCanTake--;
        }
    }

    protected int getNumberOfHitsCanTake() {
        return numberOfHitsCanTake;
    }

    protected Location getLocation() {
        return location;
    }
}
