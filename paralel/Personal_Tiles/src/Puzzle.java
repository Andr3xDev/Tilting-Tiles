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

    /**
     * Function to add glue to a tile. It checks if the position is valid and if there is a tile. Ignores the holes.
     * @param row It's the row of the tile.
     * @param column It's the column of the tile.
     */
    public void addGlue(int row, int column) {
        Tiles tile = board[row][column];
        if (tile == null || tile.getGlued() || tile.getHole()) {
            System.out.println("There is no tile in this position or it have glue already");
        } else{
            tile.setGlued(true);
            if (column+1 >= this.height || board[row][column+1] == null){
                tile.tilesGlued[0] = null; //abajo
            } else {
                tile.tilesGlued[0] = board[row][column+1]; //abajo
            }
            if (column-1 < 0 || board[row][column-1] == null){
                tile.tilesGlued[1] = null; //arriba
            } else {
                tile.tilesGlued[1] = board[row][column-1]; //arriba
            }
            if (row+1 >= this.width || board[row+1][column] == null){
                tile.tilesGlued[2] = null; //derecha
            } else {
                tile.tilesGlued[2] = board[row+1][column]; //derecha
            }
            if (row-1 < 0 || board[row-1][column] == null){
                tile.tilesGlued[3] = null; //izquierda
            } else {
                tile.tilesGlued[3] = board[row-1][column]; //izquierda
            }
        }
    }

    /**
     * Function to delete the glue of a tile. It checks if the position is valid and if there is a tile with glue already.
     * Holes are not glued.
     * @param row It's the row of the tile.
     * @param column It's the column of the tile.
     */
    public void deleteGlue(int row, int column) {
        Tiles tile = board[row][column];
        if (tile == null || !tile.getGlued() || tile.getHole()) {
            System.out.println("There is no tile in this position");
        } else{
            tile.setGlued(false);
            tile.tilesGlued[0] = null; //abajo
            tile.tilesGlued[1] = null; //arriba
            tile.tilesGlued[2] = null; //derecha
            tile.tilesGlued[3] = null; //izquierda
        }
    }

    /**
     * Function to make a hole in the board. It checks if the position is valid and if there is no tile or another hole.
     * @param row It's the row of the hole.
     * @param column It's the column of the hole.
     */
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

    /**
     * Function to tilt the board in a direction. It will move all the tiles to the direction selected.
     * If a hole it's in the way, the tiles will fall/eliminate.
     * @param direction It's the direction to tilt the board. It can be 'L' for left, 'R' for right, 'D' for down and 'U' for up.
     */
    public void tilt(char direction) {
        switch (direction){
            case 'L':
                directionHorizontal(0);
                break;
            case 'R':
                directionHorizontal(this.height-1);
                break;
            case 'D':
                directionVertical(this.width-1);
                break;
            case 'U':
                directionVertical(0);
                break;
        }
    }

    /**
     * Function to move the tiles to the left or right. It's used by Tilt to select the direction.
     * @param place It's the direcciont to move the tiles. 0 is left and height-1 is right.
     */
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
    }

    /**
     * Function to move the tiles to the up or down. It's used by Tilt to select the direction.
     * @param place It's the direcciont to move the tiles. 0 is up and width-1 is down.
     */
    private void directionVertical(int place){
        if (place == 0){ //ARRIBA
            for(int i = 0; i<height; i++){
                for (int j = 0; j<width; j++){
                    Tiles tile = board[j][i];
                    if (tile != null && !tile.getHole()){
                        relocateTile(new int[]{j,i},new int[]{place,i});
                        place ++;
                    }
                }
                place = 0;
            }
        }else { //ABAJO
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

    /**
     * Function to compare the actual arrangement of the board with the final arrangement if it exists.
     * If the final arrangement is not set, it will print a message.
     * Otherwise, it will compare the two arrangements and determinate equality.
     */
    public boolean isGoal() {
        if (actualEnding == null) {
            System.out.println("There is no goal to compare");
        } else {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    if (actualboard[i][j] != actualEnding[i][j]) {
                        System.out.println("The board is not in the goal");
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
     * Function to check if a tile without glue can move in any direction. It will print a message if the tile can not move.
     */
    public void canNotMove(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Tiles tile = board[i][j];
                if (tile != null && !tile.getGlued()){
                    if (maxMoveLeft(tile) == 0 && maxMoveRight(tile) == 0 && maxMoveUp(tile) == 0 && maxMoveDown(tile) == 0){
                        System.out.println("The tile in position "+i+","+j+" can not move");
                    }
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

    /**
     * Function to set the actual arrangement of the board. it represents the internal arrangement of the board.
     */
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

    //- Pendiende a adicion
    private void initialPrint(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (startBoard[i][j] != 'O') {
                    addTile(i,j,String.valueOf(startBoard[i][j]));
                }
            }
        }
    }


    private int maxMoveLeft(Tiles tile){
        int max = 0;
        int posX = tile.getPosX();
        for (int columnA = posX; columnA < this.width; columnA++){
            if (board[posX][columnA] == null){
                max++;
            } else if(board[posX][columnA].getHole()){
                deleteTile(posX,columnA);
                return max;
            }
        }
        return max;
    }

    private int maxMoveRight(Tiles tile){
        int max = 0;
        int posX = tile.getPosX();
        for (int columnA = 0; columnA < posX; columnA++){
            if (board[posX][columnA] == null){
                max++;
            } else if(board[posX][columnA].getHole()){
                deleteTile(posX,columnA);
                return max;
            }
        }
        return max;
    }

    private int maxMoveUp(Tiles tile){
        int max = 0;
        int posY = tile.getPosY();
        for (int columnA = 0; columnA < posY; columnA++){
            if (board[columnA][posY] == null){
                max++;
            } else if(board[columnA][posY].getHole()){
                deleteTile(posY,columnA);
                return max;
            }
        }
        return max;
    }

    private int maxMoveDown(Tiles tile){
        int max = 0;
        int posY = tile.getPosY();
        for (int columnA = posY; columnA < this.height; columnA++){
            if (board[columnA][posY] == null){
                max++;
            } else if(board[columnA][posY].getHole()){
                deleteTile(posY,columnA);
                return max;
            }
        }
        return max;
    }


    //* ------ Getters and Setters ------

    public int getHeight() {
        return height;
    }

    public int getMissingSpace() {
        return this.missingSpace;
    }

    public int getWidth() {
        return this.width;
    }
}
