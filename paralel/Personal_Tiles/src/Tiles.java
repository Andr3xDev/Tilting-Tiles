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

    /**
     * Constructor for objects of class Tiles
     * @param posX
     * @param posY
     * @param name
     * @param color
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
        makeVisible();
    }

    /**
     * Constructor for objects of class Tiles
     * @param posX
     * @param posY
     */
    public Tiles(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = "Black";
        setName("X");
        this.holeTile = new Triangle();
        this.glued = false;
        this.hole = true;
        makeVisibleX();
    }

    /**
     * Function to glue tiles
     * @param tile
     */
    public void makeVisible() {
        tile.makeVisible();
        tile.changeColor(this.color);
        tile.changeSize(20, 20);
        tile.moveHorizontal(posX * 23);
        tile.moveVertical(posY * 23);
    }

    /**
     * Function to glue tiles
     */
    public void makeVisibleX() {
        holeTile.makeVisible();
        holeTile.changeColor(this.color);
        holeTile.changeSize(20,20);
        holeTile.moveHorizontal(posX * 23);
        holeTile.moveVertical(posY * 23);
    }



    /**
     * Function to make invisible the tiles
     */
    public void makeInvisible() {
        tile.makeInvisible();
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
    public String getName() {
        return this.name;
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

    public Rectangle getTile() {
        return tile;
    }

    public void setTile(Rectangle tile) {
        this.tile = tile;
    }

    // glued
    public boolean getGlued() {
        return glued;
    }
    public boolean setGlued(boolean glued) {
        return this.glued = glued;
    }

    // hole
    public boolean getHole() {
        return hole;
    }
    public void setHole(boolean hole) {
        this.hole = hole;
    }

}