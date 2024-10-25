package puzzle;

import shapes.*;
import shapes.Rectangle;

import java.awt.*;


/**
 * Class to represent the tiles of the board. It has the attributes to represent the tiles in the GUI board.
 * Also, it has the attributes to represent the holes in the GUI board.
 * The class has the methods to make visible and invisible the tiles and holes.
 *
 * @author Andrés Felipe Chavarro Plazas
 * @author David Santiago Espinosa R+ojas
 * @since 27-08-2024
 * @version 0.7
 */

public class Tiles {
    //* attributes of the class
    protected int posX;
    protected int posY;
    private String name;
    private String color;
    private final Rectangle tile;
    private final Puzzle board;
    private boolean visible;

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

    public void moveLeft() {
        int moveSteps = maxMoveLeft(this);
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() - moveSteps};
            board.relocateTile(from, to);
        }
    }
    public void moveRight() {
        int moveSteps = maxMoveRight(this);
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY(), getPosX() + moveSteps};
            board.relocateTile(from, to);
        }
    }

    public void moveDown() {
        int moveSteps = maxMoveDown(this);
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() + moveSteps,getPosX()};
            board.relocateTile(from, to);
        }
    }

    public void moveUp() {
        int moveSteps = maxMoveUp(this);
        if (moveSteps == -1) {
            this.board.deleteTile(posY, posX);
        } else if (moveSteps > 0) {
            int[] from = {getPosY(), getPosX()};
            int[] to = {getPosY() - moveSteps,getPosX()};
            board.relocateTile(from, to);
        }
    }


    public int maxMoveLeft(Tiles tile){
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


    private int maxMoveRight(Tiles tile){
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

    private int maxMoveUp(Tiles tile){
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


    private int maxMoveDown(Tiles tile){
        int max = 0;
        int height = this.board.getHeight();
        for (int fila = posY + 1; fila < height; fila++){
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

    public void relocateTile(int[] from, int[] to) {
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
            board.addTile(newRow,newColumn,name);
        }
        board.setActualBoard();
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