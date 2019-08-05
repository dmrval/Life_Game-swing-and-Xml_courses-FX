package threads;


import logic.GameBoard;

public class BornThread implements Runnable {
    private GameBoard gameBoard;
    private Thread bornThread;

    BornThread(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        bornThread = new Thread(this, "Born");
        bornThread.start();

    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread());
            gameBoard.live();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Thread getBornThread() {
        return bornThread;
    }
}
