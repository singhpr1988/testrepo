public enum BattleShipType {
    P("P", 1),
    Q("Q", 2);

    private String type;
    private int numberOfHitsToDestroyCell;

    BattleShipType(String type, int numberOfHitsToDestroyCell) {
        this.type = type;
        this.numberOfHitsToDestroyCell = numberOfHitsToDestroyCell;
    }

    public String getType() {
        return type;
    }

    public int getNumberOfHitsToDestroyCell() {
        return numberOfHitsToDestroyCell;
    }
}
