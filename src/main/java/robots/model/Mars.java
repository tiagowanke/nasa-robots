package robots.model;

/**
 * <pre>
 * Mars {@link Terrain} with the following limits:
 * X: {@link Mars#MAX_X}
 * Y: {@link Mars#MAX_Y}
 * </pre>
 *
 * @author Tiago
 */
public class Mars extends Terrain {

    private static final short MAX_X = 4;
    private static final short MAX_Y = 4;

    public Mars() {
        super(MAX_X, MAX_Y);
    }

}
