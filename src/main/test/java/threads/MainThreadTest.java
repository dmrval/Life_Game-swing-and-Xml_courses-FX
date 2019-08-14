package threads;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MainThreadTest {

    MainThread mainThread;

    public MainThreadTest() {
        setUp();
    }

    @BeforeEach
    void setUp() {
        mainThread = new MainThread();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getMainThread() {
        assertNotNull(mainThread.getMainThread());
    }

    @Test
    void getBornThread() {
        assertNotNull(mainThread.getBornThread());

    }

    @Test
    void getDiedThread() {
        assertNotNull(mainThread.getDiedThread());
    }
}