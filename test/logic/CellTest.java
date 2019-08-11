package logic;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CellTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before CalculatorTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After CalculatorTest.class");
    }

    @Test
    void setNextRound() {
    }

    @Test
    void update() {
        Cell cell = new Cell(0, 0, false);
        cell.setNextRound(true);
        cell.update();
        assertEquals(cell.isAlive(), true);
    }

    //sanarq

    @Test
    void setAliveTrue() {
        Cell cell = new Cell(1, 1, true);
        assertTrue(cell.isAlive());
    }


    @Test
    void setAliveFalse() {
        Cell cell = mock(Cell.class);
        cell.setAlive(false);
        when(cell.isAlive()).thenReturn(false);
    }

    @Test
    void callMethods() {
        Cell cell = mock(Cell.class);
        verify(cell).setNextRound(true);
    }

    @Test
    void draw() {
    }

    @Test
    void setAlive() {
    }
}