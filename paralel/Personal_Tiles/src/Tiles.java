/**
 * Class to represent the tiles of the board. It has the attributes to represent the tiles in the GUI board.
 * Also, it has the attributes to represent the holes in the GUI board.
 * The class has the methods to make visible and invisible the tiles and holes.
 *
 * @author Andrés Felipe Chavarro Plazas
 * @author David Santiago Espinosa Rojas
 * @since 27-08-2024
 * @version 0.7
 */

public class Tiles {
    //* attributes of the class
    private int posX;
    private int posY;
    private String name;
    private String color;
    private Rectangle tile;
    private Triangle holeTile;
    private boolean glued;
    public Tiles[] tilesGlued;
    private boolean hole;
    private boolean visible;

    /**
     * Constructor for Tiles of class Board. Most part of the attributes are initialized by default.
     * name is initialized by the first letter of the color.
     * @param posX Row position to represent the tile in the GUI board.
     * @param posY Column position to represent the tile in the GUI board.
     * @param color Color and the name of the tile.
     */
    public Tiles(int posX, int posY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        setName(color);
        this.tile = new Rectangle();
        this.glued = false;
        this.hole = false;
        this.tilesGlued = new Tiles[4];
        this.visible = true;
        makeVisibleCreate();
    }

    /**
     * Constructor for the holes of class Board. Most part of the attributes are initialized by default.
     * X is the name of the hole.
     *
     * @param posX row position to represent the hole in the GUI board.
     * @param posY column position to represent the hole in the GUI board.
     */
    public Tiles(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = "Black";
        setName("X");
        this.holeTile = new Triangle();
        this.glued = false;
        this.hole = true;
        this.visible = true;
        makeVisibleX();
    }

    /**
     * Function to make visible the tiles. It's a help function to make invisible the board.
     */
    public void makeVisibleCreate() {
        tile.makeVisible();
        tile.changeColor(this.color);
        tile.changeSize(20, 20);
        tile.moveHorizontal(posX * 23);
        tile.moveVertical(posY * 23);
    }

    /**
     * Function to make visible the holes. It's a help function to make invisible the board.
     */
    public void makeVisibleX() {
        holeTile.makeVisible();
        holeTile.changeColor(this.color);
        holeTile.changeSize(20,20);
        holeTile.moveHorizontal(posX * 23);
        holeTile.moveVertical(posY * 23);
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

    // glued
    public boolean getGlued() {
        return glued;
    }
    public void setGlued(boolean glued) {
        this.glued = glued;
    }

    // hole
    public boolean getHole() {
        return hole;
    }
    public void setHole(boolean hole) {
        this.hole = hole;
    }

    // visible
    public boolean getVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTilesGlued(Tiles[] tilesGlued) {
        this.tilesGlued = tilesGlued;
    }
    public Tiles[] getTilesGlued() {
        return tilesGlued;
    }
}