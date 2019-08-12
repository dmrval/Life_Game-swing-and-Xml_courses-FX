package threads;

public class DiedThread implements Runnable {
    private boolean suspendFlag = true;

    DiedThread() {
        Thread diedThread = new Thread(this, "Died");
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
            MainThread.gameBoard.die();
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



}
