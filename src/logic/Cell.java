package logic;

import java.awt.*;

public class Cell {
    public static final int SIZE = 10;

    private int x;
    private int y;

    private boolean nextRound;
    private boolean isAlive;

    Cell(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.isAlive = alive;
    }

    void setNextRound(boolean nextRound) {
        this.nextRound = nextRound;
    }

    void update() {
        isAlive = nextRound;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void draw(Graphics g) {
        if (isAlive) {
            g.setColor(Color.BLACK);
            g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
        } else {
            g.setColor(Color.pink);
            g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);

        }
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
