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

    Frame(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        frame = new Thread(this, "Graphic");
        setLayout(new BorderLayout());
        screen = new Screen();
        add(screen, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        frame.start();

    }

    @Override
    public void run() {
        while (true) {
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


}
