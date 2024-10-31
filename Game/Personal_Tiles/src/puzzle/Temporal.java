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
        this.lives = 1;
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
     * Method to calculate the maximum steps that the tile can move to the left.
     * @return The maximum steps that the tile can move to the left.
     */
    @Override
    protected int maxMoveLeft(){
        int max = 0;
        for (int column = posX - 1; column >= 0; column--) {
            Tiles tileO = this.board.getTile(posY,column);
            Holes hole = this.board.getHole(posY,column);
            if (tileO == null && hole == null){
                max++;
            } else if (hole != null || this.lives == 0) {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to calculate the maximum steps that the tile can move to the right.
     * @return The maximum steps that the tile can move to the right.
     */
    @Override
    protected int maxMoveRight(){
        int max = 0;
        int width = this.board.getWidth();
        for (int column = posX + 1; column < width; column++) {
            Tiles tileO = this.board.getTile(posY,column);
            Holes hole = this.board.getHole(posY,column);
            if (tileO == null && hole == null){
                max++;
            } else if (hole != null || this.lives == 0)  {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to calculate the maximum steps that the tile can move up.
     * @return The maximum steps that the tile can move up.
     */
    @Override
    protected int maxMoveUp(){
        int max = 0;
        for (int fila = posY - 1; fila >= 0; fila--){
            Tiles tileO = this.board.getTile(fila,posX);
            Holes hole = this.board.getHole(fila,posX);
            if (tileO == null && hole == null){
                max++;
            } else if (hole != null || this.lives == 0) {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to calculate the maximum steps that the tile can move down.
     * @return The maximum steps that the tile can move down.
     */
    @Override
    protected int maxMoveDown(){
        int max = 0;
        int height = this.board.getHeight();
        for (int fila = posY + 1; fila < height; fila++){
            Tiles tileO = this.board.getTile(fila,posX);
            Holes hole = this.board.getHole(fila,posX);
            if (tileO instanceof Flying && hole != null){
                max--;
            } else if (tileO == null && hole == null){
                max++;
            } else if (hole != null || this.lives == 0) {
                return -1;
            }
        }
        return max;
    }


    /**
     * Method to move the tile up. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move up.
     */
    @Override
    protected void moveUp() throws puzzleExceptions {
        int moveSteps = maxMoveUp();
        if (moveSteps == -1) {
            this.board.deleteTile(this.posY, this.posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() - moveSteps,getPosX()};
            try {
                restLive();
                relocateTile(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Method to move the tile to the left. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move to the left.
     */
    @Override
    protected void moveLeft() throws puzzleExceptions {
        int moveSteps = maxMoveLeft();
        if (moveSteps == -1) {
            this.board.deleteTile(this.posY, this.posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() - moveSteps};
            try {
                restLive();                relocateTile(from, to);
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
        int moveSteps = maxMoveRight();
        if (moveSteps == -1) {
            this.board.deleteTile(this.posY, this.posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() + moveSteps};
            try {
                restLive();
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
        if (moveSteps == -1) {
            this.board.deleteTile(this.posY, this.posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() + moveSteps,getPosX()};
            try {
                restLive();
                relocateTile(from, to);
            } catch (puzzleExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void restLive(){
        this.lives--;
    }
}