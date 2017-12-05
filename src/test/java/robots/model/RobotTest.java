package robots.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.Test;

import robots.exception.InvalidPositionException;

public class RobotTest {

    @Test
    public void testInitialPosition() {

        final Robot robot = new Robot(new Mars());
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rotateMustThrowIllegalArgumentException() {
        final Robot robot = new Robot(new Mars());
        robot.rotate('Z');
    }

    @Test
    public void rotateMustWorkAsExpected() {

        final Robot robot = new Robot(new Mars());
        robot.rotate('L');
        assertEquals(Direction.WEST, robot.getDirection());

        robot.rotate('R');
        assertEquals(Direction.NORTH, robot.getDirection());

        robot.rotate('R').rotate('L');
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    public void moveOutsideToAllDirectionOnMars() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        // move six positions to North (initial position)
        Robot robot = new Robot(new Mars());
        try {
            robot.move().move().move().move().move().move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed
            assertEquals(0, robot.getX());
            assertEquals(4, robot.getY());
            assertEquals(Direction.NORTH, robot.getDirection());
        }

        final Field direction = robot.getClass().getDeclaredField("direction");
        direction.setAccessible(true);

        // move six positions to South
        robot = new Robot(new Mars());
        direction.set(robot, Direction.SOUTH);
        try {
            robot.move().move().move().move().move().move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed
            assertEquals(0, robot.getX());
            assertEquals(0, robot.getY());
            assertEquals(Direction.SOUTH, robot.getDirection());
        }

        // move six positions to East
        robot = new Robot(new Mars());
        direction.set(robot, Direction.EAST);
        try {
            robot.move().move().move().move().move().move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed
            assertEquals(4, robot.getX());
            assertEquals(0, robot.getY());
            assertEquals(Direction.EAST, robot.getDirection());
        }

        // move six positions to West
        robot = new Robot(new Mars());
        direction.set(robot, Direction.WEST);
        try {
            robot.move().move().move().move().move().move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed
            assertEquals(0, robot.getX());
            assertEquals(0, robot.getY());
            assertEquals(Direction.WEST, robot.getDirection());
        }
    }

    @Test
    public void testCurrentPosition() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        final Robot robot = new Robot(new Mars());
        assertEquals("(0, 0, N)", robot.currentPosition());

        final Field x = robot.getClass().getDeclaredField("x");
        x.setAccessible(true);
        x.set(robot, 1);
        assertEquals("(1, 0, N)", robot.currentPosition());

        final Field y = robot.getClass().getDeclaredField("y");
        y.setAccessible(true);
        y.set(robot, 1);
        assertEquals("(1, 1, N)", robot.currentPosition());

        final Field direcion = robot.getClass().getDeclaredField("direction");
        direcion.setAccessible(true);
        direcion.set(robot, Direction.EAST);
        assertEquals("(1, 1, E)", robot.currentPosition());
    }
}
