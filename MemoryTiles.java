import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.layout.GridPane;


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
public class MemoryTiles extends Application{
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        
        //array list for our tiles
        Tile[][] cards = new Tile[4][4];
        //array lost for our fruits that adds each fruit twice.
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
        //adds all elements of fruit list to make tiles for cards list
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cards[i][j] = new Tile(fruits.get(i*4+j));
            }
        }
        
        
        //making grid
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                grid.add(cards[i][j], j, i);
                
            }
        }
        
        stage.setTitle("Memory Tiles 4 by 4");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                cards[i][j].setOnAction(new EventHandler<ActionEvent>()) {
                    @Override
                    public void handle(ActionEvent event) {
                        
                    }
                }
            }
        }
        
        
        
        
        
    }
}
