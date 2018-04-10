package robots.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MarsTest {

    private static final short MAX_X = 4;
    private static final short MAX_Y = 4;

    @Test
    public void canMoveMustReturnFalse() {

        final Mars mars = new Mars();

        // x direction greater than allowed
        assertFalse(mars.canMove(MAX_X + 1, 0));

        // x direction lower than allowed
        assertFalse(mars.canMove(-1, 0));

        // y direction greated than allowed
        assertFalse(mars.canMove(0, MAX_Y + 1));

        // y direction lower than allowed
        assertFalse(mars.canMove(0, -1));
    }

    @Test
    public void canMoveMustReturnTrue() {

        final Mars mars = new Mars();

        // x and y direction on lower limit
        assertTrue(mars.canMove(0, 0));

        // x direction on highter limit
        assertTrue(mars.canMove(MAX_X, 0));

        // y direction on highter limit
        assertTrue(mars.canMove(0, MAX_Y));

    }

}
