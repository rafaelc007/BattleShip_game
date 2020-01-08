import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    private String[] position;  // array storing ship positions
    private ArrayList<String> remainHits = new ArrayList<String>();   // list storing positions missing hit
    private int shipSize = 2;   // default ship size
    private boolean isAlive = true;

    public Ship(int size) {
        if(size > 0) {
            this.setShipSize(size);
        }
    }

    final public int getShipSize() {return this.shipSize; }

    final public void setShipSize(int newSize) {
        if(newSize > 0) {
            this.shipSize = newSize;
        }
        else {
            System.out.println("Warning: shipSize value must be greater than zero.");
        }
    }

    private boolean checkPos(String pos) {
        for(String val : this.position) {
            if(val.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    final public boolean checkAlive() {return this.isAlive;}

    final public void setPos(String[] input) {
        if(input != null) {
            if (input.length == this.shipSize) {
                this.position = input;
                this.remainHits.addAll(Arrays.asList(input));
                return;
            }
            else {
                System.out.println("Argument length is wrong value, expected: " + this.shipSize);
            }
        }
        else {
            System.out.println("Argument passed is null");
        }
        System.out.println("No actions taken");
    }

    private boolean checkSunk() {
        if (this.remainHits.isEmpty()) {
            this.isAlive = false;
            return true;
        }
        return false;
    }

    final public byte makeAGuess(String userGuess) {
        // 0: miss, 1: hit, 2: already hit
        byte result = 0;
        if(userGuess != null && this.isAlive) {
            if(this.remainHits.contains(userGuess)) {
                this.remainHits.remove(userGuess);
                result = 1;
            }
            else if(this.checkPos(userGuess)) {
                result = 2;
            }
        }
        if (this.checkSunk()) {
            result = 3;
        }
        return result;
    }
}
