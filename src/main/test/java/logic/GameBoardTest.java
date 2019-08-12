package logic;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import threads.Frame;
import threads.MainThread;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameBoardTest {


    @Mock
    GameBoard gameBoard;


    @Test
    void die() {
    }

    @Test
    void live() {
    }

    @Test
    void checkLiveOnBoard() {
        GameBoard tmp = new GameBoard(mock(Frame.class));
        Cell[][] cells = new Cell[Configuration.maxW][Configuration.maxH];
        Random random = new Random();
        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                cells[x][y] = new Cell(x, y, random.nextBoolean());
            }
        }
        tmp.setCells(cells);
        assertEquals(tmp.checkLiveOnBoard(), true || false);
    }

    @Test
    void setNewSizeBoardRandomLive() {
        gameBoard = mock(GameBoard.class);
        Cell[][] newCells = new Cell[Configuration.maxW][Configuration.maxH];
        Random random = new Random();
        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                newCells[x][y] = new Cell(x, y, random.nextBoolean());
            }
        }
        assertNotNull(gameBoard);
        when(gameBoard.getCells()).thenReturn(newCells);
    }

    @Test
    void getCells() {
        Frame frame = mock(Frame.class);
        MainThread mainThread = mock(MainThread.class);
        gameBoard = new GameBoard(frame);
        assertNotNull(gameBoard.getCells());
    }

    @Test
    void setRandomLive() {
        gameBoard = mock(GameBoard.class);
        Cell[][] newCells = new Cell[Configuration.maxW][Configuration.maxH];
        gameBoard.setCells(newCells);
        verify(gameBoard).setCells(newCells);
        Random random = new Random();
        for (int x = 0; x < Configuration.maxW; x++) {
            for (int y = 0; y < Configuration.maxH; y++) {
                newCells[x][y] = new Cell(x, y, random.nextBoolean());
            }
        }
        when(gameBoard.getCells()).thenReturn(newCells);
    }
}