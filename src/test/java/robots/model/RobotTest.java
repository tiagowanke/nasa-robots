package robots.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import robots.exception.InvalidPositionException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Robot.class)
public class RobotTest {

    @Mock
    private Mars mockMars;

    private final int INITIAL_X = 0;
    private final int INITIAL_Y = 0;
    private final Direction INITIAL_DIRECTION = Direction.NORTH;
    private final String INITIAL_POSITION = String.format("(%d, %d, %c)", INITIAL_X, INITIAL_Y, INITIAL_DIRECTION.getLetter());

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNull() {
        new Robot(null);
    }

    @Test
    public void testInitialPosition() {

        final Robot robot = new Robot(this.mockMars);
        assertEquals(INITIAL_X, robot.getX());
        assertEquals(INITIAL_Y, robot.getY());
        assertEquals(INITIAL_DIRECTION, robot.getDirection());
        assertEquals(INITIAL_POSITION, robot.currentPosition());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rotateMustThrowIllegalArgumentException() {
        final Robot robot = new Robot(this.mockMars);
        robot.rotate('Z');
    }

    @Test
    public void rotateMustWorkAsExpected() {

        final Robot robot = new Robot(this.mockMars);
        robot.rotate('L');
        assertEquals(Direction.WEST, robot.getDirection());

        robot.rotate('R');
        assertEquals(Direction.NORTH, robot.getDirection());

        robot.rotate('R').rotate('L');
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeCommandMustThrowsException() throws InvalidPositionException {

        final Robot robot = new Robot(this.mockMars);
        robot.executeCommand('T');
    }

    @Test
    public void executeCommandMustExecuteAsExpected() throws Exception {

        when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        final Robot robot = spy(new Robot(this.mockMars));

        // generate a random number of times to call execute commands
        final int randomInt = new Random().ints(0, 5).findFirst().getAsInt();
        IntStream.range(0, randomInt)
        .forEach(idx -> {
            try {
                // move foward
                robot.executeCommand('M');
                // move left
                robot.executeCommand('L');
                // move right
                robot.executeCommand('R');

                // check if all the actions were called correctly
                final int i = idx + 1;
                verify(robot, times(i)).move();
                verify(robot, times(i)).rotate('L');
                verify(robot, times(i)).rotate('R');
            } catch (final Exception e) {
                Assert.fail("Didn't execute expected command.");
            }
        });

        // handle when random number is 0
        verify(robot, times(randomInt)).move();
        verify(robot, times(randomInt)).rotate('L');
        verify(robot, times(randomInt)).rotate('R');
    }

    @Test
    public void moveOutsideToAllDirectionOnMars() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        // move five positions to North (initial position) considering a terrain with 5 positions
        Robot robot = new Robot(this.mockMars);
        try {
            // first four moves must work
            when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
            robot.move().move().move().move();
            when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
            // the fifth move must throw exception since is at the edge
            robot.move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed must be on the edge of north
            assertEquals(0, robot.getX());
            assertEquals(4, robot.getY());
            assertEquals(Direction.NORTH, robot.getDirection());
        }

        final Field direction = robot.getClass().getDeclaredField("direction");
        direction.setAccessible(true);

        // move six positions to South
        robot = new Robot(this.mockMars);
        direction.set(robot, Direction.SOUTH);
        try {
            when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
            robot.move().move().move().move().move().move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed
            assertEquals(0, robot.getX());
            assertEquals(0, robot.getY());
            assertEquals(Direction.SOUTH, robot.getDirection());
        }

        // move five positions to East
        robot = new Robot(this.mockMars);
        direction.set(robot, Direction.EAST);
        try {
            when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
            robot.move().move().move().move();
            when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
            robot.move();
            fail();
        } catch (final InvalidPositionException e) {
            // check if positions were changed
            assertEquals(4, robot.getX());
            assertEquals(0, robot.getY());
            assertEquals(Direction.EAST, robot.getDirection());
        }

        // move six positions to West
        robot = new Robot(this.mockMars);
        direction.set(robot, Direction.WEST);
        try {
            when(this.mockMars.canMove(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
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
        final Robot robot = new Robot(this.mockMars);

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
