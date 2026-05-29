import javafx.scene.control.Button;

public class Tile extends Button {
    
    private boolean flipped;
    private String fruit;
    private int x;
    private int y;
    
    public Tile(String fruit, int x, int y) {
        this.fruit = fruit;
        this.flipped = false;
        this.x = x;
        this.y = y;
        //make buttons bigger
        this.setPrefSize(50, 50);
        this.setText("");
    }
    
    public boolean equals(Tile other) {
        if (other.fruit.equals(fruit)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean getFlipped() {
        return flipped;
    }
    
    public String getFruit() {
        return fruit;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void flip() {
        flipped = !flipped;
        if(flipped){
            this.setText(fruit);
        }
        else{
            this.setText("");
        }
    }
}
