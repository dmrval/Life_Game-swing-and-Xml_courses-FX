package logic;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CellTest {

    @Mock
    Cell testCell;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before ");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After ");
    }

    @Test
    void setNextRound() {
        testCell = mock(Cell.class);
        doNothing().when(testCell).setNextRound(any(Boolean.class));
    }

    @Test
    void update() {
        Cell cell = new Cell(0, 0, false);
        cell.setNextRound(true);
        cell.update();
        assertEquals(cell.isAlive(), true);
    }


//    @Test
//    void setAliveTrue() {
//        Cell cell = new Cell(1, 1, true);
//        assertTrue(cell.isAlive());
//    }


    @Test
    void setAliveFalse() {
        Cell cell = mock(Cell.class);
        cell.setAlive(false);
        when(cell.isAlive()).thenReturn(false);
    }

    @Test
    void callMethods() {
        Cell cell = mock(Cell.class);
        cell.update();
        verify(cell).update();
    }

    @Test
    void draw() {
        Cell cell = mock(Cell.class);
        doNothing().when(cell).draw(any(Graphics.class));
    }

    @Test
    void setAlive() {
        Cell cell = mock(Cell.class);
        cell.setAlive(true);
        doNothing().when(cell).setAlive(any(Boolean.class));
        verify(cell).setAlive(true);
    }

    @Test
    void isAlive() {
        testCell = mock(Cell.class);
        when(testCell.isAlive()).thenReturn(any(Boolean.class));
    }


}