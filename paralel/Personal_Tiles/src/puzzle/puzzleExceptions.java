package puzzle;

public class puzzleExceptions extends Exception {
    public static final String INVALID_POS = "Row or column not valid";
    public static final String INVALID_MOVE = "Row or column objective not valid";
    public static final String INVALID_TILE = "Tile or position no valid to move";
    public static final String FIXED_EXCEPTION = "You can't move or delete a fixed tile";
    public static final String ROUGH_EXCEPTION = "You can't tilt a rough tile";

    public puzzleExceptions(String message) {
        super(message);
    }
}