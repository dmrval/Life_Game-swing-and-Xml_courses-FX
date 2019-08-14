package logic;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import threads.Frame;
import threads.MainThread;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class GameBoardTest {

    GameBoard gameBoard;

    GameBoardTest() {
        setUp();
    }

    @Before
    public void setUp() {
        gameBoard = new GameBoard(new Frame(new MainThread()));
    }

    @Test
    void die() {
    }

    @Test
    void live() {
    }

    @Test
    void checkLiveOnBoard() {
        assertEquals(gameBoard.checkLiveOnBoard(), anyBoolean());
    }

    @Test
    void setNewSizeBoardRandomLive() {
        gameBoard.setNewSizeBoardRandomLive();
        assertNotNull(gameBoard.getCells());
    }

    @Test
    void getCells() {
        assertNotNull(gameBoard.getCells());
    }

    @Test
    void setRandomLive() {
        gameBoard.setRandomLive();
        assertNotNull(gameBoard.getCells());
    }
}