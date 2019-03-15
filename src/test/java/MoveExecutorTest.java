import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MoveExecutorTest {

    private MoveExecutor unit;

    @Before
    public void setUp() {
        unit = new MoveExecutor();
    }

    @Test
    public void shouldHitOpponentBattleShipWhenMissilesLeft() throws Exception {
        Location firstCellLocationShipOne = new Location(3, 2);
        BattleShipCell shipOneCellOne = new BattleShipCell(firstCellLocationShipOne, BattleShipType.P);

        Location secondCellLocationShipOne = new Location(3, 3);
        BattleShipCell shipOneCellTwo = new BattleShipCell(secondCellLocationShipOne, BattleShipType.P);

        Location firstCellLocationShipTwo = new Location(1, 3);
        BattleShipCell shipTwoCellOne = new BattleShipCell(firstCellLocationShipTwo, BattleShipType.Q);

        Location secondCellLocationShipTwo = new Location(1, 4);
        BattleShipCell shipTwoCellTwo = new BattleShipCell(secondCellLocationShipTwo, BattleShipType.Q);

        Location thirdCellLocationShipTwo = new Location(2, 3);
        BattleShipCell shipTwoCellThree = new BattleShipCell(thirdCellLocationShipTwo, BattleShipType.Q);

        Location fourthCellLocationShipTwo = new Location(2, 4);
        BattleShipCell shipTwoCellFour = new BattleShipCell(fourthCellLocationShipTwo, BattleShipType.Q);

        BattleArea battleArea = new BattleArea(5, 5);

        battleArea.populateBattleShipCell(shipOneCellOne);
        battleArea.populateBattleShipCell(shipOneCellTwo);
        battleArea.populateBattleShipCell(shipTwoCellOne);
        battleArea.populateBattleShipCell(shipTwoCellTwo);
        battleArea.populateBattleShipCell(shipTwoCellThree);
        battleArea.populateBattleShipCell(shipTwoCellFour);

        List<String> targets = new ArrayList<String>();
        targets.add("D3");
        targets.add("D3");
        targets.add("C5");
        targets.add("C5");
        targets.add("C5");

        Player player = new Player(1, null);
        Player opponent = new Player(2, battleArea);

        String expectedMessageOne = "Player-1 fires a missile with target D3 which got hit";
        String expectedMessageTwo = "Player-1 fires a missile with target D3 which got miss";
        String expectedMessageThree = "Player-1 fires a missile with target C5 which got hit";
        String expectedMessageFour = "Player-1 fires a missile with target C5 which got miss";

        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add(expectedMessageOne);
        expectedMessages.add(expectedMessageTwo);
        expectedMessages.add(expectedMessageThree);
        expectedMessages.add(expectedMessageThree);
        expectedMessages.add(expectedMessageFour);

        verifyMessages(targets, expectedMessages, player, opponent);
    }

    private void verifyMessages(List<String> targets, List<String> expectedMessages, Player player, Player opponent) throws Exception {
        for (int i = 0; i < targets.size(); i++) {
            Assert.assertEquals(expectedMessages.get(i), unit.executeMoveWhenMissilesLeft(player.getPlayerName(), opponent, targets.get(i)));
        }
    }

    @Test
    public void playerOneShallWinIfOpponentBattleAreaDestroyed() throws Exception {
        Location firstCellLocationShipOne = new Location(3, 2);
        BattleShipCell shipOneCellOne = new BattleShipCell(firstCellLocationShipOne, BattleShipType.P);

        Location secondCellLocationShipOne = new Location(3, 3);
        BattleShipCell shipOneCellTwo = new BattleShipCell(secondCellLocationShipOne, BattleShipType.P);

        BattleArea battleArea = new BattleArea(5, 5);

        battleArea.populateBattleShipCell(shipOneCellOne);
        battleArea.populateBattleShipCell(shipOneCellTwo);

        List<String> targets = new ArrayList<String>();
        targets.add("D3");
        targets.add("D4");
        targets.add("D3");

        Player player = new Player(1, null);
        Player opponent = new Player(2, battleArea);

        String expectedMessageOne = "Player-1 fires a missile with target D3 which got hit";
        String expectedMessageTwo = "Player-1 fires a missile with target D4 which got hit,Player-1 won the battle";
        String expectedMessageThree = "Player-2 battle area already destroyed";

        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add(expectedMessageOne);
        expectedMessages.add(expectedMessageTwo);
        expectedMessages.add(expectedMessageThree);

        verifyMessages(targets, expectedMessages, player, opponent);
    }

    @Test
    public void whenThePlayerOneGoesOutOfMissiles() throws Exception {
        Location firstCellLocationShipOne = new Location(3, 2);
        BattleShipCell shipOneCellOne = new BattleShipCell(firstCellLocationShipOne, BattleShipType.P);

        Location secondCellLocationShipOne = new Location(3, 3);
        BattleShipCell shipOneCellTwo = new BattleShipCell(secondCellLocationShipOne, BattleShipType.P);

        BattleArea battleArea = new BattleArea(5, 5);

        battleArea.populateBattleShipCell(shipOneCellOne);
        battleArea.populateBattleShipCell(shipOneCellTwo);

        Player player = new Player(1, null);
        Player opponent = new Player(2, battleArea);

        String expectedMessage = "Player-1 has no more missiles left to launch";

        Assert.assertEquals(expectedMessage, unit.executeMoveWhenNoMissilesLeft(player.getPlayerName()));
    }

    @Test(expected = Exception.class)
    public void shouldResultInExceptionWhenTargetLocationIsOutsideOfBattleArea() throws Exception {
        Location firstCellLocationShipOne = new Location(3, 2);
        BattleShipCell shipOneCellOne = new BattleShipCell(firstCellLocationShipOne, BattleShipType.P);

        Location secondCellLocationShipOne = new Location(3, 3);
        BattleShipCell shipOneCellTwo = new BattleShipCell(secondCellLocationShipOne, BattleShipType.P);

        BattleArea battleArea = new BattleArea(5, 5);

        battleArea.populateBattleShipCell(shipOneCellOne);
        battleArea.populateBattleShipCell(shipOneCellTwo);

        List<String> targets = new ArrayList<String>();
        targets.add("D3");
        targets.add("K4");


        Player player = new Player(1, null);
        Player opponent = new Player(2, battleArea);

        String expectedMessageOne = "Player-1 fires a missile with target D3 which got hit";

        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add(expectedMessageOne);

        verifyMessages(targets, expectedMessages, player, opponent);
    }

}
