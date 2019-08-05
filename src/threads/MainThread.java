package threads;

import logic.GameBoard;

public class MainThread implements Runnable {
    private static GameBoard gameBoard;
    private Thread thread;
    private BornThread bornThread;
    private DiedThread diedThread;
    private Frame frame;

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
