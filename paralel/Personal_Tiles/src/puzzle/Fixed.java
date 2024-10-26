package puzzle;
import shapes.Rectangle;

public class Fixed extends Tiles{
    private int row;
    private int column;


    public Fixed(int posX, int posY, String color, Puzzle board) {
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
    @Override
    public void relocateTile(int[] from, int[] to) throws puzzleExceptions{
        throw new puzzleExceptions(puzzleExceptions.FIXED_EXCEPTION);    }

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

    @Override
    protected void moveLeft() {
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

    @Override
    protected void moveRight() {
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

    @Override
    protected void moveDown() {
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

    @Override
    protected void moveUp() {
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

    @Override
    public void makeVisible() {
        this.tile.makeVisible();
    }
    @Override
    public void makeInvisible() {
        this.tile.makeInvisible();
    }
}