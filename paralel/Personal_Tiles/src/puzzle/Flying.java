package puzzle;

import shapes.Rectangle;

public class Flying extends Tiles{
    private int row;
    private int column;
    public Flying(int posX, int posY, String color, Puzzle board) {
        super(posX, posY, color, board);
        Rectangle tile = new Rectangle();
        this.row = posY;
        this.column = posX;
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
    @Override
    public void makeVisible() {
        this.tile.makeVisible();
    }

    @Override
    public void makeInvisible() {
        this.tile.makeInvisible();
    }
}