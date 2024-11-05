package puzzle;
import shapes.Rectangle;

/**
 * Class that represents the rough tiles of the puzzle
 */
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
        this.row = posY;
        this.column = posX;
        Rectangle tile = new Rectangle();
        setPosY(posY);
        setPosX(posX);
        this.lives = 2;
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
     * Method to move the tile to the left. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move to the left.
     */
    @Override
    protected void moveLeft() throws puzzleExceptions {
        reduceLives();
        int moveSteps = maxMoveLeft();
        if (moveSteps == -1 || this.lives == 0) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() - moveSteps};
            try {
                relocateTile(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to move the tile to the right. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move to the right.
     */
    @Override
    protected void moveRight() throws puzzleExceptions {
        reduceLives();
        int moveSteps = maxMoveRight();
        if (moveSteps == -1 || this.lives == 0) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() + moveSteps};
            try {
                relocateTile(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to move the tile down. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move down.
     */
    @Override
    protected void moveDown() throws puzzleExceptions {
        int moveSteps = maxMoveDown();
        reduceLives();
        if (moveSteps == -1 || this.lives == 0) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() + moveSteps,getPosX()};
            try {
                relocateTile(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to move the tile up. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move up.
     */
    @Override
    protected void moveUp() throws puzzleExceptions {
        int moveSteps = maxMoveUp();
        reduceLives();
        if (moveSteps == -1 || this.lives == 0) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() - moveSteps,getPosX()};
            try {
                relocateTile(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Function that reduces the lives of the tile
     */
    private void reduceLives(){
        this.lives --;
    }
}