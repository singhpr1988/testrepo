
import java.util.List;

public class BattleProcessor {

    public static void main(String ar[]) {
        BattleProcessor battleProcessor = new BattleProcessor();
        battleProcessor.proceedWithTheBattle(new BattleExecutor(), new MoveExecutor(), new BattleGameInputPreparator());
    }

    public void proceedWithTheBattle(BattleExecutor battleExecutor, MoveExecutor moveExecutor, BattleGameInputPreparator battleGameInputPreparator) {
        List<BattleGameInput> battleGameInputs = battleGameInputPreparator.readFromFileAndPrepareInput();
        try {
            List<String> resultMessages = battleExecutor.executeBattle(battleGameInputs.get(0), battleGameInputs.get(1), moveExecutor);
            for (String message : resultMessages) {
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
