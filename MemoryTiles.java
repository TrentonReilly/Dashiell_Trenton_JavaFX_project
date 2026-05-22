import javax.swing.*;



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
public class MemoryTiles{
    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame f= new JFrame();
        
        JButton b1 = new JButton("Hello, World!");
        b1.setBounds(90, 100, 180, 40);
        f.add(b1);

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}
