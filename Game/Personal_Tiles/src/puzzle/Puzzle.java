package puzzle;
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
 * @version Version 0.9
 */
public class Puzzle {

    //* ------ Variables ------
    private final int height;
    private final int width;
    private final Tiles[][] board;
    public Glues[][] gluesBoard;
    private char[][] actualGlue;
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
        this.gluesBoard = new Glues[height][width];
        this.actualGlue = new char[height][width];
        this.table = new Rectangle();
        this.missingSpace = width*height;
        this.actualBoard = new char[height][width];
        setActualBoard();
        setActualGlue();
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
        this.holesBoard = new Holes[height][width];
        this.gluesBoard = new Glues[height][width];
        this.table = new Rectangle();
        this.missingSpace = this.width*this.height;
        this.actualEnding = ending;
        this.actualBoard = new char[height][width];
        setActualBoard();
        //setActualGlue();
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
        this.holesBoard = new Holes[height][width];
        this.gluesBoard = new Glues[height][width];
        this.actualEnding = ending;
        this.startBoard = start;
        this.actualBoard = new char[height][width];
        makeVisibleC();
        initialPrint();
        //setActualGlue();
        setActualBoard();
    }



    //* ------ Methods ------
    /**
     * Function to add tiles to the board, it checks if the position is valid and if it is empty.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid.
     */
    public void addTile(int row, int column, String color) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 ||
                board[row][column] != null || holesBoard[row][column] != null){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Tiles tile = new Tiles(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }


    /**
     * Function to add tiles to the board, but it's a freelance tile.
     * It checks if the position is valid and if it is empty.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid.
     */
    public void addFreelance(int row, int column, String color) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 ||
                board[row][column] != null || holesBoard[row][column] != null){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Freelance tile = new Freelance(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }


    /**
     * Function to add tiles to the board, but it's a rough tile. It can't be tilted.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid.
     */
    public void addRough(int row, int column, String color) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 ||
                board[row][column] != null || holesBoard[row][column] != null){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Rough tile = new Rough(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }


    /**
     * Function to add tiles to the board, but it's a flying tile. It ignores the holes of the table.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid.
     */
    public void addFlying(int row, int column, String color) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 || board[row][column] != null ){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Flying tile = new Flying(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }


    /**
     * Function to add tiles to the board, but it's a fixed tile. It can't be moved or deleted.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid.
     */
    public void addFixed(int row, int column, String color) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 || board[row][column] != null){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Fixed tile = new Fixed(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }


    /**
     * Function to add tiles to the board, but it's a Temporal tile. It will be destroyed after try tilt twice.
     * @param row Set the row of the tile.
     * @param column Set the column of the tile.
     * @param color Set the color and name of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid.
     */
    public void addTemporal(int row, int column, String color) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 || board[row][column] != null){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Temporal tile = new Temporal(column,row,color,this);
            board[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }

    /**
     * Function to delete tiles from the board, it checks if the position is valid and if there is a tile.
     * @param row It's the row of the tile objective.
     * @param column It's the column of the tile objective.
     * @throws puzzleExceptions It throws an exception if the position is invalid or if there is no tile.
     */
    public void deleteTile(int row, int column) throws puzzleExceptions {
        if (row >= this.height || column >= this.width || row < 0 || column < 0 ){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Tiles tile = getTile(row,column);
            if (tile == null) {
                throw new puzzleExceptions(puzzleExceptions.INVALID_DELETE);
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
    public void addGlue(int row, int column) throws puzzleExceptions{
        if(holesBoard[row][column] == null && board[row][column] != null) {
            gluesBoard[row][column] = new Glues(row, column, this, true);
            Glues glue = gluesBoard[row][column];
            glue.addNeighbour(row, column);
            setActualGlue();
        }else{
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        }
    }


    /**
     * Function to get the glue of the board. It will print the position of the glues.
     */
    public void getGlue(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (gluesBoard[i][j] != null){
                    System.out.println(gluesBoard[i][j]);
                }
            }
        }
    }


    /**
     * Function to delete the glue from a tile. It checks if the position is valid and if there is a glue.
     * @param row It's the row of the tile.
     * @param column It's the column of the tile.
     * @throws puzzleExceptions It throws an exception if the position is invalid or if there is no glue.
     */
    public void deleteGlue(int row, int column) throws puzzleExceptions {
        if(holesBoard[row][column] == null && board[row][column] != null && gluesBoard[row][column].getType() == 'g') {
            gluesBoard[row][column] = null;
            Glues glue = gluesBoard[row][column];
            glue.eliminateNeighbour(row, column);
            setActualGlue();
        }else{
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        }
    }


    /**
     * Function to make a hole in the board. It makes the tiles fall from the board.
     * @param row It's the row of the hole.
     * @param column It's the column of the hole.
     * @throws puzzleExceptions It throws an exception if the position is invalid or if there is a tile.
     */
    public void makeHole(int row, int column) throws puzzleExceptions{
        if (row >= this.height || column >= this.width || row < 0 || column < 0 || board[row][column] != null){
            throw new puzzleExceptions(puzzleExceptions.INVALID_POS);
        } else {
            Holes tile = new Holes(column, row,this);
            holesBoard[row][column] = tile;
            this.missingSpace--;
        }
        setActualBoard();
    }


    /**
     * Function to tilt the board in a direction. It will move all the tiles to the direction selected.
     * If a hole it's in the way, the tiles will fall/eliminate.
     * @param direction It's the direction to tilt the board. It can be 'L' for left,
     *                  'R' for right, 'D' for down and 'U' for up.
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
            default:
                System.out.println("Error: Invalid direction");
        }
    }


    /**
     * Function to move all the tiles to the right.
     */
    private void moveRight() {
        for (int i = this.width - 1; i >= 0; i--) {
            for (int j = 0; j < this.height; j++) {
                Tiles tile = board[j][i];
                if (tile != null) {
                    try {
                        tile.moveRight();
                    } catch (puzzleExceptions e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    /**
     * Function to move all the tiles to the left.
     */
    private void moveLeft(){
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Tiles tile = board[j][i];
                if (tile != null) {
                    try {
                        tile.moveLeft();
                    } catch (puzzleExceptions e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    /**
     * Function to move all the tiles down.
     */
    private void moveDown(){
        for (int i = this.height - 1; i >= 0; i--) {
            for (int j = 0; j < this.width; j++) {
                Tiles tile = getTile(i,j);
                if (tile != null){
                    try {
                        tile.moveDown();
                    } catch (puzzleExceptions e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    /**
     * Function to move all the tiles up.
     */
    private void moveUp(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tiles tile = board[i][j];
                if (tile != null) {
                    try {
                        tile.moveUp();
                    } catch (puzzleExceptions e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    /**
     * Function to compare the actual arrangement of the board with the final arrangement if it exists.
     * If the final arrangement is not set, it will print a message.
     * Otherwise, it will compare the two arrangements and determinate equality.
     */
    public boolean isGoal() throws puzzleExceptions {
        try {
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    if (board[i][j] != null && board[i][j].getName() != actualEnding[i][j]) {
                        return false;
                    }
                }
            }
            System.out.println("The goal was reached");
            return true;
        } catch (NullPointerException e){
            throw new puzzleExceptions(puzzleExceptions.NO_GOAL);
        }
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


    //! MISSING DOC
    public void actualGlue() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(actualGlue[i][j]);
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
                if (holesBoard[i][j] != null) {
                    holesBoard[i][j].makeInvisible();
                }
                if (gluesBoard[i][j] != null) {
                    gluesBoard[i][j].makeInvisible();
                }
            }
        }
    }


    /**
     * Function to finish the game. It will make the board invisible and print a message when the goal is complete.
     */
    public void finish() {
        System.out.println("Game was finished");
        System.exit(0);
    }




    //* ------ Private Methods ------
    // used to complementary the others methods.


    /**
     * Function to set the actual arrangement of the board. it represents the internal arrangement of the board.
     */
    public void setActualBoard() {
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
     * Function to set the actual glued board, it represents the internal connexions of the glue.
     */
    public void setActualGlue(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (gluesBoard[i][j] != null) {
                    this.actualGlue[i][j] = gluesBoard[i][j].getType();
                } else if (gluesBoard[i][j] == null) {
                    this.actualGlue[i][j] = '.';
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


    /**
     * Function to print the initial arrangement of the board. It's a help function to make visible the initial board.
     */
    private void initialPrint(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (startBoard[i][j] != '.') {
                    try{
                        addTile(i,j,setColorConstructor(String.valueOf(startBoard[i][j])));
                    } catch (puzzleExceptions e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    /**
     * Function to set the color of the tiles. It's a help function to make visible the initial board.
     * @param charName It's the initial char of the color.
     * @return It returns the name of the color.
     */
    private String setColorConstructor(String charName){
        return switch (charName) {
            case "r" -> "red";
            case "g" -> "green";
            case "b" -> "blue";
            case "w" -> "white";
            default -> "black";
        };
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

    public Glues getGlue(int i, int i1) {
        return gluesBoard[i][i1];
    }
}