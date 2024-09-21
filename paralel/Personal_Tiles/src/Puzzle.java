import javax.swing.*;

/**
 * Write a description of class Puzzle here.
 *
 * @author Andrés Chavarro & David Espinosa
 * @version Version 0.5
 */
//! Falta poner la descripcion

public class Puzzle {

    //* Variables
    private int height;
    private int width;
    public Tiles[][] board;
    private Rectangle table;
    private int missingSpace;

    /**
     * Constructor for objects of class Puzzle
     */
    public Puzzle(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new Tiles[width][height];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        makeVisible();
    }

    // + missing others constructors

    /**
     * Function to add tiles to the board
     */
    public void addTile(int row, int column, String color) {
        if (row >= this.width || column >= this.height || row < 0 || column < 0 || board[row][column] != null){
            System.out.println("Row or column not valid");
        } else {
            Tiles tile = new Tiles(row, column, color);
            board[row][column] = tile;
            this.missingSpace--;
        }
    }

    /**
     * Function to delete tiles from the board
     */
    public void deleteTile(int row, int column) {
        if (row >= this.width || column >= this.height || row < 0 || column < 0 ){
            System.out.println("Row or column not valid");
        } else {
            Tiles tile = board[row][column];
            if (tile == null || tile.getHole()) {
                System.out.println("There is not valid tile in this position");
            }else {
                tile.makeInvisible();
                board[row][column] = null;
                this.missingSpace++;
            }
        }
    }

    /**
     * Function to relocate tiles in the board
     * @param from
     * @param to
     */
    public void relocateTile(int[] from, int[] to) {
        int row = from[0];
        int column = from[1];
        int newRow = to[0];
        int newColumn = to[1];
        if (row >= this.width || column >= this.height || row < 0 || column < 0 ){
            System.out.println("Row or column not valid");
        }
        if (newRow >= this.width || newColumn >= this.height || newRow < 0 || newColumn < 0 ){
            System.out.println("Row or column objetive not valid");
        }
        Tiles tile = board[row][column];
        if (tile == null || tile.getGlued()) {
            System.out.println("Tile or position no valid to move");
        } else {
            board[row][column] = null;
            board[newRow][newColumn] = tile;
            tile.setPosX(newColumn-column);
            tile.setPosY(newRow-row);
            tile.makeVisible();
        }
    }


    public void addGlue(int row, int column) {
        Tiles tile = board[row][column];
        if (tile == null) {
            System.out.println("There is no tile in this position");
        } else if (!tile.getGlued()){
            tile.setGlued(true);
            tile.tilesGlued[0] = board[row][column+1]; //abajo
            tile.tilesGlued[1] = board[row][column-1]; //arriba
            tile.tilesGlued[2] = board[row+1][column]; //derecha
            tile.tilesGlued[3] = board[row-1][column]; //izquierda
        }
    }

    public void deleteGlue(int row, int column) {
        Tiles tile = board[row][column];
        if (tile == null) {
            System.out.println("There is no tile in this position");
        } else if (tile.getGlued()){
            tile.setGlued(false);
            tile.tilesGlued[0] = null;
            tile.tilesGlued[1] = null;
            tile.tilesGlued[2] = null;
            tile.tilesGlued[3] = null;
        }
    }

    public void makeHole(int row, int column) {
        if (row >= this.width || column >= this.height || row < 0 || column < 0 || board[row][column] != null){
            System.out.println("Row or column not valid");
        } else {
            Tiles tile = new Tiles(row, column);
            board[row][column] = tile;
            this.missingSpace--;
        }
    }

    public void tilt(char direction) {
        switch (direction){
            case 'L':
                directionHorizontal(0);
            case 'R':
                directionHorizontal(this.height-1);
            case 'D':
                directionVertical(this.width-1);
            case 'U':
                directionVertical(0);
        }
    }

    public void directionHorizontal(int place){
        if (place == 0){ //IZQUIERDA
            for(int i = 0; i<width; i++){
                for (int j = 0; j<height; j++){
                    Tiles tile = board[i][j];
                    if (tile != null){
                        relocateTile(new int[]{i,j},new int[]{i,place});
                        place ++;
                    }
                }
                place = 0;
            }
        }else { //DERECHA
            for(int i = 0; i<width; i++){
                for (int j = height-1; j>=0; j--){
                    Tiles tile = board[i][j];
                    if (tile != null){
                        relocateTile(new int[]{i,j},new int[]{i,place});
                        place --;
                    }
                }
                place = height-1;
            }
        }
    }

    public void directionVertical(int place){
        if (place == 0){ //ARRIBA
            for(int i = 0; i<height; i++){
                for (int j = 0; j<width; j++){
                    Tiles tile = board[j][i];
                    if (tile != null){
                        relocateTile(new int[]{j,i},new int[]{place,i});
                        place ++;
                    }
                }
                place = 0;
            }
        }else {
            for(int i = 0; i<height; i++){
                for (int j = width-1; j>=0; j--){
                    Tiles tile = board[j][i];
                    if (tile != null){
                        relocateTile(new int[]{j,i},new int[]{place,i});
                        place --;
                    }
                }
                place = height-1;
            }
        }
    }

    //public void tilt() {}

    //public void isGoal() {}

    public void actualArrangemment() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getName());
                } else {
                    System.out.print('O');
                }
            }
            System.out.println();
        }
    }

    public int misPlacedTiles() {
        return this.missingSpace;
    }

    public void makeVisible(){
        table.makeVisible();
        table.changeSize((height*20)+height*3+2,(width*20)+width*3+2);
        table.changeColor("yellow");
        table.moveHorizontal(-3);
        table.moveVertical(-3);
    }

    public void makeInvisible() {
        table.makeInvisible();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (board[i][j] != null) {
                    deleteTile(i, j);
                }
            }
        }
    }

    public void finish() {

    }

    private boolean isFree(Tiles tile) {
        int row = tile.getPosX();
        int column = tile.getPosY();
        Tiles ng1 = board[row][column];
        Tiles ng2 = board[row][column+1];
        Tiles ng3 = board[row][column-1];
        Tiles ng4 = board[row+1][column];

        if (!ng1.getGlued() || !ng2.getGlued() || !ng3.getGlued() || !ng4.getGlued()) {
            return false;
        } else {
            return true;
        }
    }
}