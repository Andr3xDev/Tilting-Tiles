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

    private void moveLeft() {
        int width = this.board.getWidth();
        int height = this.board.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0;j < height; j++) {
                Tiles tile = this.board.getTile(j,i);
                if (tile != null) {
                    if (maxMoveLeft(tile) == -1){
                        this.board.deleteTile(j,i);
                    } else {
                        int[] from = {tile.getPosY(), tile.getPosX()};
                        int[] to = {tile.getPosY(), tile.getPosX()-maxMoveLeft(tile)};
                        //relocateTile(from,to);
                    }
                }
            }
        }
    }
    private void moveRight() {
        int width = this.board.getWidth();
        int height = this.board.getHeight();

        for (int i = width-1; i >= 0 ; i--) {
            for (int j = 0;j < height; j++) {
                Tiles tile = this.board.getTile(j,i);
                if (tile != null) {
                    if (maxMoveRight(tile) == -1){
                        this.board.deleteTile(j,i);
                    } else {
                        int[] from = {tile.getPosY(), tile.getPosX()};
                        int[] to = {tile.getPosY(), tile.getPosX()+maxMoveRight(tile)};
                        //relocateTile(from,to);
                    }
                }
            }
        }
    }

    private void moveDown() {
        int width = this.board.getWidth();
        int height = this.board.getHeight();
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                Tiles tile = this.board.getTile(i,j);
                if (tile != null) {
                    int moveSteps = maxMoveDown(tile);
                    if (moveSteps == -1) {
                        this.board.deleteTile(i, j);
                    } else if (moveSteps > 0) {
                        int[] from = {tile.getPosY(), tile.getPosX()};
                        int[] to = {tile.getPosY() + moveSteps, tile.getPosX()};
                        //relocateTile(from, to);
                    }
                }
            }
        }
    }

    private void moveUp() {
        int width = this.board.getWidth();
        int height = this.board.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0;j < width; j++) {
                Tiles tile = this.board.getTile(i,j);
                if (tile != null) {
                    if (maxMoveUp(tile) == -1){
                        this.board.deleteTile(i,j);
                    } else if (maxMoveUp(tile) > 0) {
                        int[] from = {tile.getPosY(), tile.getPosX()};
                        int[] to = {tile.getPosY()-maxMoveUp(tile), tile.getPosX()};
                        //relocateTile(from,to);
                    }
                }
            }
        }
    }


    private int maxMoveLeft(Tiles tile){
        int max = 0;
        for (int column = posX - 1; column >= 0; column--) {
            Tiles tileO = this.board.getTile(posY,column);
            if (tileO == null){
                max++;
            }
        }
        return max;
    }


    public int maxMoveRight(Tiles tile){
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

    public int maxMoveUp(Tiles tile){
        int max = 0;
        for (int fila = posY - 1; fila >= 0; fila--){
            Tiles tileO = this.board.getTile(fila,posX);
            if (tileO == null){
                max++;
            }
        }
        return max;
    }


    private int maxMoveDown(Tiles tile){
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