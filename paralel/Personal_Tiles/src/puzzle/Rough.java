package puzzle;
import shapes.Rectangle;

/**
 * Class that represents the rough tiles of the puzzle
 */
public class Rough extends Tiles{

    //* Attributes
    private final Rectangle tile;
    private int row;
    private int column;

    //* Constructor
    /**
     * Constructor of the class
     * @param posX Position in the row
     * @param posY Position in the column
     * @param color Color of the tile
     * @param board Board where the tile is
     */
    public Rough(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        tile = new Rectangle();
        makeVisibleCreate();
    }

    //* Methods
    /**
     * Method that creates the tile
     */
    @Override
    public void makeVisibleCreate() {
        tile.changeColor(this.getColor());
        tile.changeSize(20, 20);
        tile.moveHorizontal(column * 23);
        tile.moveVertical(row * 23);
        tile.makeVisible();
    }
    /**
     * Method that makes the tile visible
     */
    public void makeVisible() {
        tile.makeVisible();
    }

    /**
     * Method that makes the tile invisible
     */
    public void makeInvisible() {
        tile.makeInvisible();
    }

    /**
     * Method that prevent moves the tile to the left
     * @throws puzzleExceptions Exception that throws if the tile can't move
     */
    @Override
    protected void moveLeft() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }

    /**
     * Method that prevent moves the tile to the right
     * @throws puzzleExceptions Exception that throws if the tile can't move
     */
    @Override
    protected void moveRight() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }

    /**
     * Method that prevent moves the tile down
     * @throws puzzleExceptions Exception that throws if the tile can't move
     */
    @Override
    protected void moveDown() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }

    /**
     * Method that prevent moves the tile up
     * @throws puzzleExceptions Exception that throws if the tile can't move
     */
    @Override
    protected void moveUp() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }
}