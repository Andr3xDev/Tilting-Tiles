package puzzle;

import shapes.Rectangle;

public class Temporal extends Tiles{

    //* Attributes
    private final int row;
    private final int column;
    private int lives;

    //* Constructor
    /**
     * Constructor of the class
     * @param posX Position in the row
     * @param posY Position in the column
     * @param color Color of the tile
     * @param board Board where the tile is
     */
    public Temporal(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        Rectangle tile = new Rectangle();
        this.row = posY;
        this.column = posX;
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
     */
    @Override
    protected void moveLeft() throws puzzleExceptions {
    }

    /**
     * Method that prevent moves the tile to the right
     */
    @Override
    protected void moveRight() throws puzzleExceptions {

    }

    /**
     * Method that prevent moves the tile down
     */
    @Override
    protected void moveDown() throws puzzleExceptions {

    }

    /**
     * Method that prevent moves the tile up
     */
    @Override
    protected void moveUp() throws puzzleExceptions {

    }
}