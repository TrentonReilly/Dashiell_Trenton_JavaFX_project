import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;


/**
 * 6 by 6 memory tile game with time limit
 * 
 * <p> Users use visual display to click and flip two tiles. If they match
 * then they stay upright and can't be reflipped. Once the 3 minute time
 * limit is done, the board is checked to make sure all tiles are 
 * right side up. </p>
 * 
 * @author Trenton Reilly
 * @author Dashielle Baalman
 * @version 1.0
 * @since 1.0
*/
public class MemoryTiles {
    public static void main(String[] args) {
        Tile[][] cards = new Tile[4][4];
        GridPane grid = new GridPane();
        
        ArrayList<String> fruits = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            fruits.add("🍎");
            fruits.add("🍒");
            fruits.add("🍓");
            fruits.add("🍇");
            fruits.add("🍌");
            fruits.add("🫐");
            fruits.add("🍊");
            fruits.add("🍍");
        }
        Collections.shuffle(fruits);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cards[i][j] = new Tile(fruits.get(i*4+j));
                fruits.remove(i*4+j);
            }
        }
    }
}
