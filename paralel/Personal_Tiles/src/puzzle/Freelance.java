package puzzle;
import shapes.Rectangle;

public class Freelance extends Tiles{
    private int row;
    private int column;

    public Freelance(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        Rectangle tile = new Rectangle();
        makeVisibleCreate();
    }
    @Override
    public void makeVisibleCreate() {
        tile.changeColor(this.getColor());
        tile.changeSize(20, 20);
        tile.moveHorizontal(column * 23);
        tile.moveVertical(row * 23);
        tile.makeVisible();
    }
    public void makeVisible() {
        tile.makeVisible();
    }
    public void makeInvisible() {
        tile.makeInvisible();
    }
}