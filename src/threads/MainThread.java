package threads;

import logic.GameBoard;

public class MainThread implements Runnable {
    static GameBoard gameBoard;
    Thread thread;
    BornThread bornThread;
    DiedThread diedThread;
    Frame frame;

    public MainThread() {
        gameBoard = new GameBoard();
        thread = new Thread(this, "Optional");
        thread.start();
    }


    @Override
    public void run() {
        frame = new Frame(gameBoard);
        bornThread = new BornThread(gameBoard);
        diedThread = new DiedThread(gameBoard);
    }

    public Frame getFrame() {
        return frame;
    }

    public Thread getThread() {
        return thread;
    }
}
