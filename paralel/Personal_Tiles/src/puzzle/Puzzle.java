package puzzle;// import java.lang.Math;
import shapes.*;

/**
 * The initial project aims to develop an application that allows simulating a
 * situation inspired by Problem F of the 2023 Tilting international programming marathon
 * puzzle.Tiles. In this simulator you want to test a special glue, Gummy Glue, which is used
 * applied on a tile causes it and the neighboring tiles to stick together.
 *
 * @author Andrés Felipe Chavarro Plazas
 * @author David Santiago Espinosa Rojas
 * @since 27-08-2024
 * @version Version 0.8
 */

public class Puzzle {

    //* ------ Variables ------
    private final int height;
    private final int width;
    public Tiles[][] board;
//    public Glues[][] gluesBoard;
    public Holes[][] holesBoard;
    private final Rectangle table;
    private int missingSpace;
    private char[][]  actualEnding;
    private char[][] startBoard;
    private char[][] actualBoard;

    //* ------ Constructors ------

    /**
     * Constructor to create a board without initial or final objectives.
     * @param height to set the height of the board.
     * @param width to set the width of the board.
     */
    public Puzzle(int width, int height) {
        this.height = height;
        this.width = width;
        this.board = new Tiles[height][width];
        this.holesBoard = new Holes[height][width];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        this.actualBoard = new char[height][width];
        setActualBoard();
        makeVisibleC();
    }

    /**
     * Constructor to create a board with final arrangement, board is built empty.
     * @param ending Matrix of charts with the final objective arrangement of the board.
     */
    public Puzzle(char[][] ending) {
        this.width = ending.length;
        this.height = ending[0].length;
        this.board = new Tiles[height][width];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        this.actualEnding = ending;
        this.actualBoard = new char[height][width];
        setActualBoard();
        makeVisibleC();
    }

    /**
     * Constructor to create a board with objectives, it builds the board with the initial arrangement
     * @param start Matrix of charts with the initial arrangement of the board.
     * @param ending Matrix of charts with the final objective arrangement of the board.
     */
    public Puzzle(char[][] start, char[][] ending) {
        this.height = start.length;
        this.width = start[0].length;
        this.board = new Tiles[height][width];
        this.table = new Rectangle();
        this.missingSpace = start.length*start[0].length;
        this.actualEnding = ending;
        this.startBoard = start;
        this.actualBoard = new char[height][width];
        makeVisibleC();
        initialPrint();
        setActualBoard();
    }

    //* ------ Methods ------

