package puzzle;

public class puzzleExceptions extends Exception {
    public static final String INVALID_POS = "Row or column not valid"; // Invalid position
    public static final String INVALID_MOVE = "Row or column objective not valid"; // Invalid move
    public static final String INVALID_TILE = "Tile or position no valid to move"; // Invalid tile
    public static final String FIXED_EXCEPTION = "You can't move or delete a fixed tile"; // Fixed tile
    public static final String ROUGH_EXCEPTION = "You can't tilt a rough tile"; // Rough tile
    public static final String INVALID_DELETE = "There's not valid tile to delete"; // Invalid delete
    public static final String NO_GOAL = "There's not a goal defined"; // No goal

    public puzzleExceptions(String message) {
        super(message);
    }
}
