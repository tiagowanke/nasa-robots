package robots.model;

public enum Direction {

    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');

    private char letter;

    private Direction(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    /**
     * @return The corresponding left {@link Direction} of the current {@link Direction}.
     */
    public Direction leftDirection() {

        Direction toReturn;

        switch (this) {
        case NORTH:
            toReturn = Direction.WEST;
            break;
        case SOUTH:
            toReturn = Direction.EAST;
            break;
        case EAST:
            toReturn = Direction.NORTH;
            break;
        case WEST:
            toReturn = Direction.SOUTH;
            break;
        default:
            throw new UnsupportedOperationException("It is not currently supported to left direction on the current direction: " + this.toString());
        }

        return toReturn;
    }

    /**
     * @return The corresponding right {@link Direction} of the current {@link Direction}.
     */
    public Direction rightDirection() {

        Direction toReturn;

        switch (this) {
        case NORTH:
            toReturn = Direction.EAST;
            break;
        case SOUTH:
            toReturn = Direction.WEST;
            break;
        case EAST:
            toReturn = Direction.SOUTH;
            break;
        case WEST:
            toReturn = Direction.NORTH;
            break;
        default:
            throw new UnsupportedOperationException("It is not currently supported to right direction on the current direction: " + this.toString());
        }

        return toReturn;
    }

}
