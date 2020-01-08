import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class BattleShipGame {
    private ArrayList<Ship> shipList = new ArrayList<>();
    private int score = 0;
    private int[] gridSize = {7, 7};
    private TerminalGraphics gameGraph = new TerminalGraphics(gridSize);
    private ArrayList<String> sortedOnes = new ArrayList<>();

    final protected void setSortedOnes(ArrayList<String> sorted) {this.sortedOnes = sorted; }

    final public void setGridSize(int valX, int valY) {
        if(valX > 0 && valX < 26) {
            this.gridSize[0] = valX;
        }
        if(valY > 0 && valY < Integer.MAX_VALUE) {
            this.gridSize[1] = valY;
        }
        this.gameGraph.initBoard(this.gridSize);
    }

    final public int getNumAlive() {
        int shipCount = 0;
        for (Ship ship : this.shipList) {
            if (ship.checkAlive()) {
                shipCount++;
            }
        }
        return shipCount;
    }

    final public String getGridSize() {
        return this.makeAPos(this.gridSize[0]-1, this.gridSize[1]-1);
    }

    private boolean anyShipAlive() {
        for (Ship ship : this.shipList) {
            if (ship.checkAlive()) {
                return true;
            }
        }
        return false;
    }

    final protected String makeAPos(int x, int y) {
        // make a grid pos string out of a euclidian numeric position
        if(x >= 0 && x < this.gridSize[0] && y >=0 && y < this.gridSize[1]) {
            return (char) (x + 65) + String.valueOf(y);
        }
        return null;
    }

    final protected int[] makeAPos(String position) {
        // make a euclidian numeric position out of a grid pos string
        int intPosX = (int) position.charAt(0) - 65;
        int intPosY = (int) position.charAt(1) - 48;

        if(intPosX < this.gridSize[0] && intPosY < this.gridSize[1]) {
            return new int[]{intPosX, intPosY};
        }
        System.out.println("Invalid input string");
        return null;
    }

    final protected String[] sortPositions(int shipSize) {
        int genSeedX = (int) Math.floor(Math.random() * (this.gridSize[0]-shipSize));
        int genSeedY = (int) Math.floor(Math.random() * (this.gridSize[1]-shipSize));
        byte shipOrient = (byte) Math.round(Math.random());     // 0: vertical, 1: horizontal

        String[] genPositions = new String[shipSize];
        for(int j=0; j<shipSize; ++j) {
            genPositions[j] = makeAPos(genSeedX + (shipOrient*j), genSeedY + ((1-shipOrient)*j));
        }
        return genPositions;
    }

    final protected boolean compareSorted(String[] sorted) {
        int i = 0;
        for(String val : this.sortedOnes) {
            if(sorted[i].equals(val)) {
                return true;
            }
        }
        return false;
    }

    final protected void initializeGame(int numOfShips, int shipSize) {
        for(int i=0; i<numOfShips; ++i) {
            Ship gameShip = new Ship(shipSize);

            String[] genPositions = new String[shipSize];
            boolean sortFlag = true;
            while(sortFlag) {
                 genPositions = this.sortPositions(gameShip.getShipSize());

                 if(!compareSorted(genPositions)) {
                     this.sortedOnes.addAll(Arrays.asList(genPositions));
                     sortFlag = false;
                 }
            }

            gameShip.setPos(genPositions);
            this.shipList.add(gameShip);
        }
    }

    final protected String askPlayer() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a position: ");
        String playerGuess = scan.nextLine().toUpperCase();
        if(playerGuess.length() != 2) {
            System.out.println("Invalid input");
            return null;
        }
        else {
            return playerGuess;
        }
    }

    final public void printGameStatus() {
        System.out.print("\n");
        System.out.println("Alive: "+this.getNumAlive());
        System.out.println("Score: "+this.score);
    }

    final public void playGame(int numOfShips) {
        if(numOfShips > 0) {
            initializeGame(numOfShips, 3);

            //run the game
            while(anyShipAlive()) {
                // graphic interface
                this.gameGraph.printGrid();
                this.printGameStatus();

                String playerGuess = askPlayer();
                if (playerGuess != null) {
                    boolean hitNow = false;
                    int shipNum = 1;
                    for (Ship ship : this.shipList) {
                        byte result = ship.makeAGuess(playerGuess);
                        if (result > 0) {
                            hitNow = true;
                            if (result == 1) {
                                System.out.println("hit on ship " + shipNum);
                                gameGraph.setBoardPos(Objects.requireNonNull(this.makeAPos(playerGuess)), result);
                            } else if (result == 2) {
                                System.out.print("already hit on ship " + shipNum);
                            }
                            else if (result == 3) {
                                System.out.println("hit, ship " + shipNum + " sunk");
                                gameGraph.setBoardPos(Objects.requireNonNull(this.makeAPos(playerGuess)), 1);
                            }
                        }
                        shipNum++;
                    }
                    if (!hitNow) {
                        System.out.println("miss");
                        gameGraph.setBoardPos(Objects.requireNonNull(this.makeAPos(playerGuess)), 3);
                    }
                }
                this.score++;
            }
            System.out.println("End of the game, score: "+this.score);
        }

    }

    public static void main(String[] args) {
        BattleShipGame game = new BattleShipGame();

        game.playGame(3);
    }
}
