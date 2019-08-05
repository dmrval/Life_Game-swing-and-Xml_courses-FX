package threads;

import logic.GameBoard;

public class DiedThread implements Runnable {
    GameBoard gameBoard;
    Thread diedThread;

    public DiedThread(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        diedThread = new Thread(this, "Died");
        diedThread.start();
    }

    @Override
    public void run() {
        while (true) {
            gameBoard.die();
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(80);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
