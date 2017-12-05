package robots.model;

import robots.exception.InvalidPositionException;

/**
 * Objects that have the hability to move must implement this class.
 *
 * @author Tiago
 */
public interface Movable {

    /**
     * @throws InvalidPositionException If position to move is invalid
     */
    public Movable move() throws InvalidPositionException;

    /**
     * Rotate to the given direction
     *
     * @param direction 'L' or 'R' to left or right.
     */
    public Movable rotate(char direction);

    /**
     * @return Current robot's position in the format (x, y, direction); Example: (0, 2, W)
     */
    public String currentPosition();

}
