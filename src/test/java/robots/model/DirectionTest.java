package robots.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void testReturnLeftDirection() {
        assertEquals(Direction.WEST, Direction.NORTH.leftDirection());
        assertEquals(Direction.EAST, Direction.SOUTH.leftDirection());
        assertEquals(Direction.NORTH, Direction.EAST.leftDirection());
        assertEquals(Direction.SOUTH, Direction.WEST.leftDirection());
    }

    @Test
    public void testReturRightDirection() {
        assertEquals(Direction.EAST, Direction.NORTH.rightDirection());
        assertEquals(Direction.WEST, Direction.SOUTH.rightDirection());
        assertEquals(Direction.SOUTH, Direction.EAST.rightDirection());
        assertEquals(Direction.NORTH, Direction.WEST.rightDirection());
    }

    @Test
    public void testLetters() {
        assertEquals('E', Direction.EAST.getLetter());
        assertEquals('W', Direction.WEST.getLetter());
        assertEquals('N', Direction.NORTH.getLetter());
        assertEquals('S', Direction.SOUTH.getLetter());
    }
}
