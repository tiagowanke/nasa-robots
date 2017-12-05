package robots.model;

import robots.exception.InvalidPositionException;

/**
 * Represents a Robot that move one position and rotate 90 degrees at time.
 *
 * @author Tiago
 */
public class Robot implements Movable {

    private int x = 0;
    private int y = 0;
    private Direction direction = Direction.NORTH;
    private final Terrain terrain;

    public Robot(Terrain terrain) {
        this.terrain = terrain;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Move one position foward to the current {@link Robot#direction};
     */
    public Movable move() throws InvalidPositionException {

        int xToCheck = this.x;
        int yToCheck = this.y;

        switch (this.direction) {
        case NORTH:
            yToCheck++;
            break;
        case SOUTH:
            yToCheck--;
            break;
        case EAST:
            xToCheck++;
            break;
        case WEST:
            xToCheck--;
            break;
        default:
            throw new UnsupportedOperationException("Robot has a current direction that has no supported movement. Current direction: " + this.direction);
        }

        if (this.terrain != null && this.terrain.canMove(xToCheck, yToCheck)) {
            this.x = xToCheck;
            this.y = yToCheck;
        } else {
            throw new InvalidPositionException(String.format("Position x=%d y=%d is not a valid position for the current terrain.", xToCheck, yToCheck));
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Movable rotate(char direction) {

        switch (direction) {
        case 'L':
            this.direction = this.direction.leftDirection();
            break;
        case 'R':
            this.direction = this.direction.rightDirection();
            break;
        default:
            throw new IllegalArgumentException("Informed direction it's not a valid one. It should be 'L' for left or 'R' for right.");
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public String currentPosition() {
        return String.format("(%d, %d, %c)", this.x, this.y, this.direction.getLetter());
    }

}
