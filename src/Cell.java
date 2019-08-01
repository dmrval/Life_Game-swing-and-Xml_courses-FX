import java.util.ArrayList;
import java.util.List;

public class Cell {
    Status currStatus;
    List<Cell> near;

    public Cell() {
        this.currStatus = new Status();
        near = new ArrayList<>();
    }


    void addNear(Cell cell) {
        near.add(cell);
    }

    void level1() {
        currStatus = currStatus.level1(countCells());
    }

    private int countCells() {
        int count = 0;
        for (Cell c : near) {
            if (c.currStatus.isAlive()) {
                count++;
            }
        }
        return count;
    }


    void level2() {
        currStatus.level2();
    }
}
