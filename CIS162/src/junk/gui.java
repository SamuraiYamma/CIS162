package junk;
/**
 * @author SamuraiYamma
 * @description the view of our program
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class gui extends JFrame{
    private JFrame game;
    private JPanel panel;
    private JPanel gameView;
    private Image backgroundImage;
    private static final String IMG_PATH = "img/background8bit.png";

    public void startGame(){
        createFrame();
//        makeView();
    }

    /**
     * Creates the frame of our game
     */
    private void createFrame(){
        game = new JFrame("Basic Game");
        panel = new JPanel();
        game.getContentPane().add(panel);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setSize(new Dimension(700, 720));
        game.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        game.setLocation(dim.width/2-game.getSize().width/2,
                dim.height/2-game.getSize().height/2);
    }

    /**
     * Creates the view of our game
     */
    private void makeView() throws IOException{
        gameView = new JPanel();

        backgroundImage = ImageIO.read(new File(IMG_PATH));
//        super.(backgroundImage);
        panel.add(gameView);
    }

    /**
     * Creates the controller of our game
     */
    private void addControls(){

    }

    //helper methods

}
