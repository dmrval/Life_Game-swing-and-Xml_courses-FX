package threads;

import logic.GameBoard;


public class MainThread implements Runnable {
    static GameBoard gameBoard;
    private Thread mainThread;
    private BornThread bornThread;
    private DiedThread diedThread;
    private Frame frame;

    public MainThread() {
        gameBoard = new GameBoard();
        mainThread = new Thread(this, "Optional");
        mainThread.start();
    }

    @Override
    public void run() {
        frame = new Frame(gameBoard, this);
        bornThread = new BornThread(gameBoard);
        diedThread = new DiedThread(gameBoard);
    }

    public Frame getFrame() {
        return frame;
    }


    public Thread getMainThread() {
        return mainThread;
    }

    public BornThread getBornThread() {
        return bornThread;
    }

    public DiedThread getDiedThread() {
        return diedThread;
    }

    public void setBornThread(BornThread bornThread) {
        this.bornThread = bornThread;
    }

    public void setDiedThread(DiedThread diedThread) {
        this.diedThread = diedThread;
    }
}

