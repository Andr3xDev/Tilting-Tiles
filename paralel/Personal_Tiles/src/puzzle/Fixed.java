package puzzle;
import shapes.Rectangle;

/**
 * Class Fixed
 * This class represents a fixed tile in the puzzle, this tile can't be relocated or deleted
 */
public class Fixed extends Tiles{

    //* Attributes
    private final int row;
    private final int column;

    //* Constructor
    /**
     * Constructor of the class Fixed
     * @param posX Position in the row
     * @param posY Position in the column
     * @param color Color of the tile
     * @param board Board where the tile is
     */
    public Fixed(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        this.row = posY;
        this.column = posX;
        Rectangle tile = new Rectangle();
        makeVisibleCreate();
    }

    //* Methods
    /**
     * Method to make visible the tile in the creation and set the position in the GUI
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
     * Method to sent exception when try to relocate a tile in the board
     * @param from Position of the tile to move.
     * @param to Position to move the tile.
     * @throws puzzleExceptions Exception to indicate that the tile can't be relocated
     */
    @Override
    public void relocateTile(int[] from, int[] to) throws puzzleExceptions{
        throw new puzzleExceptions(puzzleExceptions.FIXED_EXCEPTION);    }

    /**
     * Method to delete the tile in the board, it only works to do the tilt of the board.
     * @param from Position of the tile to delete.
     * @param to Position to add the tile.
     * @throws puzzleExceptions Exception to indicate that the tile can't complete method
     */
    private void relocate(int[] from, int[] to) throws puzzleExceptions{
        int row = from[0];
        int column = from[1];
        int newRow = to[0];
        int newColumn = to[1];
        if (row >= this.board.getHeight() || column >= this.board.getWidth() || row < 0 || column < 0 ){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        }
        if (newRow >= this.board.getHeight() || newColumn >= this.board.getWidth() || newRow < 0 || newColumn < 0 ){
            throw new puzzleExceptions(puzzleExceptions.INVALID_MOVE);
        }
        Tiles tile = this.board.getTile(row,column);
        if (tile == null) {
            throw new puzzleExceptions(puzzleExceptions.INVALID_TILE);
        } else{
            tile.setPosX(newColumn);
            tile.setPosY(newRow);
            String name = tile.getColor();
            board.deleteTile(row,column);
            board.addFixed(newRow,newColumn,name);
        }
        board.setActualBoard();
    }

    /**
     * Method to move the tile to the left
     * @throws puzzleExceptions Exception to indicate that the tile can't complete method
     */
    @Override
    protected void moveLeft() throws puzzleExceptions {
        int moveSteps = maxMoveLeft();
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() - moveSteps};
            try {
                relocate(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to move the tile to the right
     * @throws puzzleExceptions Exception to indicate that the tile can't complete method
     */
    @Override
    protected void moveRight() throws puzzleExceptions {
        int moveSteps = maxMoveRight();
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() + moveSteps};
            try {
                relocate(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to move the tile down
     * @throws puzzleExceptions Exception to indicate that the tile can't complete method
     */
    @Override
    protected void moveDown() throws puzzleExceptions {
        int moveSteps = maxMoveDown();
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() + moveSteps,getPosX()};
            try {
                relocate(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to move the tile up
     * @throws puzzleExceptions Exception to indicate that the tile can't complete method
     */
    @Override
    protected void moveUp() throws puzzleExceptions {
        int moveSteps = maxMoveUp();
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() - moveSteps,getPosX()};
            try {
                relocate(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to make the tile visible in the GUI
     */
    @Override
    public void makeVisible() {
        this.tile.makeVisible();
    }

    /**
     * Method to make the tile invisible in the GUI
     */
    @Override
    public void makeInvisible() {
        this.tile.makeInvisible();
    }
}