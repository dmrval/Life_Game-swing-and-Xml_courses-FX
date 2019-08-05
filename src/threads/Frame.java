package threads;

import logic.Cell;
import logic.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame implements Runnable {
    private GameBoard gameBoard;
    private Thread frame;
    private Screen screen;
    private MainThread mainThread;
    private boolean suspendFlag = true;

    Frame(GameBoard gameBoard, MainThread mainThread) {
        this.mainThread = mainThread;
        this.gameBoard = gameBoard;
        frame = new Thread(this, "Graphic");
        setLayout(new BorderLayout());
        screen = new Screen();
        add(screen, BorderLayout.CENTER);

        JPanel jPanel = new JPanel();
        jPanel.setSize(50, 50);
        jPanel.setBackground(Color.GRAY);

        add(jPanel, BorderLayout.SOUTH);

        Button startButton = new Button();
        startButton.setSize(50, 50);
        startButton.setLabel("Start");
        startButton.addActionListener(new StartButtonListener());
        jPanel.add(startButton);

        Button stopButton = new Button();
        stopButton.setSize(50, 50);
        stopButton.setLabel("Stop");
        stopButton.addActionListener(new StopButtonListener());
        jPanel.add(stopButton);

        Button clearButton = new Button();
        clearButton.setSize(50, 50);
        clearButton.setLabel("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        jPanel.add(clearButton);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(gameBoard.getWidth() * Cell.SIZE,
                gameBoard.getHeight() * Cell.SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
        frame.start();

    }

    void mySuspend() {
        suspendFlag = true;
    }

    synchronized void myResume() {
        suspendFlag = false;
        notify();
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
            repaint();
        }
    }

    private class Screen extends JLabel {
        private Screen() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() / 10;
                    int y = e.getY() / 10;
                    if (x > gameBoard.getWidth() || y > gameBoard.getHeight()) {
                        return;
                    }
                    if (gameBoard.getCells()[x][y].isAlive()) {
                        gameBoard.getCells()[x][y].setAlive(false);
                    } else {
                        gameBoard.getCells()[x][y].setAlive(true);
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            for (Cell[] cell : gameBoard.getCells()) {
                for (Cell c : cell) {
                    c.draw(g);
                }
            }
        }
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boolean newGame = gameBoard.chechLiveOnBoard();
            if (newGame) {
                gameBoard.setRandomLive();
                repaint();
                mainThread.getDiedThread().myResume();
                mainThread.getBornThread().myResume();
            }
            mainThread.getDiedThread().myResume();
            mainThread.getBornThread().myResume();
            mainThread.getFrame().myResume();
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            mainThread.getDiedThread().mySuspend();
            mainThread.getBornThread().mySuspend();
            mainThread.getFrame().mySuspend();
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for (Cell[] celArr : gameBoard.getCells()) {
                for (Cell current : celArr) {
                    current.setAlive(false);
                }
            }
            repaint();
        }
    }

    public void setFrame(Thread frame) {
        this.frame = frame;
    }
}
