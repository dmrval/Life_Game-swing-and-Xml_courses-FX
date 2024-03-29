package threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void run() {
        MainThread mainThread = new MainThread();
        mainThread.run();
        assertNotNull(mainThread.getBornThread());
        assertNotNull(mainThread.getDiedThread());
        assertNotNull(mainThread.getFrame());
        assertNotNull(mainThread.getMainThread());
    }

}