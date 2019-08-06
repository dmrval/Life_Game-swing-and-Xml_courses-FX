package threads;


public class DiedThread implements Runnable {
    private Thread diedThread;
    private boolean suspendFlag = true;

    DiedThread() {
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
            MainThread.gameBoard.die();
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
