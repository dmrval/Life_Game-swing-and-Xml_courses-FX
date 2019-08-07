package threads;


public class BornThread implements Runnable {
    private boolean suspendFlag = true;

    BornThread() {
        Thread bornThread = new Thread(this, "Born");
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
            MainThread.gameBoard.live();
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