    /**
     * Function to add tiles to the board, it checks if the position is valid and if it is empty.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     */
    public void addTile(int row, int column, String color) {
        if (row >= this.height || column >= this.width || row < 0 || column < 0 || board[row][column] != null || holesBoard[row][column] != null){
            System.out.println("Row or column not valid");
        } else {
            Tiles tile = new Tiles(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }

    /**
     * Function to delete tiles from the board, it checks if the position is valid and if there is a tile.
     * @param row It's the row of the tile objective.
     * @param column It's the column of the tile objective.
     */
    public void deleteTile(int row, int column) {
        if (row >= this.height || column >= this.width || row < 0 || column < 0 ){
            System.out.println("Row or column not valid");
        } else {
            Tiles tile = board[row][column];
            if (tile == null) {
                System.out.println("There is not valid tile in this position");
            }else {
                tile.makeInvisible();
                board[row][column] = null;
                this.missingSpace++;
            }
        }
        setActualBoard();
    }


    /**
     * Function to add glue to a tile. It checks if the position is valid and if there is a tile. Ignores the holes.
     * @param row It's the row of the tile.
     * @param column It's the column of the tile.
     */
//    public void addGlue(int row, int column) {
//        Tiles tile = board[row][column];
//        if (tile == null || tile.getGlued() || tile.getHole()) {
//            System.out.println("There is no tile in this position or it have glue already");
//        } else{
//            tile.setGlued(true);
//            if (column+1 >= this.height || board[row][column+1] == null){
//                tile.tilesGlued[0] = null; //DOWN
//            } else {
//                Tiles tileO = board[row][column+1];
//                tileO.tilesGlued[0] = tile; //DOWN
//            }
//            if (column-1 < 0 || board[row][column-1] == null){
//                tile.tilesGlued[1] = null; //UP
//            } else {
//                Tiles tileO = board[row][column-1];
//                tileO.tilesGlued[1] = tile; //UP
//            }
//            if (row+1 >= this.width || board[row+1][column] == null){
//                tile.tilesGlued[2] = null; //RIGHT
//            } else {
//                Tiles tileO = board[row+1][column];
//                tileO.tilesGlued[2] = tile; //RIGHT
//            }
//            if (row-1 < 0 || board[row-1][column] == null){
//                tile.tilesGlued[3] = null; //LEFT
//            } else {
//                Tiles tileO = board[row-1][column];
//                tileO.tilesGlued[3] = tile; //LEFT
//            }
//        }
//        setActualBoard();
//    }

//    public void deleteGlue(int row, int column) {
//        Tiles tile = board[row][column];
//        if (tile == null || !tile.getGlued() || tile.getHole()) {
//            System.out.println("There is no tile in this position");
//        } else{
//            tile.setGlued(false);
//            tile.tilesGlued[0] = null; //DOWN
//            tile.tilesGlued[1] = null; //UP
//            tile.tilesGlued[2] = null; //RIGHT
//            tile.tilesGlued[3] = null; //LEFT
//        }
//    }

    public void makeHole(int row, int column) {
        if (row >= this.height || column >= this.width || row < 0 || column < 0 || board[row][column] != null){
            System.out.println("Row or column not valid");
        } else {
            Holes tile = new Holes(column, row,this);
            holesBoard[row][column] = tile;
            this.missingSpace--;
        }
    }
    public void relocateTile(int[] from, int[] to) {
        int row = from[0];
        int column = from[1];
        int newRow = to[0];
        int newColumn = to[1];
        if (row >= this.height || column >= this.width || row < 0 || column < 0 ){
            System.out.println("Row or column not valid");
            System.out.println(row);
            System.out.println(column);
        }
        if (newRow >= this.height || newColumn >= this.width || newRow < 0 || newColumn < 0 ){
            System.out.println("Row or column objective not valid");
            System.out.println(newRow);
            System.out.println(newColumn);
        }
        Tiles tile = board[row][column];
        if (tile == null) {
            System.out.println("Tile or position no valid to move");
        } else{
            tile.setPosX(newColumn);
            tile.setPosY(newRow);
            String name = tile.getColor();
            deleteTile(row,column);
            addTile(newRow,newColumn,name);
        }
        setActualBoard();
    }

    /**
     * Function to tilt the board in a direction. It will move all the tiles to the direction selected.
     * If a hole it's in the way, the tiles will fall/eliminate.
     * @param direction It's the direction to tilt the board. It can be 'L' for left, 'R' for right, 'D' for down and 'U' for up.
     */
    public void tilt(char direction) {
        switch (direction){
            case 'L':
                moveLeft();
                break;
            case 'R':
                moveRight();
                break;
            case 'D':
                moveDown();
                break;
            case 'U':
                moveUp();
                break;
        }
    }
    private void moveRight() {
        for (int i = this.width - 1; i >= 0; i--) {
            for (int j = 0; j < this.height; j++) {
                Tiles tile = board[j][i];
                if (tile != null) {
                    tile.moveRight();
                }
            }
        }
    }
    private void moveLeft(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Tiles tile = board[j][i];
                if (tile != null) {
                    tile.moveLeft();
                    }
                }
            }
        }
    private void moveDown(){
        for (int i = this.height - 1; i >= 0; i--) {
            for (int j = 0; j < this.width; j++) {
                Tiles tile = board[i][j];
                if (tile != null){
                    tile.moveDown();
                }
            }
        }
    }
    private void moveUp(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tiles tile = board[i][j];
                if (tile != null) {
                    tile.moveUp();
                }
            }
        }
    }
    /**
     * Function to compare the actual arrangement of the board with the final arrangement if it exists.
     * If the final arrangement is not set, it will print a message.
     * Otherwise, it will compare the two arrangements and determinate equality.
     */
    public boolean isGoal() {
        if (actualEnding == null) {
            System.out.println("There is no goal to compare");
        } else {
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    if (actualBoard[i][j] != actualEnding[i][j]) {
                        //System.out.println("The board is not in the goal");
                        return false;
                    }
                }
            }
            System.out.println("The board is in the goal");
            finish();
        }
        return true;
    }


    /**
     * Function to print the actual arrangement of the board. It means, te internal function of the board.
     */
    public void actualArrangemment() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(actualBoard[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Function to get how many tiles can be placed in the board.
     */
    public void missPlacedTiles() {
        System.out.println(this.missingSpace);
    }

    /**
     * Function to make the board visible. Includes the table and the tiles.
     */
    public void makeVisible() {
        table.makeVisible();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (board[i][j] != null) {
                    board[i][j].makeVisible();
                }
            }
        }
    }

    /**
     * Function to make the board invisible. Includes the table and the tiles.
     */
    public void makeInvisible() {
        table.makeInvisible();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (board[i][j] != null) {
                    board[i][j].makeInvisible();
                }
            }
        }
    }

    /**
     * Function to finish the game. It will make the board invisible and print a message when the goal is complete.
     */
    public void finish() {
        //makeInvisible();
        System.out.println("Game was finished");
    }


    //* ------ Private Methods ------
    // used to complementary the others methods.

    /**
     * Function to set the actual arrangement of the board. it represents the internal arrangement of the board.
     */
    void setActualBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (board[i][j] != null) {
                    this.actualBoard[i][j] = board[i][j].getName();
                } else if (holesBoard[i][j] != null) {
                    this.actualBoard[i][j] = holesBoard[i][j].getName();
                } else {
                    this.actualBoard[i][j] = '.';
                }
            }
        }
    }

    /**
     * Function to add a tile to the board. It's a help function to make visible the initial board.
     */
    private void makeVisibleC(){
        table.makeVisible();
        table.changeSize((height*20)+height*3+2,(width*20)+width*3+2);
        table.changeColor("yellow");
        table.moveHorizontal(-3);
        table.moveVertical(-3);
    }

    //- missing do it with the final board
    private void initialPrint(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (startBoard[i][j] != '.') {
                    addTile(i,j,String.valueOf(startBoard[i][j]));
                }
            }
        }
    }



