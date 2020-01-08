public class TerminalGraphics {
    private int[][] Board;

    public TerminalGraphics(int[] gridSize) {
        this.initBoard(gridSize);
    }

    final public void initBoard(int[] gridSize) {
        if (gridSize.length == 2) {
            this.Board = new int[gridSize[0]][gridSize[1]];
        }
        else {
            System.out.println("Invalid argument gridSize");
        }
    }

    private char mapBoard2char(int boardInt) {
        // 0: ?
        if(boardInt == 0) {
            return '?';
        }
        else if(boardInt == 1 || boardInt == 2) {
            return 'X';
        }
        else if(boardInt == 3) {
            return 'O';
        }
        else {
            return (char) boardInt;
        }
    }

    private char num2Letter(int num) {
        return (char) (num + 65);
    }

    final public void setBoardPos(int[] position, int val){
        if (position.length == 2) {
            this.Board[position[0]][position[1]] = val;
        }
        else {
            System.out.println("Invalid argument position");
        }

    }
    public void printGrid() {
        for (int x = 0; x < this.Board.length+1; ++x) {
            System.out.println();
            for (int y = 0; y < this.Board[0].length+1; ++y) {
                if(x < this.Board.length && y != 0) {
                    System.out.print("[" + this.mapBoard2char(this.Board[x][y-1]) + "]" + "\t");
                }
                else if(y == 0 && x < this.Board.length) {
                    System.out.print(" "+ this.num2Letter(x) + " " + "\t");
                }
                else if (x == this.Board.length && y != 0){
                    System.out.print(" "+ (y-1) + " " + "\t");
                }
                else {
                    System.out.print("   " + "\t");
                }

            }
        }
    }

    public static void main(String[] args) {
        int[] gridsize1 = {3, 3};
        TerminalGraphics term = new TerminalGraphics(gridsize1);
        term.printGrid();
        int[] gridsize2 = {7, 7};
        term.initBoard(gridsize2);
        term.printGrid();
    }
}
