package threads;

import logic.GameBoard;

public class DiedThread implements Runnable {
    private GameBoard gameBoard;
    private Thread diedThread;
    boolean suspendFlag = true;

    DiedThread(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        diedThread = new Thread(this, "Died");
        diedThread.start();
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
            System.out.println(Thread.currentThread());
            gameBoard.die();
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
        return diedThread;
    }
}
