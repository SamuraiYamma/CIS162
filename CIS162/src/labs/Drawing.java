package labs;

import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel{

    private final int width = 800;
    private final int height = 600;

    private int inc = 0;

    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.setContentPane(new Drawing());
        //normally id use the variables i used earlier, but i dont want to deal
        // with making everything static when its not
        f.setSize(800, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2,
                dim.height/2-f.getSize().height/2);
        f.setResizable(false);
    }

    public void paintComponent(Graphics g){

        int locA, locB; //first car origin
        int locX, locY; //second car origin

        inc += 10;

        locA = 40 + inc;
        locB = height/2 - 40;

        locX = width/2 + inc;
        locY = height/2 + 120;

        // this statement required
        super.paintComponent(g);

        // optional: paint the background color (default is white)
        setBackground(new Color(101,236,255));

        //make road
        g.setColor(Color.darkGray);
        g.drawRect(0, height/2, width, height/2);
        g.fillRect(0, height/2, width, height/2);

        //car body
        g.setColor(Color.white);
        g.drawRect(locA, locB, 180,80);
        g.fillRect(locA, locB, 180, 80);

        //rear wheel
        g.setColor(Color.black);
        g.drawOval(locA + 20, locB + 70, 40, 40);
        g.fillOval(locA + 20, locB + 70, 40, 40);

        //front wheel
        g.setColor(Color.black);
        g.drawOval(locA + 135, locB + 70, 40, 40);
        g.fillOval(locA + 135, locB + 70, 40, 40);

        //poly arrays
        int[] xs = {locA + 225, locA + 225, locA + 255};
        int[] ys = {locB, locB + 40, locB + 40};

        //front body
        g.setColor(Color.red);
        g.drawRect(locA + 180, locB, 45, 80);
        g.fillRect(locA + 180, locB, 45, 80);
        g.drawRect(locA + 180 + 45, locB + 40, 30, 40);
        g.fillRect(locA + 180 + 45, locB + 40, 30, 40);
        g.drawPolygon(xs, ys, 3);
        g.fillPolygon(xs, ys, 3);

        //other front wheel
        g.setColor(Color.black);
        g.drawOval(locA + 185, locB + 70, 40, 40);
        g.fillOval(locA + 185, locB + 70, 40, 40);

        //logo
        g.drawString("Carter Transport", locA + 50, locB + 40);

        //car body
        g.setColor(Color.white);
        g.drawRect(locX, locY, 180,80);
        g.fillRect(locX, locY, 180, 80);

        //rear wheel
        g.setColor(Color.black);
        g.drawOval(locX + 20, locY + 70, 40, 40);
        g.fillOval(locX + 20, locY + 70, 40, 40);

        //front wheel
        g.setColor(Color.black);
        g.drawOval(locX + 135, locY + 70, 40, 40);
        g.fillOval(locX + 135, locY + 70, 40, 40);

        //poly arrays
        int[] xss = {locX + 225, locX + 225, locX + 255};
        int[] yss = {locY, locY + 40, locY + 40};

        //front body
        g.setColor(Color.blue);
        g.drawRect(locX + 180, locY, 45, 80);
        g.fillRect(locX + 180, locY, 45, 80);
        g.drawRect(locX + 180 + 45, locY + 40, 30, 40);
        g.fillRect(locX + 180 + 45, locY + 40, 30, 40);
        g.drawPolygon(xss, yss, 3);
        g.fillPolygon(xss, yss, 3);

        //other front wheel
        g.setColor(Color.black);
        g.drawOval(locX + 185, locY + 70, 40, 40);
        g.fillOval(locX + 185, locY + 70, 40, 40);

        //logo
        g.drawString("Bickford Transport", locX + 50, locY + 40);

    }
}