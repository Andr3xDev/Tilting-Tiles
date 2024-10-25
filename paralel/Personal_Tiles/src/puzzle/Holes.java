package puzzle;

import shapes.Triangle;

public class Holes {
    protected int posX;
    protected int posY;
    private final static char name = 'X';
    private final static String color = "Black";
    private Triangle holeTile;
    private Puzzle board;

    public Holes(int posX, int posY,Puzzle board) {
        this.posX = posX;
        this.posY = posY;
        this.holeTile = new Triangle();
        this.board = board;
        makeVisibleX();
    }
    public void makeVisibleX() {
        holeTile.makeVisible();
        holeTile.changeColor(color);
        holeTile.changeSize(20,20);
        holeTile.moveHorizontal(posX * 23);
        holeTile.moveVertical(posY * 23);
    }
    public char getName() {
        return name;
    }
}