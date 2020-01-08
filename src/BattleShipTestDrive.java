import java.util.ArrayList;
import java.util.Objects;

public class BattleShipTestDrive extends BattleShipGame{
    BattleShipGame game = new BattleShipGame();

    private void TestMakeAPos() {
        this.game.setGridSize(10, 10);  //max is J9
        System.out.println("Grid size: " + this.game.getGridSize());
        assert Objects.requireNonNull(this.game.makeAPos(7, 7)).equals("H7");
        assert Objects.requireNonNull(this.game.makeAPos(0, 0)).equals("A0");
        assert this.game.makeAPos(26, 0) == null;
        assert this.game.makeAPos(0, 11) == null;

        int[] pos1 = this.game.makeAPos("B3");
        assert pos1 != null;
        assert pos1[0] == 1;
        assert pos1[1] == 3;
    }

    private void TestInitializeGame() {
        this.game.setGridSize(10, 10);
        System.out.println("Grid size: " + this.game.getGridSize());
        this.game.initializeGame(1, 0);
        assert this.game.getNumAlive() == 3;
        this.game.initializeGame(3, 3);
        assert this.game.getNumAlive() == 3;
    }

    private void TestAskPlayer() {
        this.game.askPlayer();
    }

    private void TestPlayGame() {
        this.game.setGridSize(10, 10);
        System.out.println("Grid size: " + this.game.getGridSize());
        this.game.playGame(3);
    }

    private void TestCompareSorted() {
        String[] testSorted = {"A1", "A2", "A3"};
        ArrayList<String> sortedList = new ArrayList<>();
        sortedList.add("A2");
        sortedList.add("B0");
        this.game.setSortedOnes(sortedList);
        assert this.game.compareSorted(testSorted);
        System.out.println("passed!");
    }

    public static void main(String[] args) {
        BattleShipTestDrive driver = new BattleShipTestDrive();
        //driver.TestMakeAPos();
        //driver.TestInitializeGame();
        //driver.TestAskPlayer();
        //driver.TestPlayGame();
        driver.TestCompareSorted();
    }
}
