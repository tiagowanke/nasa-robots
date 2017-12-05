package robots.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MarsTest {

    @Test
    public void canMoveMustReturnFalse() {

        final Mars mars = new Mars();
        int x, y;

        // x direction greater than allowed
        x = mars.getMaxX() + 1;
        y = 0;
        assertFalse(mars.canMove(x, y));

        // x direction lower than allowed
        x = -1;
        y = 0;
        assertFalse(mars.canMove(x, y));

        // y direction greated than allowed
        x = 0;
        y = mars.getMaxY() + 1;
        assertFalse(mars.canMove(x, y));

        // y direction lower than allowed
        x = 0;
        y = -1;
        assertFalse(mars.canMove(x, y));
    }

    @Test
    public void canMoveMustReturnTrue() {

        final Mars mars = new Mars();
        int x, y;

        // x and y direction on lower limit
        x = 0;
        y = 0;
        assertTrue(mars.canMove(x, y));

        // x direction on highter limit
        x = mars.getMaxX();
        y = 0;
        assertTrue(mars.canMove(x, y));

        // y direction on highter limit
        x = 0;
        y = mars.getMaxY();
        assertTrue(mars.canMove(x, y));

    }

}
