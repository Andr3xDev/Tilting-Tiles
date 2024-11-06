package puzzle;

import shapes.Triangle;

/**
 * Class Holes
 * This class represents the holes in the puzzle, it creates a board just with the holes
 */
public class Holes {

    //* Attributes
    protected int posX;
    protected int posY;
    private final static char name = 'X';
    private final static String color = "Black";
    private Triangle holeTile;
    private Puzzle board;

    //* Constructor
    /**
     * Constructor of the class Holes
     * @param posX Position in the row
     * @param posY Position in the column
     * @param board Board where the hole is
     */
    public Holes(int posX, int posY,Puzzle board) {
        this.posX = posX;
        this.posY = posY;
        this.holeTile = new Triangle();
        this.board = board;
        makeVisibleX();
    }

    //* Methods
    /**
     * Method to make visible the hole in the GUI
     */
    public void makeVisibleX() {
        holeTile.makeVisible();
        holeTile.changeColor(color);
        holeTile.changeSize(20,20);
        holeTile.moveHorizontal(posX * 23);
        holeTile.moveVertical(posY * 23);
    }

    public void makeInvisible() {
        holeTile.makeInvisible();
    }

    public void makeVisible() {
        holeTile.makeVisible();
    }

    /**
     * Method to get the name of the hole
     * @return Name of the hole
     */
    public char getName() {
        return name;
    }
}