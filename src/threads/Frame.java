package threads;

import logic.Cell;
import logic.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame implements Runnable {
    private Thread frame;
    private Screen screen;
    private MainThread mainThread;
    JTextArea widthTextField;
    JTextArea heightTextField;
    private JTextArea timeTextField;
    private JTextArea ageTextField;
    private boolean suspendFlag = true;
    private Button startButton;
    private Button stopButton;
    private Button clearButton;
    private Timer timer;
    private int ageCount = -1;

    Frame(MainThread mainThread) {
        this.mainThread = mainThread;
        frame = new Thread(this, "Graphic");
        setName("Game Form");
        setTitle("Life Game");
        setLayout(new BorderLayout());
        screen = new Screen();
        add(screen, BorderLayout.CENTER);

        JPanel jPanelButtons = new JPanel();
        jPanelButtons.setSize(50, 50);
        jPanelButtons.setBackground(Color.GRAY);

        add(jPanelButtons, BorderLayout.SOUTH);

        startButton = new Button();
        startButton.setSize(50, 50);
        startButton.setLabel("   Start   ");
        startButton.addActionListener(new StartButtonListener(this));
        jPanelButtons.add(startButton);

        stopButton = new Button();
        stopButton.setSize(50, 50);
        stopButton.setLabel("Stop");
        stopButton.addActionListener(new StopButtonListener());
        jPanelButtons.add(stopButton);

        clearButton = new Button();
        clearButton.setSize(50, 50);
        clearButton.setLabel("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        jPanelButtons.add(clearButton);

        JPanel upPanel = new JPanel();
        upPanel.setBackground(Color.GRAY);
        upPanel.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        upPanel.add(jPanel, BorderLayout.SOUTH);
        jPanel.setBackground(Color.GRAY);

        add(upPanel, BorderLayout.NORTH);


        Label labeltime = new Label();
        labeltime.setText("Time");
        jPanel.add(labeltime);

        timeTextField = new JTextArea(1, 10);
        timeTextField.setText("5");
        jPanel.add(timeTextField);

        Label labelWidth = new Label();
        labelWidth.setText("Width(70-200 cell)");

        jPanel.add(labelWidth);

        widthTextField = new JTextArea(1, 10);
        widthTextField.setText(Configuration.w + "");
        jPanel.add(widthTextField);

        Label labelHeight = new Label();
        labelHeight.setText("Height(20-200 cell)");
        jPanel.add(labelHeight);

        heightTextField = new JTextArea(1, 10);
        heightTextField.setText(Configuration.h + "");
        jPanel.add(heightTextField);

        JPanel advanced = new JPanel();
        advanced.setBackground(Color.GRAY);

        upPanel.add(advanced, BorderLayout.NORTH);

        Label labelAge = new Label();
        labelAge.setText("Age");

        advanced.add(labelAge);

        ageTextField = new JTextArea(1, 10);
        ageTextField.setText("1000");
        advanced.add(ageTextField);

        jPanel.setSize(50, 50);
        Checkbox checkbox = new Checkbox("Expand?");
        advanced.add(checkbox);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(Configuration.w * Cell.SIZE,
                Configuration.h * Cell.SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        frame.start();
    }

    private void mySuspend() {
        suspendFlag = true;
    }

    private synchronized void myResume() {
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
            if (ageCount != 0) {
                repaint();
            } else {
                suspendThreads();
            }
            ageTextField.setText(ageCount+"");

        }
    }

    private class Screen extends JLabel {
        private Screen() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() / 10;
                    int y = e.getY() / 10;
                    if (x > Configuration.w || y > Configuration.h) {
                        return;
                    }
                    if (MainThread.gameBoard.getCells()[x][y].isAlive()) {
                        MainThread.gameBoard.getCells()[x][y].setAlive(false);
                    } else {
                        MainThread.gameBoard.getCells()[x][y].setAlive(true);
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            for (Cell[] cell : MainThread.gameBoard.getCells()) {
                for (Cell c : cell) {
                    c.draw(g);
                }
            }
        }
    }

    private class StartButtonListener implements ActionListener {
        Frame frame;

        StartButtonListener(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (inputCorrect(ageTextField.getText())) {
                ageCount = Integer.parseInt(ageTextField.getText());
            }
            if (inputCorrect(timeTextField.getText())) {
                timer = new Timer(1000, new TimerTick(Integer.parseInt(timeTextField.getText())));
                timer.start();
            } else if (timeTextField.getText().isEmpty() || timeTextField.getText().equals("0")) {
                timer = new Timer(1000, new TimerTick(999));

            } else {
                timer = new Timer(1000, new TimerTick(5));
                timer.start();
            }
            if (inputCorrect(widthTextField.getText()) && inputCorrect(heightTextField.getText())) {
                startButtonActivity();
                if (!MainThread.gameBoard.chechLiveOnBoard()) {
                    setConfiguration();
                    MainThread.gameBoard.setNewSizeBoardRandomLive();
                    frame.setSize(Configuration.w * Cell.SIZE, Configuration.h * Cell.SIZE);
                    MainThread.gameBoard.setRandomLive();
                } else {
                    setConfiguration();
                    frame.setSize(Configuration.w * Cell.SIZE, Configuration.h * Cell.SIZE);
                }
                resumeThreads();
            } else {
                startButtonActivity();
                if (!MainThread.gameBoard.chechLiveOnBoard()) {
                    setDefaultConfiguration();
                    MainThread.gameBoard.setNewSizeBoardRandomLive();
                    frame.setSize(Configuration.w * Cell.SIZE, Configuration.h * Cell.SIZE);
                    MainThread.gameBoard.setRandomLive();
                } else {
                    setDefaultConfiguration();
                    frame.setSize(Configuration.w * Cell.SIZE, Configuration.h * Cell.SIZE);
                }
                resumeThreads();
            }
        }

        void setConfiguration() {
            Configuration.w = Integer.parseInt(widthTextField.getText());
            Configuration.h = Integer.parseInt(heightTextField.getText());
            if (Configuration.w < 70 || Configuration.w > 200) {
                widthTextField.setText("70");
                Configuration.w = 70;
            }
            if (Configuration.h < 20 || Configuration.h > 200) {
                heightTextField.setText("70");
                Configuration.h = 70;
            }
        }

        void setDefaultConfiguration() {
            Configuration.w = 70;
            Configuration.h = 70;
            widthTextField.setText("70");
            heightTextField.setText("70");
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            timer.stop();
            suspendThreads();
            stopButtonActivity();
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for (Cell[] celArr : MainThread.gameBoard.getCells()) {
                for (Cell current : celArr) {
                    current.setAlive(false);
                }
            }
            repaint();
        }
    }

    private boolean inputCorrect(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void resumeThreads() {
        mainThread.getDiedThread().myResume();
        mainThread.getBornThread().myResume();
        mainThread.getFrame().myResume();
    }

    private void suspendThreads() {
        mainThread.getDiedThread().mySuspend();
        mainThread.getBornThread().mySuspend();
        mainThread.getFrame().mySuspend();
    }


    private class TimerTick implements ActionListener {
        int countdown;

        TimerTick(int countdown) {
            this.countdown = countdown;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (countdown == 0) {
                timer.stop();
                suspendThreads();
                stopButtonActivity();
                timeTextField.setText("5");
                return;
            }
            countdown--;
            timeTextField.setText(String.valueOf(countdown));
        }
    }


    private void stopButtonActivity() {
        startButton.setLabel("   Start   ");
        startButton.setEnabled(true);
        clearButton.setEnabled(true);
    }

    private void startButtonActivity() {
        startButton.setLabel("Running");
        startButton.setEnabled(false);
        clearButton.setEnabled(false);
    }

    public void minusAge() {
        ageCount--;
    }


}
