package puzzle;
import shapes.Rectangle;

public class Fixed extends Tiles{
    private boolean visible;


    public Fixed(int posX, int posY, String color, Puzzle board) {
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

    public void relocateTile(int[] from, int[] to) {
        System.out.println("You can't move a fixed tile");
    }

    private void relocate(int[] from, int[] to) {
        int row = from[0];
        int column = from[1];
        int newRow = to[0];
        int newColumn = to[1];
        if (row >= this.board.getHeight() || column >= this.board.getWidth() || row < 0 || column < 0 ){
            System.out.println("Row or column not valid");
        }
        if (newRow >= this.board.getHeight() || newColumn >= this.board.getWidth() || newRow < 0 || newColumn < 0 ){
            System.out.println("Row or column objective not valid");
        }
        Tiles tile = this.board.getTile(row,column);
        if (tile == null) {
            System.out.println("Tile or position no valid to move");
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
            this.relocate(from, to);
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
            this.relocate(from, to);
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
            this.relocate(from, to);
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
            this.relocate(from, to);
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