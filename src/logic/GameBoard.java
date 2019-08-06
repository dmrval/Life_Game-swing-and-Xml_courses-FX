package logic;


import java.util.Random;

public class GameBoard {
    Cell[][] cells;
    static boolean born = true;

    public GameBoard() {
        cells = new Cell[Configuration.maxW][Configuration.maxH];

        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                cells[x][y] = new Cell(x, y, false);
            }
        }
    }

    public synchronized void die() {
        while (born) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        forDie();
        born = !born;
        notify();
    }

    public synchronized void live() {
        while (!born) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        forLive();
        born = !born;
        notify();
    }

    private void forLive() {
        changeState();
    }

    private void changeState() {
        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                int mx = x - 1;
                if (mx < 0) mx = Configuration.maxW - 1;
                int my = y - 1;
                if (my < 0) my = Configuration.maxH - 1;
                int gx = (x + 1) % Configuration.maxW;
                int gy = (y + 1) % Configuration.maxH;
                int neighbor = 0;
                if (cells[mx][my].isAlive()) {
                    neighbor++;
                }
                if (cells[mx][y].isAlive()) {
                    neighbor++;
                }
                if (cells[mx][gy].isAlive()) {
                    neighbor++;
                }
                if (cells[x][my].isAlive()) {
                    neighbor++;
                }
                if (cells[x][gy].isAlive()) {
                    neighbor++;
                }
                if (cells[gx][my].isAlive()) {
                    neighbor++;
                }
                if (cells[gx][y].isAlive()) {
                    neighbor++;
                }
                if (cells[gx][gy].isAlive()) {
                    neighbor++;
                }
                if (neighbor < 2 || neighbor > 3) {
                    cells[x][y].setNextRound(false);
                } else if (neighbor == 2) {
                    cells[x][y].setNextRound(cells[x][y].isAlive());
                } else {
                    cells[x][y].setNextRound(true);
                }
            }
        }

        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                cells[x][y].update();
            }
        }
    }

    private void forDie() {
        changeState();
    }

    public boolean chechLiveOnBoard() {
        for (Cell[] celArr : getCells()) {
            for (Cell current : celArr) {
                if (current.isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setNewSizeBoardRandom() {
        Cell[][] newCells = new Cell[Configuration.maxW][Configuration.maxH];
        Random random = new Random();
        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                newCells[x][y] = new Cell(x, y, random.nextBoolean());
            }
        }
        cells = newCells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setRandomLive() {
        Random random = new Random();
        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                cells[x][y] = new Cell(x, y, random.nextBoolean());
            }
        }
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
