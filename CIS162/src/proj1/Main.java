package proj1;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ethan Carter
 *
 * @description
 * Business Card project for GVSU CIS 162 under supervision of Dr. Posada and Dr. Grissom
 */
public class Main extends JFrame{

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Business Card");
        frame.setSize(523, 470);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //make a panel and add it
        Panel panel = new Panel();
        frame.getContentPane().add(panel);

        //make it open in the middle of your screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setResizable(true);
        frame.setVisible(true);
    }
}
