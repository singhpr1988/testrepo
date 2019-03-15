import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BattleGameInputPreparator {

    public List<BattleGameInput> readFromFileAndPrepareInput() {
        List<BattleGameInput> battleGameInputs = new ArrayList<BattleGameInput>();
        try {
            int widthOfBattleArea = 0;
            int heightOfBattleArea = 0;
            int numberOfBattleShipEachPlayerHas = 0;

            BattleArea battleAreaOne = null;
            BattleArea battleAreaTwo = null;

            BufferedReader br = new BufferedReader(new FileReader("Input"));
            String line = "";
            int lineNumber = 1;

            List<String> targetsForPlayerOne = new ArrayList<String>();
            List<String> targetsForPlayerTwo = new ArrayList<String>();

            while ((line = br.readLine()) != null) {
                if (lineNumber == 1) {
                    String arr[] = line.split(" ");
                    widthOfBattleArea = Integer.parseInt(arr[0].trim());
                    heightOfBattleArea = ((int)arr[1].charAt(0)) - 64;
                    battleAreaOne = new BattleArea(widthOfBattleArea, heightOfBattleArea);
                    battleAreaTwo = new BattleArea(widthOfBattleArea, heightOfBattleArea);
                    lineNumber++;
                } else if (lineNumber == 2) {
                    numberOfBattleShipEachPlayerHas = Integer.parseInt(line);
                    lineNumber++;
                } else if (lineNumber >= 3 && lineNumber <= 3 + numberOfBattleShipEachPlayerHas - 1) {
                    String arr[] = line.split(" ");
                    BattleShipType battleShipType = BattleShipType.valueOf(arr[0]);
                    int widthOfBattleShip = Integer.parseInt(arr[1]);
                    int heightOfBattleShip = Integer.parseInt(arr[2]);

                    List<BattleShipCell> battleShipCellsPlayerOne = prepareBattleShipCells(widthOfBattleShip, heightOfBattleShip, battleShipType, arr[3]);
                    List<BattleShipCell> battleShipCellsPlayerTwo = prepareBattleShipCells(widthOfBattleShip, heightOfBattleShip, battleShipType, arr[4]);
                    populateBattleShipCells(battleAreaOne, battleShipCellsPlayerOne);
                    populateBattleShipCells(battleAreaTwo, battleShipCellsPlayerTwo);
                    lineNumber++;
                } else if (lineNumber == 3 + numberOfBattleShipEachPlayerHas) {
                    populateTargets(line.split(" "), targetsForPlayerOne);
                    lineNumber++;
                } else {
                    populateTargets(line.split(" "), targetsForPlayerTwo);
                }
            }

            Player playerOne = new Player(1, battleAreaOne);
            Player playerTwo = new Player(2, battleAreaTwo);

            BattleGameInput battleGameInputOne = new BattleGameInput(playerOne.getPlayerName(), targetsForPlayerOne, playerTwo);
            BattleGameInput battleGameInputTwo = new BattleGameInput(playerTwo.getPlayerName(), targetsForPlayerTwo, playerOne);

            battleGameInputs.add(battleGameInputOne);
            battleGameInputs.add(battleGameInputTwo);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return battleGameInputs;
    }

    private void populateBattleShipCells(BattleArea battleArea, List<BattleShipCell> battleShipCells) {
        for (BattleShipCell battleShipCell : battleShipCells) {
            battleArea.populateBattleShipCell(battleShipCell);
        }
    }

    private List<BattleShipCell> prepareBattleShipCells(int widthOfBattleArea, int heightOfBattleArea, BattleShipType battleShipType, String startingLocation) {
        List<BattleShipCell> battleShipCells = new ArrayList<BattleShipCell>();
        char arr[] = startingLocation.toCharArray();
        int x = (int)arr[0] - 65;
        int y = Character.getNumericValue(arr[1]) - 1;

        for (int i = 1, a = x; i <= heightOfBattleArea; i++, a++) {
            for (int j = 1, b = y; j <= widthOfBattleArea; j++, b++) {
                Location location = new Location(a, b);
                BattleShipCell battleShipCell = new BattleShipCell(location, battleShipType);
                battleShipCells.add(battleShipCell);
            }
        }
        return battleShipCells;
    }

    private void populateTargets(String[] input, List<String> targets) {
        for (String target : input) {
            targets.add(target);
        }
    }
}
