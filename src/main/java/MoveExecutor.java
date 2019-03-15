
public class MoveExecutor {

    public static final String SPACE = " ";

    public String executeMoveWhenMissilesLeft(String playerName, Player opponent, String target) throws  Exception {
        try {
            BattleArea opponentBattleArea = opponent.getBattleArea();

            Location opponentCellLocation = decipherOpponentCellLocation(target);

            if (opponentBattleArea.isBattleAreaDestroyed()) {
                return prepareMessage(opponent.getPlayerName(), OutPutMessages.BATTLE_AREA_ALREADY_DESTROYED, null).trim();
            } else {
                if (opponentBattleArea.isHit(opponentCellLocation)) {
                    if (opponentBattleArea.isBattleAreaDestroyed()) {
                        return prepareMessage(playerName, OutPutMessages.HIT, target).trim() + "," + prepareMessage(playerName, OutPutMessages.BATTLE_WON, null).trim();
                    } else {
                        return prepareMessage(playerName, OutPutMessages.HIT, target).trim();
                    }
                } else {
                    return prepareMessage(playerName, OutPutMessages.MISS, target).trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Location decipherOpponentCellLocation(String target) {
            char arr[] = target.toCharArray();
            int x = (int)arr[0] - 65;
            int y = Character.getNumericValue(arr[1]) - 1;
            return new Location(x, y);
    }

    public String executeMoveWhenNoMissilesLeft(String playerName) throws Exception {
        return prepareMessage(playerName, OutPutMessages.NO_MISSILES_LEFT, null).trim();
    }

    private String prepareMessage(String playerName, OutPutMessages outPutMessages, String target) throws Exception {
        try {
            if (target == null) {
                return playerName + SPACE + outPutMessages.getMessageOne() + SPACE + outPutMessages.getMessageTwo();
            }
            return playerName + SPACE + outPutMessages.getMessageOne() + SPACE + target + SPACE + outPutMessages.getMessageTwo();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
