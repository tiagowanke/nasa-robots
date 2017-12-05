package robots.model;

/**
 * Represents any terrain where {@link Movable} entities can move above.
 *
 * @author Tiago
 */
public abstract class Terrain {

    private final int maxX;
    private final int maxY;

    Terrain(short maxX, short maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    /**
     * @return True if is possible to move to the given position, false otherwise.
     */
    public boolean canMove(int x, int y) {
        final boolean canMoveX = x >= 0 && x <= this.maxX;
        final boolean canMoveY = y >= 0 && y <= this.maxY;

        return canMoveX && canMoveY;
    }

}
