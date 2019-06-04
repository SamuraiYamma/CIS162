package proj1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for managing controls, view
 * and instance variables. Not ideal, but it works.
 *
 * Many variables and methods are not used, but they exist should
 * there ever be a need to add additional functionality.
 *
 * No comments are provided on getters and setters.
 */
public class Panel extends JPanel {

    //MENU VAR
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu viewMenu;
    private JMenuItem printItem;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem quitItem;
    private JMenuItem editToggleItem;

    //panels
    private JPanel editPanel;

    //listeners
    private MenuListener menuListener;
    private EditListener editListener;

    //file var
    private String fileLoc;
    private String picLoc;

    //content var: DEFAULT
    //background
    private int borderWidth, borderLength;
    Color color = Color.lightGray;

    //picture
    private int profileXValue, profileYValue;

    //logo
    private int logoXValue, logoYValue;

    //text
    private int textXValue, textYValue;
    private String name, title, information;

    //SWITCHES
    private boolean hasChangedSwitch = false; //DEFAULT
    private boolean editToggle = false; //DEFAULT

    //BUTTONS
    private JButton applyChanges;
    private JButton changePicture;

    //FIELDS
    private JTextField logoXArea, logoYArea, profileXArea, profileYArea, textXArea,
            textYArea, nameArea, titleArea, informationArea;


    /**
     * Panel constructor
     * This creates the two panels you see (one of them is the toggled
     * edit panel) as well as the menu bar. default variables are initialized
     * here, look for DEFAULT in the comments to find them.
     */
    public Panel(){
        //set layout
        setLayout(new BorderLayout());

        //menu bar
        menuBar = new JMenuBar();

        //menu
        fileMenu = new JMenu("File");
        viewMenu = new JMenu("View");

        //menu items
        printItem = new JMenuItem("Print");
        openItem = new JMenuItem("Open File");
        saveItem = new JMenuItem("Save File");
        quitItem = new JMenuItem("Quit");

        //ENABLE OR DISABLE MENU ITEMS HERE
        printItem.setEnabled(false);
        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        quitItem.setEnabled(true);

        //clicking on this should toggle if it says On or Off
        editToggleItem = new JMenuItem("Toggle Edit Mode: Off");

        //add items to menu
        fileMenu.add(printItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(quitItem);

        viewMenu.add(editToggleItem);

        //add menu to menu bar
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        //add menu bar to panel
        add(menuBar, BorderLayout.NORTH);

        //menu listeners
        menuListener = new MenuListener();

        //add listeners
        printItem.addActionListener(menuListener);
        openItem.addActionListener(menuListener);
        saveItem.addActionListener(menuListener);
        quitItem.addActionListener(menuListener);
        editToggleItem.addActionListener(menuListener);

        //content
        borderWidth = 500; //DEFAULT
        borderLength = 300; //DEFAULT

        profileXValue = 60; //DEFAULT
        profileYValue = 45; //DEFAULT

        logoXValue = 310; //DEFAULT
        logoYValue = 45; //DEFAULT

        textXValue = 300; //DEFAULT
        textYValue = 260; //DEFAULT

        name = "Ethan Carter"; //DEFAULT
        title = "Stack Slave"; //DEFAULT
        information = "SamuraiYamma@github.com - 616.350.xxxx"; //DEFAULT
        picLoc = "profile.png"; //DEFAULT

        //buttons
        applyChanges = new JButton("Apply Changes");
        changePicture = new JButton("Change Picture");
        changePicture.setEnabled(false); //DISABLED UNTIL FUTURE VERSIONS

        //edit listener
        EditListener editListener = new EditListener();

        //add listeners
        applyChanges.addActionListener(editListener);
        changePicture.addActionListener(editListener);

        //labels
        JLabel profileXLabel = new JLabel("Profile Picture X-Value: ");
        JLabel profileYLabel = new JLabel("Profile Picture Y-Value: ");
        JLabel logoXLabel = new JLabel("Logo X-Value: ");
        JLabel logoYLabel = new JLabel("Logo Y-Value: ");
        JLabel textXLabel = new JLabel("Text X-Value: ");
        JLabel textYLabel = new JLabel("Text Y-Value: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel titleLabel = new JLabel("Title: ");
        JLabel informationLabel = new JLabel("Information: ");

        //fields
        logoXArea = new JTextField("" + getLogoXValue());
        logoYArea = new JTextField("" + getLogoYValue());
        profileXArea = new JTextField(""+ getProfileXValue());
        profileYArea = new JTextField("" + getProfileYValue());
        textXArea = new JTextField();
        textYArea = new JTextField();
        nameArea = new JTextField();
        titleArea = new JTextField();
        informationArea = new JTextField();

        //edit layout
        editPanel = new JPanel();
        editPanel.setLayout(new FlowLayout()); //This layout kinda sucks
        editPanel.setVisible(false);

        //add items to edit pane
        //picture stuff
        editPanel.add(profileXLabel);
        editPanel.add(profileXArea);
        editPanel.add(profileYLabel);
        editPanel.add(profileYArea);
        //logo stuff
        editPanel.add(logoXLabel);
        editPanel.add(logoXArea);
        editPanel.add(logoYLabel);
        editPanel.add(logoYArea);
        //apply changes
        editPanel.add(applyChanges);

        //add edit pane
        add(editPanel, BorderLayout.SOUTH);
        editPanel.setPreferredSize(new Dimension(500, 90));

        repaint();
    }

    /**
     * Draws the business card
     * @param graphics
     */
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics); //DO NOT DELETE

        setBackground(new Color(49,48,49)); //Contrast

        //Background
        graphics.setColor(color);
        graphics.drawRect(10,30, borderWidth, borderLength);
        graphics.fillRect(10,30, borderWidth, borderLength);

        //Text
        Font plain = new Font("serif", Font.PLAIN, 20);
        Font bold = new Font("serif", Font.BOLD, 20);
        Font italic = new Font("serif", Font.ITALIC, 20);

        graphics.setColor(Color.BLACK);
        graphics.setFont(bold);
        graphics.drawString(name, textXValue, textYValue);
        graphics.setFont(italic);
        graphics.drawString(title, textXValue + 18, textYValue + 22);
        graphics.setFont(plain);
        graphics.drawString(information, 50, textYValue + 44);

        //Logo
        graphics.setColor(Color.BLACK);
        graphics.drawOval(logoXValue, logoYValue, 150, 150);
        graphics.fillOval(logoXValue, logoYValue, 150, 150);
        graphics.setColor(color);
        graphics.drawOval(logoXValue + 5, logoYValue + 5, 140, 140);
        graphics.fillOval(logoXValue + 5, logoYValue + 5, 140, 140);
        graphics.setColor(Color.BLACK);
        graphics.drawOval(logoXValue + 37, logoYValue + 45, 80, 65);
        graphics.fillOval(logoXValue + 37, logoYValue + 45, 80, 65);
        graphics.drawRect(logoXValue + 62, logoYValue + 80, 30, 68);
        graphics.fillRect(logoXValue + 62, logoYValue + 80, 30, 68);
        graphics.fillOval(logoXValue + 45, logoYValue + 41, 15, 20);
        graphics.fillOval(logoXValue + 92, logoYValue + 41, 15, 20);

        //Photo
        BufferedImage photo = null;
        try{
            File file = new File(picLoc);
            photo = ImageIO.read(file);
        } catch (IOException e){
            //DISPLAY SOME ERROR
            System.out.println("Error reading image file"); //CHANGE THIS LATER
        }
        graphics.fillRect(profileXValue - 5, profileYValue - 5, 195, 220);
        graphics.drawImage(photo, profileXValue, profileYValue, 185, 210, null);
    }

