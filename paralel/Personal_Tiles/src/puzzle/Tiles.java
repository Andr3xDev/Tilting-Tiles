package puzzle;

import shapes.Rectangle;

/**
 * Class to represent the tiles of the board. It has the attributes to represent the tiles in the GUI board.
 * Also, it has the attributes to represent the holes in the GUI board.
 * The class has the methods to make visible and invisible the tiles and holes.
 *
 * @author Andrés Felipe Chavarro Plazas
 * @author David Santiago Espinosa R+ojas
 * @since 27-08-2024
 * @version 1.0
 */
public class Tiles {

    //* attributes of the class
    protected int posX;
    protected int posY;
    private String name;
    private String color;
    protected Rectangle tile ;
    protected final Puzzle board;
    private boolean visible;


    //* Constructors
    /**
     * Constructor for puzzle.Tiles of class Board. Most part of the attributes are initialized by default.
     * name is initialized by the first letter of the color.
     * @param posX Row position to represent the tile in the GUI board.
     * @param posY Column position to represent the tile in the GUI board.
     * @param color Color and the name of the tile.
     */
    public Tiles(int posX, int posY, String color, Puzzle board) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        setName(color);
        this.tile = new Rectangle();
        this.board = board;
        this.visible = true;
        makeVisibleCreate();
    }

    //* Methods

    /**
     * Method to move the tile to the left. It's a help function to move the tiles in the board.
     * @throws puzzleExceptions If the tile can't move to the left.
     */
    protected void moveLeft() throws puzzleExceptions {
        int moveSteps = maxMoveLeft();
        if (moveSteps == -1) {
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
    protected void moveRight() throws puzzleExceptions {
        int moveSteps = maxMoveRight();
        if (moveSteps == -1) {
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
    protected void moveDown() throws puzzleExceptions {
        int moveSteps = maxMoveDown();
        if (moveSteps == -1) {
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
    protected void moveUp() throws puzzleExceptions {
        int moveSteps = maxMoveUp();
        if (moveSteps == -1) {
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
     * Method to calculate the maximum steps that the tile can move to the left.
     * @return The maximum steps that the tile can move to the left.
     */
    protected int maxMoveLeft(){
        int max = 0;
        for (int column = posX - 1; column >= 0; column--) {
            Tiles tileO = this.board.getTile(posY,column);
            Holes hole = this.board.getHole(posY,column);
            if (tileO == null && hole == null){
                max++;
            } else if (hole != null) {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to calculate the maximum steps that the tile can move to the right.
     * @return The maximum steps that the tile can move to the right.
     */
    protected int maxMoveRight(){
        int max = 0;
        int width = this.board.getWidth();
        for (int column = posX + 1; column < width; column++) {
            Tiles tileO = this.board.getTile(posY,column);
            Holes hole = this.board.getHole(posY,column);
            if (tileO == null && hole == null){
                max++;
            } else if (hole != null) {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to calculate the maximum steps that the tile can move up.
     * @return The maximum steps that the tile can move up.
     */
    protected int maxMoveUp(){
        int max = 0;
        for (int fila = posY - 1; fila >= 0; fila--){
            Tiles tileO = this.board.getTile(fila,posX);
            Holes hole = this.board.getHole(fila,posX);
            if (tileO == null && hole == null){
                max++;
            } else if (hole != null) {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to calculate the maximum steps that the tile can move down.
     * @return The maximum steps that the tile can move down.
     */
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
            } else if (hole != null) {
                return -1;
            }
        }
        return max;
    }

    /**
     * Method to relocate the tile in the board.
     * @param from Position of the tile to move.
     * @param to Position to move the tile.
     * @throws puzzleExceptions If the tile can't move to the position.
     */
    public void relocateTile(int[] from, int[] to) throws puzzleExceptions{
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
            decideTile(row,column,newRow,newColumn,name);
        }
        board.setActualBoard();
    }

    /**
     * Method to decide the type of tile to add in the board.
     * @param row Row position of the tile to move.
     * @param column Column position of the tile to move.
     * @param newRow Row position to move the tile.
     * @param newColumn Column position to move the tile.
     * @param name Name of the tile to move.
     * @throws puzzleExceptions If the tile can't move to the position.
     */
    private void decideTile(int row,int column,int newRow,int newColumn,String name) throws puzzleExceptions {
        Tiles tile = this.board.getTile(row, column);
        if (tile instanceof Flying){
            board.deleteTile(row,column);
            try {
                board.addFlying(newRow,newColumn,name);
            } catch (puzzleExceptions e) {
                throw new RuntimeException(e);
            }
        } else if (tile instanceof Rough) {
            board.deleteTile(row,column);
            try {
                board.addRough(newRow,newColumn,name);
            } catch (puzzleExceptions e) {
                throw new RuntimeException(e);
            }
        }else if (tile instanceof Freelance) {
            board.deleteTile(row,column);
            try {
                board.addFreelance(newRow,newColumn,name);
            } catch (puzzleExceptions e) {
                throw new RuntimeException(e);
            }
        } else {
            board.deleteTile(row,column);
            try {
                board.addTile(newRow,newColumn,name);
            } catch (puzzleExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Function to make visible the tiles. It's a help function to make invisible the board.
     */
    public void makeVisibleCreate() {
        tile.changeColor(this.color);
        tile.changeSize(20, 20);
        tile.moveHorizontal(posX * 23);
        tile.moveVertical(posY * 23);
        tile.makeVisible();
    }

    /**
     * Function to make visible the tiles
     */
    public void makeVisible() {
        tile.makeVisible();
        this.visible = true;
    }
    /**
     * Function to make invisible the tiles
     */
    public void makeInvisible() {
        tile.makeInvisible();
        this.visible = false;
    }



    //* getters and setters

    // positions
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // name
    public char getName() {
        return this.name.charAt(0);
    }
    public void setName(String name) {
        this.name = String.valueOf(name.charAt(0));
    }

    //colors
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    // visible
    public boolean getVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}