package logic;


import threads.MainThread;

public class Main {
    public static void main(String[] args) {
        MainThread mainThread = new MainThread();
        try {
            mainThread.getMainThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
