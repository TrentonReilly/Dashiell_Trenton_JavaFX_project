//import all of the javafx packages needed
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
    
    //launches javafx application
    public static void main(String[] args) {
        launch(args);
    }
    //is outside start b/c needs to be modified throughout
    private Tile selected = new Tile("", -1, -1);
    
    /**
     * Initializes and displays the primary stage for the 6 x 6 memory tile game
     * 
     * @param stage the primary stage for the application where the gridpane and scene
     * can be seen.
     */
    @Override
    public void start(Stage stage) {
        //pause timer to make sure tiles stay flipped for 2 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        //make timer for counting down by 1 displayed on a button
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
        //shuffles tiles randomly for each game
        Collections.shuffle(fruits);
        //adds all elements of fruit list to make tiles for cards list
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //make tile for each index, i * 4 + j to convert from 2d to 1d index
                cards[i][j] = new Tile(fruits.get(i*4+j), j, i);
            }
        }
        
        
        //make the grid for all of the visuals and button locations
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        //add timer to top left
        grid.add(timerDisplay, 0, 0);
        //add all of the premade tiles to the grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid.add(cards[i][j], j, i+1);
            }
        }
        
        //changes window bar to have correcy title
        stage.setTitle("Memory Tiles 4 by 4");
        //loop through each tile button to give it a handler for when clicked
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // local variables need to be final in lambda expression (used ai to understand error)
                final int tempJ = j;
                final int tempI = i;
                Tile currentTile = cards[tempJ][tempI];
                cards[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    //when clicked, make that tile selected if no other tile is selected
                    @Override
                    public void handle(ActionEvent event) {
                        if (selected.getX() < 0) {
                            selected = cards[tempI][tempJ];
                        } 
                        else {
                            //if one already selected, flip both up
                            cards[tempI][tempJ].flip();
                            selected.flip();
                            //update pause to take action when finished
                            pause.setOnFinished(event2 -> {
                                //if not the same then unflip them
                                if (!cards[tempI][tempJ].equals(selected)) {
                                    //reflip them down
                                    cards[tempI][tempJ].flip();
                                    selected.flip();
                                }
                                //update selected to point nowhere in grid
                                selected = new Tile("", -1, -1);
                                //make it so you can interact with grid again
                                grid.setDisable(false);
                            });
                            //disable the grid before pausing
                            grid.setDisable(true);
                            //pause for 2 seconds 
                            pause.play();
                        }
                    }
                });
            }
        }
        
        //make wrapper because inside lambda variables need to be final or effectively final
        //wrapper alows it to change variable outside of the lambda instead of inside
        var wrapper = new Object(){int timerCount = 120;};
        //keep running 1 second timers unless timer is finished
        timer.setOnFinished(event3 -> {
            if (wrapper.timerCount > 0) {
                wrapper.timerCount--;
                timerDisplay.setText(Integer.toString(wrapper.timerCount));
                timer.play();
            } else {
                //disable grid once timer runs out
                grid.setDisable(true);
                boolean won = true;
                //checks if all tiles in cards is flipped
                for (Tile[] row : cards){
                    for (Tile card : row) {
                        if (!card.getFlipped()) {
                            won = false;
                        }
                    }
                }
                //gives message on if all tiles are flipped or not.
                if (won) {
                    timerDisplay.setText("You won!");
                } else {
                    timerDisplay.setText("You lost!");
                }
            }
        });
        //visually start the timer
        timer.play();
        
        //put grid layout of buttons into window
        stage.setScene(new Scene(grid));
        //displays window on the screne
        stage.show();
    }
}
