package threads;


public class BornThread implements Runnable {
    private Thread bornThread;
    boolean suspendFlag = true;

    BornThread() {
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
            MainThread.gameBoard.live();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mySuspend() {
        suspendFlag = true;
    }

    public synchronized void myResume() {
        suspendFlag = false;
        notify();
    }

}