//    public int maxMoveGlued (Tiles tile, char direction){
//        int min = 999999999;
//        for (int i = 0; i < 4; i++){
//            if (tile.tilesGlued[i] != null){
//                Tiles tileO = tile.tilesGlued[i];
//                Tiles[] tiles = getNeighbors(tileO);
//                for (Tiles tl : tiles){
//                    min = switch (direction) {
//                        case 'R' -> Math.min(maxMoveRight(tl), min);
//                        case 'L' -> Math.min(maxMoveLeft(tl), min);
//                        case 'U' -> Math.min(maxMoveUp(tl), min);
//                        case 'D' -> Math.min(maxMoveDown(tl), min);
//                        default -> min;
//                    };
//                }
//            }
//        }
//        return min;
//    }


    public Tiles[] getNeighbors(Tiles tile){
        Tiles[] tiles = new Tiles[4];
        int posX = tile.getPosX();
        int posY = tile.getPosY();
        if (posX+1 < this.width && board[posY][posX+1] != null){
            tiles[0] = board[posY][posX+1]; //RIGHT
        }
        if (posX-1 >= 0 && board[posY][posX-1] != null){
            tiles[1] = board[posY][posX-1]; //LEFT
        }
        if (posY+1 < this.height && board[posY+1][posX] != null){
            tiles[2] = board[posY+1][posX]; //DOWN
        }
        if (posY-1 >= 0 && board[posY-1][posX] != null){
            tiles[3] = board[posY-1][posX]; //UP
        }
        return tiles;
    }


    //* ------ Getters and Setters ------

    public int getHeight() {
        return height;
    }

    public int getMissingSpace() {
        return this.missingSpace;
    }

    public char[][] getActualBoard() {
        return actualBoard;
    }

    public char[][] getEndingBoard() {
        return actualEnding;
    }

    public void setBoard(char[][] original){
        this.actualBoard=original;
    }

    public int getWidth() {
        return this.width;
    }

    public Tiles getTile(int row, int column){
        return board[row][column];
    }
    public Holes getHole(int row, int column){
        return holesBoard[row][column];
    }
}