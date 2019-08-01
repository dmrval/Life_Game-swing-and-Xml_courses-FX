import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements Runnable {

    JFrame jFrame;
    Thread currThread;

    Molecule[][] molecules;

    public Window() {
        currThread = new Thread(this);
        currThread.start();
    }

    @Override
    public void run() {
        initialComponents();
        initialMolecules();
        initialTimer();
    }

    private void initialTimer() {
        TimeListener timerListener = new TimeListener();
        Timer timer = new Timer(Configuration.SLEEPMILS, timerListener);
        timer.start();
    }


    private void initialComponents() {
        jFrame = new JFrame();
        jFrame.getContentPane().setLayout(null);
        jFrame.setSize(Configuration.SIZE * Configuration.WIDTH,
                Configuration.SIZE * Configuration.HEIGHT);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Game of Life");
        jFrame.setVisible(true);
    }


    private void initialMolecules() {
        molecules = new Molecule[Configuration.WIDTH][Configuration.HEIGHT];
        for (int i = 0; i < Configuration.WIDTH; i++) {
            for (int j = 0; j < Configuration.HEIGHT; j++) {
                Molecule tmp = molecules[i][j] = new Molecule(i, j);
                jFrame.add(tmp);
            }
        }
        for (int i = 0; i < Configuration.WIDTH; i++) {
            for (int j = 0; j < Configuration.HEIGHT; j++) {
                for (int sx = -1; sx <= +1; sx++) {
                    for (int sy = -1; sy <= +1; sy++) {
                        if (!(sx == 0 && sy == 0)) {
                            molecules[i][j].getCell().addNear(molecules
                                    [(i + sx + Configuration.WIDTH) % Configuration.WIDTH]
                                    [(j + sy + Configuration.HEIGHT) % Configuration.HEIGHT].getCell()
                            );
                        }
                    }
                }
            }
        }
        for (int i = 10; i < 15; i++) {
            molecules[i][10].getCell().currStatus.setCurrentStatus("live");
            molecules[i][10].setColor();
        }
    }

    private class TimeListener implements ActionListener {
        boolean flag = false;

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            flag = !flag;
            for (int i = 0; i < Configuration.WIDTH; i++) {
                for (int j = 0; j < Configuration.HEIGHT; j++) {
                    if (flag) {
                        molecules[i][j].level1();
                    } else {
                        molecules[i][j].level2();
                    }
                }
            }
        }
    }
}
