package puzzle;

import shapes.Rectangle;

/**
 * Class that represents the flying tile
 * This tile can move in any direction ignoring the holes of the board
 */
public class Flying extends Tiles{

    //* Attributes
    private final int row;
    private final int column;

    //* Constructor

    /**
     * Constructor of the flying tile
     * @param posX Position in the row
     * @param posY Position in the column
     * @param color Color of the tile
     * @param board Board where the tile is
     */
    public Flying(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        Rectangle tile = new Rectangle();
        this.row = posY;
        this.column = posX;
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
     * Method to move the tile to the left
     */
    @Override
    protected void moveLeft() {
        int moveSteps = this.maxMoveL();
        if (moveSteps > 0) {
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
     * Method to move the tile to the right
     */
    @Override
    protected void moveRight() {
        int moveSteps = this.maxMoveR();
        if (moveSteps > 0) {
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
     * Method to move the tile down
     */
    @Override
    protected void moveDown() {
        int moveSteps = this.maxMoveD();
        if (moveSteps > 0) {
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
     * Method to move the tile up
     */
    @Override
    protected void moveUp() {
        int moveSteps = this.maxMoveU();
        if (moveSteps > 0) {
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
     * Method to get the maximum steps to move to the right
     * @return Maximum steps to move to the right
     */
    protected int maxMoveR(){
        int max = 0;
        int width = this.board.getWidth();
        for (int column = posX + 1; column < width; column++) {
            Tiles tileO = this.board.getTile(posY,column);
            if (tileO == null){
                max++;
            }
        }
        return max;
    }

    /**
     * Method to get the maximum steps to move to the left
     * @return Maximum steps to move to the left
     */
    protected int maxMoveU(){
        int max = 0;
        for (int fila = posY - 1; fila >= 0; fila--){
            Tiles tileO = this.board.getTile(fila,posX);
            if (tileO == null){
                max++;
            }
        }
        return max;
    }

    /**
     * Method to get the maximum steps to move down
     * @return Maximum steps to move down
     */
    protected int maxMoveD(){
        int max = 0;
        int height = this.board.getHeight();
        for (int fila = posY + 1; fila < height; fila++){
            Tiles tileO = this.board.getTile(fila,posX);
            if (tileO == null){
                max++;
            }
        }
        return max;
    }

    /**
     * Method to get the maximum steps to move to the left
     * @return Maximum steps to move to the left
     */
    protected int maxMoveL(){
        int max = 0;
        for (int column = posX - 1; column >= 0; column--) {
            Tiles tileO = this.board.getTile(posY,column);
            if (tileO == null ){
                max++;
            }
        }
        return max;
    }

    /**
     * Method to make visible the tile in the GUI
     */
    @Override
    public void makeVisible() {
        this.tile.makeVisible();
    }

    /**
     * Method to make invisible the tile in the GUI
     */
    @Override
    public void makeInvisible() {
        this.tile.makeInvisible();
    }
}