    /**
     * MenuListener is responsible for responding to an actions
     * derived from clicking the menu options
     */
    private class MenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == printItem){
                //This prints, I think...
                File file = new File("C:\\document.doc");
                try {
                    Desktop.getDesktop().print(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (actionEvent.getSource() == openItem){
                //OPEN AN OPEN FILE DIALOGUE
            }
            else if (actionEvent.getSource() == saveItem){
                //OPEN A SAVE FILE DIALOGUE
            }
            else if (actionEvent.getSource() == quitItem){
                if (hasChangedSwitch){
                    //OPEN SAVE FILE PROMPT
                }
                //QUIT
                System.exit(0);
            }
            else if (actionEvent.getSource() == editToggleItem){
                if (!editToggle){
                    editToggle = !editToggle;
                    editToggleItem.setText("Toggle Edit Mode: On");
                    editPanel.setVisible(true);
                }
                else if(editToggle){
                    editToggle = !editToggle;
                    editToggleItem.setText("Toggle Edit Mode: Off");
                    editPanel.setVisible(false);
                }
            }
            else {
                //DISPLAY ERROR CODE
                //KEEP RUNNING
            }

            repaint();
        }
    }

    /**
     * EditListener is responsible for handling inputs from the
     * edit panel and responding accordingly.
     */
    private class EditListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == applyChanges){
                hasChangedSwitch = true;

                try{
                    //location of picture
                    setProfileXValue(Integer.parseInt(profileXArea.getText()));
                    setProfileYValue(Integer.parseInt(profileYArea.getText()));

                    //location of logo
                    setLogoXValue(Integer.parseInt(logoXArea.getText()));
                    setLogoYValue(Integer.parseInt(logoYArea.getText()));

                    //TODO: Add the rest of the options
                }
                catch (Exception e){ //Lazy excuse for a catch
                    System.out.println("Error in applying values");
                }

            }
            repaint();
        }
    }

    public String getFileLoc() {
        return fileLoc;
    }

    public void setFileLoc(String fileLoc) {
        this.fileLoc = fileLoc;
    }

    public String getPicLoc() {
        return picLoc;
    }

    public void setPicLoc(String picLoc) {
        this.picLoc = picLoc;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getBorderLength() {
        return borderLength;
    }

    public void setBorderLength(int borderLength) {
        this.borderLength = borderLength;
    }

    public int getProfileXValue() {
        return profileXValue;
    }

    /**
     * This method sets the instance profileXValue to what's
     * @param profileXValue
     */
    public void setProfileXValue(int profileXValue) {
        this.profileXValue = profileXValue;
    }

    public int getProfileYValue() {
        return profileYValue;
    }

    public void setProfileYValue(int profileYValue) {
        this.profileYValue = profileYValue;
    }

    public int getLogoXValue() {
        return logoXValue;
    }

    public void setLogoXValue(int logoXValue) {
        this.logoXValue = logoXValue;
    }

    public int getLogoYValue() {
        return logoYValue;
    }

    public void setLogoYValue(int logoYValue) {
        this.logoYValue = logoYValue;
    }

    public int getTextXValue() {
        return textXValue;
    }

    public void setTextXValue(int textXValue) {
        this.textXValue = textXValue;
    }

    public int getTextYValue() {
        return textYValue;
    }

    public void setTextYValue(int textYValue) {
        this.textYValue = textYValue;
    }
}
