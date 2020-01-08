public class BattleShipTestDrive extends BattleShipGame{
    BattleShipGame game = new BattleShipGame();

    private void TestMakeAPos() {
        this.game.setGridSize(10, 10);  //max is J9
        System.out.println("Grid size: " + this.game.getGridSize());
        assert this.game.makeAPos(7, 7).equals("H7");
        assert this.game.makeAPos(0, 0).equals("A0");
        assert this.game.makeAPos(26, 0) == null;
        assert this.game.makeAPos(0, 11) == null;

        int[] pos1 = this.game.makeAPos("B3");
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

    public static void main(String[] args) {
        BattleShipTestDrive driver = new BattleShipTestDrive();
        driver.TestMakeAPos();
        //driver.TestInitializeGame();
        //driver.TestAskPlayer();
        //driver.TestPlayGame();
    }
}
