import javax.swing.*;
import java.awt.*;

public class Molecule extends JPanel {

    private Cell cell;

    public Molecule(int x, int y) {
        super();
        this.cell = new Cell();
        setBounds(x * Configuration.SIZE,
                y * Configuration.SIZE,
                Configuration.SIZE,
                Configuration.SIZE);
        setBackground(Configuration.getColor("no"));
    }

    public void initCells(int x, int y) {

    }

    public Cell getCell() {
        return cell;
    }

    public void setColor() {
        setBackground(Configuration.getColor(cell.currStatus.getCurrentStatus()));
    }


    void level1() {
        cell.level1();
        setColor();
    }


    void level2() {
        cell.level2();
        setColor();
    }
}
