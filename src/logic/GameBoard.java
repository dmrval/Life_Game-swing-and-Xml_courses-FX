package logic;

import java.util.Random;

public class GameBoard {
    private Cell[][] cells;
    private int width;
    private int height;
    static boolean born = true;

    public GameBoard() {
        width = 70;
        height = 70;
        cells = new Cell[width][height];

        Random random = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y, random.nextBoolean());
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
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int mx = x - 1;
                if (mx < 0) mx = width - 1;
                int my = y - 1;
                if (my < 0) my = height - 1;
                int gx = (x + 1) % width;
                int gy = (y + 1) % height;
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

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
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
                    return false;
                }
            }
        }
        return true;
    }


    public Cell[][] getCells() {
        return cells;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void setRandomLive() {
        Random random = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y, random.nextBoolean());
            }
        }
    }
}
