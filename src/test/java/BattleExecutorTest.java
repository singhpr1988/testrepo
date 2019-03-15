import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BattleExecutorTest {

    private BattleExecutor unit;

    @Before
    public void setUp() {
        unit = new BattleExecutor();
    }

    @Test
    public void playerTwoShallWinIfPlayerOneRunsOutOfMissilesAndPlayerOneGetsDestroyedBeforePlayerTwo() throws Exception {
        Location firstCellLocationShipOnePlayerTwo = new Location(2, 2);
        BattleShipCell shipOneCellOnePlayerTwo = new BattleShipCell(firstCellLocationShipOnePlayerTwo, BattleShipType.P);

        Location secondCellLocationShipOnePlayerTwo = new Location(2, 3);
        BattleShipCell shipOneCellTwoPlayerTwo = new BattleShipCell(secondCellLocationShipOnePlayerTwo, BattleShipType.P);

        Location firstCellLocationShipTwoPlayerTwo = new Location(1, 1);
        BattleShipCell shipTwoCellOnePlayerTwo = new BattleShipCell(firstCellLocationShipTwoPlayerTwo, BattleShipType.Q);


        BattleArea battleAreaTwo = new BattleArea(5, 5);

        battleAreaTwo.populateBattleShipCell(shipOneCellOnePlayerTwo);
        battleAreaTwo.populateBattleShipCell(shipOneCellTwoPlayerTwo);
        battleAreaTwo.populateBattleShipCell(shipTwoCellOnePlayerTwo);

        List<String> targetsForPlayerTwo = new ArrayList<String>();

        targetsForPlayerTwo.add("A1");
        targetsForPlayerTwo.add("B2");
        targetsForPlayerTwo.add("B3");
        targetsForPlayerTwo.add("A1");
        targetsForPlayerTwo.add("D1");
        targetsForPlayerTwo.add("E1");
        targetsForPlayerTwo.add("D4");
        targetsForPlayerTwo.add("D4");
        targetsForPlayerTwo.add("D5");
        targetsForPlayerTwo.add("D5");

        Player playerTwo = new Player(2, battleAreaTwo);

        BattleArea battleAreaOne = new BattleArea(5, 5);

        Location firstCellLocationShipOnePlayerOne = new Location(3, 3);
        BattleShipCell shipOneCellOnePlayerOne = new BattleShipCell(firstCellLocationShipOnePlayerOne, BattleShipType.P);

        Location secondCellLocationShipOnePlayerOne = new Location(3, 4);
        BattleShipCell shipOneCellTwoPlayerOne = new BattleShipCell(secondCellLocationShipOnePlayerOne, BattleShipType.P);

        Location FirstCellLocationShipTwoPlayerOne = new Location(0, 0);
        BattleShipCell shipTwoCellOnePlayerOne = new BattleShipCell(FirstCellLocationShipTwoPlayerOne, BattleShipType.Q);

        battleAreaOne.populateBattleShipCell(shipOneCellOnePlayerOne);
        battleAreaOne.populateBattleShipCell(shipOneCellTwoPlayerOne);
        battleAreaOne.populateBattleShipCell(shipTwoCellOnePlayerOne);

        List<String> targetsForPlayerOne = new ArrayList<String>();

        targetsForPlayerOne.add("A1");
        targetsForPlayerOne.add("B2");
        targetsForPlayerOne.add("B2");
        targetsForPlayerOne.add("B3");

        Player playerOne = new Player(1, battleAreaOne);

        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add("Player-1 fires a missile with target A1 which got miss");
        expectedMessages.add("Player-2 fires a missile with target A1 which got hit");
        expectedMessages.add("Player-2 fires a missile with target B2 which got miss");
        expectedMessages.add("Player-1 fires a missile with target B2 which got hit");
        expectedMessages.add("Player-1 fires a missile with target B2 which got hit");
        expectedMessages.add("Player-1 fires a missile with target B3 which got miss");
        expectedMessages.add("Player-2 fires a missile with target B3 which got miss");
        expectedMessages.add("Player-1 has no more missiles left to launch");
        expectedMessages.add("Player-2 fires a missile with target A1 which got hit");

        expectedMessages.add("Player-2 fires a missile with target D1 which got miss");
        expectedMessages.add("Player-1 has no more missiles left to launch");
        expectedMessages.add("Player-2 fires a missile with target E1 which got miss");
        expectedMessages.add("Player-1 has no more missiles left to launch");
        expectedMessages.add("Player-2 fires a missile with target D4 which got hit");
        expectedMessages.add("Player-2 fires a missile with target D4 which got miss");
        expectedMessages.add("Player-1 has no more missiles left to launch");
        expectedMessages.add("Player-2 fires a missile with target D5 which got hit");
        expectedMessages.add("Player-2 won the battle");

        BattleGameInput gameInputOne = new BattleGameInput(playerOne.getPlayerName(), targetsForPlayerOne, playerTwo);
        BattleGameInput gameInputTwo = new BattleGameInput(playerTwo.getPlayerName(), targetsForPlayerTwo, playerOne);

        MoveExecutor moveExecutor = new MoveExecutor();

        List<String> actualMessages = unit.executeBattle(gameInputOne, gameInputTwo, moveExecutor);

        verifyMessages(expectedMessages, actualMessages);

    }

    @Test
    public void playerOneShallWinIfPlayerTwoRunsOutOfMissilesAndPlayerTwoGetsDestroyedBeforePlayerOne() throws Exception {
        Location firstCellLocationShipOnePlayerTwo = new Location(2, 2);
        BattleShipCell shipOneCellOnePlayerTwo = new BattleShipCell(firstCellLocationShipOnePlayerTwo, BattleShipType.P);

        Location secondCellLocationShipOnePlayerTwo = new Location(2, 3);
        BattleShipCell shipOneCellTwoPlayerTwo = new BattleShipCell(secondCellLocationShipOnePlayerTwo, BattleShipType.P);

        Location firstCellLocationShipTwoPlayerTwo = new Location(1, 1);
        BattleShipCell shipTwoCellOnePlayerTwo = new BattleShipCell(firstCellLocationShipTwoPlayerTwo, BattleShipType.Q);


        BattleArea battleAreaTwo = new BattleArea(5, 5);

        battleAreaTwo.populateBattleShipCell(shipOneCellOnePlayerTwo);
        battleAreaTwo.populateBattleShipCell(shipOneCellTwoPlayerTwo);
        battleAreaTwo.populateBattleShipCell(shipTwoCellOnePlayerTwo);

        List<String> targetsForPlayerTwo = new ArrayList<String>();

        targetsForPlayerTwo.add("A1");
        targetsForPlayerTwo.add("B2");
        targetsForPlayerTwo.add("B2");
        targetsForPlayerTwo.add("B3");

        Player playerTwo = new Player(2, battleAreaTwo);

        BattleArea battleAreaOne = new BattleArea(5, 5);

        Location firstCellLocationShipOnePlayerOne = new Location(3, 3);
        BattleShipCell shipOneCellOnePlayerOne = new BattleShipCell(firstCellLocationShipOnePlayerOne, BattleShipType.P);

        Location secondCellLocationShipOnePlayerOne = new Location(3, 4);
        BattleShipCell shipOneCellTwoPlayerOne = new BattleShipCell(secondCellLocationShipOnePlayerOne, BattleShipType.P);

        Location FirstCellLocationShipTwoPlayerOne = new Location(0, 0);
        BattleShipCell shipTwoCellOnePlayerOne = new BattleShipCell(FirstCellLocationShipTwoPlayerOne, BattleShipType.Q);

        battleAreaOne.populateBattleShipCell(shipOneCellOnePlayerOne);
        battleAreaOne.populateBattleShipCell(shipOneCellTwoPlayerOne);
        battleAreaOne.populateBattleShipCell(shipTwoCellOnePlayerOne);

        List<String> targetsForPlayerOne = new ArrayList<String>();

        targetsForPlayerOne.add("A1");
        targetsForPlayerOne.add("B2");
        targetsForPlayerOne.add("B3");
        targetsForPlayerOne.add("B2");
        targetsForPlayerOne.add("D1");
        targetsForPlayerOne.add("E1");
        targetsForPlayerOne.add("C3");
        targetsForPlayerOne.add("D4");
        targetsForPlayerOne.add("C4");
        targetsForPlayerOne.add("D5");

        Player playerOne = new Player(1, battleAreaOne);

        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add("Player-1 fires a missile with target A1 which got miss");
        expectedMessages.add("Player-2 fires a missile with target A1 which got hit");
        expectedMessages.add("Player-2 fires a missile with target B2 which got miss");
        expectedMessages.add("Player-1 fires a missile with target B2 which got hit");
        expectedMessages.add("Player-1 fires a missile with target B3 which got miss");
        expectedMessages.add("Player-2 fires a missile with target B2 which got miss");
        expectedMessages.add("Player-1 fires a missile with target B2 which got hit");
        expectedMessages.add("Player-1 fires a missile with target D1 which got miss");
        expectedMessages.add("Player-2 fires a missile with target B3 which got miss");

        expectedMessages.add("Player-1 fires a missile with target E1 which got miss");
        expectedMessages.add("Player-2 has no more missiles left to launch");
        expectedMessages.add("Player-1 fires a missile with target C3 which got hit");
        expectedMessages.add("Player-1 fires a missile with target D4 which got miss");
        expectedMessages.add("Player-2 has no more missiles left to launch");
        expectedMessages.add("Player-1 fires a missile with target C4 which got hit");
        expectedMessages.add("Player-1 won the battle");

        BattleGameInput gameInputOne = new BattleGameInput(playerOne.getPlayerName(), targetsForPlayerOne, playerTwo);
        BattleGameInput gameInputTwo = new BattleGameInput(playerTwo.getPlayerName(), targetsForPlayerTwo, playerOne);

        MoveExecutor moveExecutor = new MoveExecutor();

        List<String> actualMessages = unit.executeBattle(gameInputOne, gameInputTwo, moveExecutor);

        verifyMessages(expectedMessages, actualMessages);

    }

    @Test
    public void gameShallEndInADraw() throws Exception {

        Location firstCellLocationShipOnePlayerTwo = new Location(2, 2);
        BattleShipCell shipOneCellOnePlayerTwo = new BattleShipCell(firstCellLocationShipOnePlayerTwo, BattleShipType.P);

        Location secondCellLocationShipOnePlayerTwo = new Location(2, 3);
        BattleShipCell shipOneCellTwoPlayerTwo = new BattleShipCell(secondCellLocationShipOnePlayerTwo, BattleShipType.P);

        BattleArea battleAreaTwo = new BattleArea(5, 5);

        battleAreaTwo.populateBattleShipCell(shipOneCellOnePlayerTwo);
        battleAreaTwo.populateBattleShipCell(shipOneCellTwoPlayerTwo);

        Player playerTwo = new Player(2, battleAreaTwo);

        BattleArea battleAreaOne = new BattleArea(5, 5);

        Location firstCellLocationShipOnePlayerOne = new Location(3, 3);
        BattleShipCell shipOneCellOnePlayerOne = new BattleShipCell(firstCellLocationShipOnePlayerOne, BattleShipType.P);

        Location secondCellLocationShipOnePlayerOne = new Location(3, 4);
        BattleShipCell shipOneCellTwoPlayerOne = new BattleShipCell(secondCellLocationShipOnePlayerOne, BattleShipType.P);

        battleAreaOne.populateBattleShipCell(shipOneCellOnePlayerOne);
        battleAreaOne.populateBattleShipCell(shipOneCellTwoPlayerOne);

        Player playerOne = new Player(1, battleAreaOne);

        List<String> targetsForPlayerTwo = new ArrayList<String>();

        targetsForPlayerTwo.add("C2");
        targetsForPlayerTwo.add("D4");

        List<String> targetsForPlayerOne = new ArrayList<String>();

        targetsForPlayerOne.add("C3");
        targetsForPlayerOne.add("E1");

        BattleGameInput gameInputOne = new BattleGameInput(playerOne.getPlayerName(), targetsForPlayerOne, playerTwo);
        BattleGameInput gameInputTwo = new BattleGameInput(playerTwo.getPlayerName(), targetsForPlayerTwo, playerOne);

        MoveExecutor moveExecutor = new MoveExecutor();

        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add("Player-1 fires a missile with target C3 which got hit");
        expectedMessages.add("Player-1 fires a missile with target E1 which got miss");
        expectedMessages.add("Player-2 fires a missile with target C2 which got miss");
        expectedMessages.add("Player-1 has no more missiles left to launch");
        expectedMessages.add("Player-2 fires a missile with target D4 which got hit");
        expectedMessages.add("Game ended in a draw");

        verifyMessages(expectedMessages, unit.executeBattle(gameInputOne, gameInputTwo, moveExecutor));
    }

    private void verifyMessages(List<String> expectedMessages, List<String> actualMessages) {
        Assert.assertEquals(expectedMessages.size(), actualMessages.size());
        for (int i = 0; i < expectedMessages.size(); i++) {
            Assert.assertEquals(expectedMessages.get(i), actualMessages.get(i));
        }
    }
}
