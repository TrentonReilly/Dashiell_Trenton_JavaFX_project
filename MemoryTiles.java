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
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * 6 by 6 memory tile game with time limit
 * 
 * <p> Users use visual display to click and flip two tiles. If they match
 * then they stay upright and can't be reflipped. Once the 3 minute time
 * limit is done, the board is checked to make sure all tiles are 
 * right side up. </p>
 * 
 * @author Trenton Reilly
 * @author Dashiell Baalman
 * @version 1.0
 * @since 1.0
*/
public class MemoryTiles extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    //is outside start b/c needs to be modified throughout
    private Tile selected = new Tile("", -1, -1);
    @Override
    public void start(Stage stage) {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        PauseTransition timer = new PauseTransition(Duration.seconds(1));
        Button timerDisplay = new Button("");
        //array list for our tiles
        Tile[][] cards = new Tile[4][4];
        //array list for our fruits that adds each fruit twice.
        ArrayList<String> fruits = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            fruits.add("apple.png");
            fruits.add("banana.png");
            fruits.add("cherry.png");
            fruits.add("grape.png");
            fruits.add("orange.png");
            fruits.add("pineapple.png");
            fruits.add("strawberry.png");
            fruits.add("watermelon.png");
        }
        Collections.shuffle(fruits);
        //adds all elements of fruit list to make tiles for cards list
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cards[i][j] = new Tile(fruits.get(i*4+j), j, i);
            }
        }
        
        
        //making grid
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.add(timerDisplay, 0, 0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid.add(cards[i][j], j, i+1);
            }
        }
        
        
        stage.setTitle("Memory Tiles 4 by 4");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // local variables need to be final in lambda expression (used ai to understand error)
                final int tempJ = j;
                final int tempI = i;
                Tile currentTile = cards[tempJ][tempI];
                cards[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (selected.getX() < 0) {
                            selected = cards[tempI][tempJ];
                        } else {
                            cards[tempI][tempJ].flip();
                            selected.flip();
                            pause.setOnFinished(event2 -> {
                                if (!cards[tempI][tempJ].equals(selected)) {
                                    cards[tempI][tempJ].flip();
                                    selected.flip();
                                }
                                selected = new Tile("", -1, -1);
                                grid.setDisable(false);
                            });
                            grid.setDisable(true);
                            pause.play();
                        }
                    }
                });
            }
        }
        
        var wrapper = new Object(){int timerCount = 120;};
        timer.setOnFinished(event3 -> {
            if (wrapper.timerCount > 0) {
                wrapper.timerCount--;
                timerDisplay.setText(Integer.toString(wrapper.timerCount));
                timer.play();
            } else {
                grid.setDisable(true);
                boolean won = true;
                for (Tile[] row : cards){
                    for (Tile card : row) {
                        if (!card.getFlipped()) {
                            won = false;
                        }
                    }
                }
                if (won) {
                    timerDisplay.setText("You won!");
                } else {
                    timerDisplay.setText("You lost!");
                }
            }
        });
        timer.play();
        
        stage.setScene(new Scene(grid));
        stage.show();
    }
}
