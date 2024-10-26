package puzzle;
import shapes.Rectangle;

public class Rough extends Tiles{

    public Rough(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        Rectangle tile = new Rectangle();
        makeVisibleCreate();
    }
    public void makeVisibleCreate() {
        tile.changeColor(this.getColor());
        tile.changeSize(20, 20);
        tile.moveHorizontal(posX * 23);
        tile.moveVertical(posY * 23);
        tile.makeVisible();
    }
    public void makeVisible() {
        tile.makeVisible();
    }
    public void makeInvisible() {
        tile.makeInvisible();
    }

    @Override
    protected void moveLeft() {
        System.out.println("Nonas");
    }

    @Override
    protected void moveRight() {
        System.out.println("Nonas");
    }

    @Override
    protected void moveDown() {
        System.out.println("Nonas");
    }

    @Override
    protected void moveUp() {
        System.out.println("Nonas");
    }
}