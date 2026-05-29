import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {
    
    private boolean flipped;
    private Image fruit;
    private String fruitFile;
    private int x;
    private int y;
    
    
    public Tile(String fruitFile, int x, int y) {
        this.fruitFile = fruitFile;
        this.fruit = new Image(getClass().getResourceAsStream(fruitFile));
        this.flipped = false;
        this.x = x;
        this.y = y;
        //make buttons bigger
        this.setPrefSize(50, 50);
        
    }
    
    public boolean equals(Tile other) {
        if (other.fruitFile.equals(fruitFile)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean getFlipped() {
        return flipped;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void flip() {
        flipped = !flipped;
        if (flipped) {
            ImageView view = new ImageView(fruit);
            view.setPreserveRatio(true);
            view.fitWidthProperty().bind(this.widthProperty());
            view.fitHeightProperty().bind(this.heightProperty());
            this.setGraphic(view);
        }
        else { 
            this.setGraphic(null);
        }
    }
}
