package threads;


import logic.GameBoard;

public class BornThread implements Runnable {
    GameBoard gameBoard;
    Thread bornThread;

    public BornThread(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        bornThread = new Thread(this, "Born");
        bornThread.start();

    }

    @Override
    public void run() {
        while (true) {
            gameBoard.live();
            System.out.println(Thread.currentThread());

            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
