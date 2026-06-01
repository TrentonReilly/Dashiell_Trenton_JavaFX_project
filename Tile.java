import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * class to store and manage tiles for a memory game
 * 
 * <p> this class manages and stores tiles to be used in a memory
 * tiled game. They are images with visuals that can be clicked, 
 * flipped, and checked for equals with other tiles</p>
 * 
 * @author Trenton Reilly
 * @author Dashiell Baalman
 * @version 1.0
 * @since 1.0
*/
public class Tile extends Button {
    
    private boolean flipped;
    private Image fruit;
    private String fruitFile;
    private int x;
    private int y;
    
    /**
     * constructs a tile with its coordinates, image, fruit image file 
     * location, and a boolean to see if its flipped or not.
     * 
     * @param fruitFile the name of the image path to a file with fruit image
     * @param x the column of the tile in the grid
     * @param y the row of the tile in the grid
     * @pre fruitFile correctly points to a file
     */
    public Tile(String fruitFile, int x, int y) {
        this.fruitFile = fruitFile;
        this.fruit = new Image(getClass().getResourceAsStream(fruitFile));
        this.flipped = false;
        this.x = x;
        this.y = y;
        // make buttons bigger
        this.setPrefSize(100, 100);
        
    }
    
    /**
     * checks if one tile object is equal to another
     * 
     * @param other A tile object that will be compared with the object called on.
     * @return a boolean on if one tile is equal to another if they have the same file path.
     * 
     */
    public boolean equals(Tile other) {
        if (other.fruitFile.equals(fruitFile)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * getter method that returns if a tile is flipped or not
     * 
     * @returns the flipped boolean variable for the object called on.
     */ 
    public boolean getFlipped() {
        return flipped;
    }
    
    /**
     * getter method that returns x coordinate of a tile
     * 
     * @returns the X value of the tiled called upon
     */
    public int getX() {
        return x;
    }
    
    /**
     * getter method that returns y coordinate of a tile
     * 
     * @returns the Y value of the tiled called upon
     */
    public int getY() {
        return y;
    }
    
    /**
     * flips the tile that is called upon and updates characteristics
     * <p> changes the flipped variable to its opposite. If it flips to be
     * face up, then it makes the buttons graphic its fruit image and
     * make sure it can be seen. Updates the images view to fit the
     * button. If unflipped, removes the view to appear blank</p>
     * 
     * 
     */
    public void flip() {
        flipped = !flipped;
        if (flipped) {
            ImageView view = new ImageView(fruit);
            view.setPreserveRatio(true);
            view.setFitWidth(50);
            view.setFitHeight(50);
            this.setGraphic(view);
        }
        else { 
            this.setGraphic(null);
        }
    }
}
