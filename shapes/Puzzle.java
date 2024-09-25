/**
 * The initial project aims to develop an application that allows simulating a
 * situation inspired by Problem F of the 2023 Tilting international programming marathon
 * Tiles. In this simulator you want to test a special glue, Gummy Glue, which is used
 * applied on a tile causes it and the neighboring tiles to stick together.
 *
 * @author Andrés Felipe Chavarro Plazas
 * @author David Santiago Espinosa Rojas
 * @since 27-08-2024
 * @version Version 0.6
 */

public class Puzzle {

    //* ------ Variables ------
    private int height;
    private int width;
    public Tiles[][] board;
    private Rectangle table;
    private int missingSpace;
    private char[][] actualEnding;
    private char[][] startBoard;
    private char[][] actualboard;

    //* ------ Constructors ------

    /**
     * Constructor to create a board without objectives
     * @param height to set the height of the board.
     * @param width to set the width of the board.
     */
    public Puzzle(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new Tiles[width][height];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        this.actualboard = new char[width][height];
        setActualBoard();
        makeVisibleC();
    }

    /**
     * Constructor to create a board with objectives, board is built empty.
     * @param ending Matrix of charts with the final objective arrangement of the board.
     */
    public Puzzle(char[][] ending) {
        this.height = ending.length;
        this.width = ending[0].length;
        this.board = new Tiles[width][height];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        this.actualEnding = ending;
        this.actualboard = new char[width][height];
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
        this.board = new Tiles[width][height];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        this.actualEnding = ending;
        this.startBoard = start;
        this.actualboard = start;
        initialPrint();
        makeVisibleC();
    }

    //* ------ Methods ------

    /**
     * Function to add tiles to the board, it checks if the position is valid and if it is empty.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     */
    public void addTile(int row, int column, String color) {
        if (row >= this.width || column >= this.height || row < 0 || column < 0 || board[row][column] != null){
            System.out.println("Row or column not valid");
        } else {
            Tiles tile = new Tiles(column, row, color);
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
        setActualBoard();
    }

    /**
     * Function to relocate tiles in the board to a new position, it checks if the position is valid and if there is a tile.
     * @param from List with the row and column of the tile to move.
     *             The first element is the row and the second element is the column.
     * @param to List with the row and column of the objective position.
     *           The first element is the row and the second element is the column.
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
            tile.makeVisibleCreate();
        }
        setActualBoard();
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
            Tiles tile = new Tiles(column, row);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
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

    private void directionHorizontal(int place){
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
        setActualBoard();
    }

    private void directionVertical(int place){
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
        setActualBoard();
    }

    //public void tilt() {}

    /**
     * Function to compare the actual arrangement of the board with the final arrangement if it exists.
     * If the final arrangement is not set, it will print a message.
     * Otherwise, it will compare the two arrangements and determinate equality.
     */
    public void isGoal() {
        if (actualEnding == null) {
            System.out.println("There is no goal to compare");
        } else {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    if (actualboard[i][j] != actualEnding[i][j]) {
                        System.out.println("The board is not in the goal");
                        return;
                    }
                }
            }
            System.out.println("The board is in the goal");
            finish();
        }
    }


    /**
     * Function to print the actual arrangement of the board. It means, te internal function of the board.
     */
    public void actualArrangemment() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                System.out.print(actualboard[i][j]);
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
        makeInvisible();
        System.out.println("Game was finished");
    }


    //* ------ Private Methods ------
    // used to complementary the others methods.

    private boolean isFree(Tiles tile) {
        int row = tile.getPosX();
        int column = tile.getPosY();
        Tiles ng1 = board[row][column];
        Tiles ng2 = board[row][column+1];
        Tiles ng3 = board[row][column-1];
        Tiles ng4 = board[row+1][column];

        return ng1.getGlued() && ng2.getGlued() && ng3.getGlued() && ng4.getGlued();
    }

    private void setActualBoard() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (board[i][j] != null) {
                    this.actualboard[i][j] = board[i][j].getName();
                } else {
                    this.actualboard[i][j] = 'O';
                }
            }
        }
    }

    private void makeVisibleC(){
        table.makeVisible();
        table.changeSize((height*20)+height*3+2,(width*20)+width*3+2);
        table.changeColor("yellow");
        table.moveHorizontal(-3);
        table.moveVertical(-3);
    }

    private void initialPrint(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (startBoard[i][j] != 'O') {
                    addTile(i,j,String.valueOf(startBoard[i][j]));
                }
            }
        }
    }

    //* ------ Getters and Setters ------

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}