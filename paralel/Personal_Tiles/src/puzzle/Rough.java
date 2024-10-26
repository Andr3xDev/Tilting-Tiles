package puzzle;
import shapes.Rectangle;

public class Rough extends Tiles{
    private Rectangle tile;
    private int row;
    private int column;

    public Rough(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        tile = new Rectangle();
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

    @Override
    protected void moveLeft() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }

    @Override
    protected void moveRight() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }

    @Override
    protected void moveDown() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }

    @Override
    protected void moveUp() throws puzzleExceptions {
        throw new puzzleExceptions(puzzleExceptions.ROUGH_EXCEPTION);
    }
}