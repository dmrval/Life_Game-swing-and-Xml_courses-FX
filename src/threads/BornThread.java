package threads;


import logic.GameBoard;

public class BornThread implements Runnable {
    private GameBoard gameBoard;
    private Thread bornThread;
    boolean suspendFlag = true;

    BornThread(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        bornThread = new Thread(this, "Born");
        bornThread.start();

    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (suspendFlag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            gameBoard.live();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void mySuspend() {
        suspendFlag = true;
    }

    synchronized void myResume() {
        suspendFlag = false;
        notify();
    }


    public Thread getThread() {
        return bornThread;
    }
}
