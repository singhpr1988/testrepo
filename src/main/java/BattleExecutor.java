import java.util.ArrayList;
import java.util.List;

public class BattleExecutor {

    /*public List<String> executeBattle(BattleGameInput playerOne, BattleGameInput playerTwo, MoveExecutor moveExecutor) throws Exception {

        List<String> movesResult = new ArrayList<String>();

        try {
            List<String> targetsForPlayerOne = playerOne.getTargets();
            List<String> targetsForPlayerTwo = playerTwo.getTargets();

            List<String> mergedListOfTargets = mergeList(targetsForPlayerOne, targetsForPlayerTwo);

            int startIndexForPlayerOne = 0;
            int startIndexForPlayerTwo = targetsForPlayerOne.size();
            int endIndexForPlayerOne = startIndexForPlayerTwo - 1;
            int endIndexForPlayerTwo = startIndexForPlayerTwo + targetsForPlayerTwo.size() - 1;
            boolean flag = true;

            while (startIndexForPlayerOne <= endIndexForPlayerOne && startIndexForPlayerTwo <=endIndexForPlayerTwo) {
                if (flag) {
                    String result = moveExecutor.executeMoveWhenMissilesLeft(playerOne.getPlayerName(), playerOne.getOpponent(), mergedListOfTargets.get(startIndexForPlayerOne));
                    if (!result.contains(OutPutMessages.HIT.getMessageTwo())) {
                        flag = false;
                    }
                    updateFinalResult(movesResult, result);
                    startIndexForPlayerOne++;
                } else {
                    String result = moveExecutor.executeMoveWhenMissilesLeft(playerTwo.getPlayerName(), playerTwo.getOpponent(), mergedListOfTargets.get(startIndexForPlayerTwo));
                    if (!result.contains(OutPutMessages.HIT.getMessageTwo())) {
                        flag = true;
                    }
                    updateFinalResult(movesResult, result);
                    startIndexForPlayerTwo++;
                }
            }

            if (!playerOne.getOpponent().getBattleArea().isBattleAreaDestroyed() && !playerTwo.getOpponent().getBattleArea().isBattleAreaDestroyed()) {
                if (startIndexForPlayerOne > endIndexForPlayerOne && startIndexForPlayerTwo <= endIndexForPlayerTwo) {
                    playRemainingMoves(startIndexForPlayerTwo, endIndexForPlayerTwo, playerTwo, mergedListOfTargets, moveExecutor, movesResult);

                } else if (startIndexForPlayerTwo > endIndexForPlayerTwo && startIndexForPlayerOne <= endIndexForPlayerOne) {
                    playRemainingMoves(startIndexForPlayerOne, endIndexForPlayerOne, playerOne, mergedListOfTargets, moveExecutor, movesResult);
                }
            }

            if (!playerOne.getOpponent().getBattleArea().isBattleAreaDestroyed() && !playerTwo.getOpponent().getBattleArea().isBattleAreaDestroyed()) {
                updateFinalResult(movesResult, OutPutMessages.DRAW.getMessageOne());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return movesResult;
    }*/

