public enum  OutPutMessages {

    MISS("fires a missile with target", "which got miss"),
    HIT("fires a missile with target", "which got hit"),
    NO_MISSILES_LEFT("has no more missiles left to launch", ""),
    BATTLE_WON("won the battle", ""),
    DRAW("Game ended in a draw", ""),
    BATTLE_AREA_ALREADY_DESTROYED("battle area already destroyed", "");

    OutPutMessages(String messageOne, String messageTwo) {
        this.messageOne = messageOne;
        this.messageTwo = messageTwo;
    }

    private String messageOne;
    private String messageTwo;

    public String getMessageOne() {
        return messageOne;
    }

    public String getMessageTwo() {
        return messageTwo;
    }
}