    public List<String> executeBattle(BattleGameInput playerOne, BattleGameInput playerTwo, MoveExecutor moveExecutor) throws Exception {

        List<String> movesResult = new ArrayList<String>();

        try {
            List<String> targetsForPlayerOne = playerOne.getTargets();
            List<String> targetsForPlayerTwo = playerTwo.getTargets();

            //List<String> mergedListOfTargets = mergeList(targetsForPlayerOne, targetsForPlayerTwo);

            int startIndexForPlayerOne = 0;
            int startIndexForPlayerTwo = 0;

            boolean flag = true;

            while (playerOne.areMovesLeft(startIndexForPlayerOne) && playerTwo.areMovesLeft(startIndexForPlayerTwo)) {
                if (flag) {
                    String result = moveExecutor.executeMoveWhenMissilesLeft(playerOne.getPlayerName(), playerOne.getOpponent(), targetsForPlayerOne.get(startIndexForPlayerOne));
                    if (!result.contains(OutPutMessages.HIT.getMessageTwo())) {
                        flag = false;
                    }
                    updateFinalResult(movesResult, result);
                    startIndexForPlayerOne++;
                } else {
                    String result = moveExecutor.executeMoveWhenMissilesLeft(playerTwo.getPlayerName(), playerTwo.getOpponent(), targetsForPlayerTwo.get(startIndexForPlayerTwo));
                    if (!result.contains(OutPutMessages.HIT.getMessageTwo())) {
                        flag = true;
                    }
                    updateFinalResult(movesResult, result);
                    startIndexForPlayerTwo++;
                }
            }

           if (!playerOne.getOpponent().getBattleArea().isBattleAreaDestroyed() && !playerTwo.getOpponent().getBattleArea().isBattleAreaDestroyed()) {
                if (!playerOne.areMovesLeft(startIndexForPlayerOne) && playerTwo.areMovesLeft(startIndexForPlayerTwo)) {
                    playRemainingMoves(startIndexForPlayerTwo, playerTwo, targetsForPlayerTwo, moveExecutor, movesResult);

                } else if (playerOne.areMovesLeft(startIndexForPlayerOne) && !playerTwo.areMovesLeft(startIndexForPlayerTwo)) {
                    playRemainingMoves(startIndexForPlayerOne, playerOne, targetsForPlayerOne, moveExecutor, movesResult);
                }
            }

            if (!playerOne.getOpponent().getBattleArea().isBattleAreaDestroyed() && !playerTwo.getOpponent().getBattleArea().isBattleAreaDestroyed()) {
                updateFinalResult(movesResult, OutPutMessages.DRAW.getMessageOne());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return movesResult;
    }

    private void updateFinalResult(List<String> movesResult, String result) {
        if (!result.contains(OutPutMessages.BATTLE_AREA_ALREADY_DESTROYED.getMessageOne())) {
            if (result.contains(OutPutMessages.HIT.getMessageTwo()) && result.contains(OutPutMessages.BATTLE_WON.getMessageOne())) {
                String arr[] = result.split(",");
                movesResult.add(arr[0].trim());
                movesResult.add(arr[1].trim());
            } else {
                movesResult.add(result);
            }
        }
    }

    /*private void playRemainingMoves(int startIndex, int endIndex, BattleGameInput player, List<String> mergedTargets, MoveExecutor moveExecutor, List<String> movesResult) {
        while (startIndex <= endIndex) {
            try {
                    String result = moveExecutor.executeMoveWhenMissilesLeft(player.getPlayerName(), player.getOpponent(), mergedTargets.get(startIndex));
                    updateFinalResult(movesResult, result);
                if (!result.contains(OutPutMessages.HIT.getMessageTwo()) && !player.getOpponent().getBattleArea().isBattleAreaDestroyed()) {
                    movesResult.add(moveExecutor.executeMoveWhenNoMissilesLeft(player.getOpponent().getPlayerName()));
                }
                startIndex++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }*/

    private void playRemainingMoves(int currentIndex, BattleGameInput player, List<String> targets, MoveExecutor moveExecutor, List<String> movesResult) {
        while (currentIndex < targets.size()) {
            try {
                String result = moveExecutor.executeMoveWhenMissilesLeft(player.getPlayerName(), player.getOpponent(), targets.get(currentIndex));
                updateFinalResult(movesResult, result);
                if (!result.contains(OutPutMessages.HIT.getMessageTwo()) && !player.getOpponent().getBattleArea().isBattleAreaDestroyed()) {
                    movesResult.add(moveExecutor.executeMoveWhenNoMissilesLeft(player.getOpponent().getPlayerName()));
                }
                currentIndex++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private List<String> mergeList(List<String> targetsForPlayerOne, List<String> targetsForPlayerTwo) {
        List<String> playersMoves = new ArrayList<String>();
        playersMoves = merge(playersMoves, targetsForPlayerOne);
        playersMoves = merge(playersMoves, targetsForPlayerTwo);
        return playersMoves;
    }

    private List<String> merge(List<String> playersMoves, List<String> targets) {
        for (String move : targets) {
            playersMoves.add(move);
        }
        return playersMoves;
    }

}
