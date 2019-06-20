package proj2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/*************************************************************
 * GUI for a Zip Code Database
 *
 * @author Scott Grissom
 * @version May 7, 2019
 ************************************************************/
public class ZipCodeGUI extends JFrame implements ActionListener{

    private DatabaseStub dataBase;

    //Buttons
    private JButton distance, findZip, withinRadius, searchName, furthest;

    //Fields
    private JTextField zip1field, zip2field, radiusfield, namefield;

    //JMenu
    private JMenuItem count, open, about, togglefcn, quit;

    /** Results */
    private JTextArea results;

    /*****************************************************************
     * Main Method
     ****************************************************************/
    public static void main(String args[]){
        ZipCodeGUI gui = new ZipCodeGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Zip Codes");
        gui.pack();
        gui.setVisible(true);


        //relocates it to the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        gui.setLocation(dim.width/2-gui.getSize().width/2,
                dim.height/2-gui.getSize().height/2);

    }

    /******************************************************************
     * constructor installs all of the GUI components
     *****************************************************************/
    public ZipCodeGUI(){
        // instantiate the database object
        setupGUI();
        setupMenus();
    }

    /**
     * Helper method to setup the GUI
     */
    private void setupGUI(){
        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // instantiating the JButtons and JTextFields
        distance = new JButton ("distance between Zip 1 and 2");
        findZip = new JButton("find Zip 1");
        withinRadius = new JButton("within radius of Zip 1");
        searchName = new JButton("search by name");
        furthest = new JButton("furthest from Zip 1");

        zip1field = new JTextField (12);
        zip2field = new JTextField(12);
        radiusfield = new JTextField(10);
        namefield = new JTextField(17);

        // create results area to span one column and 10 rows
        results= new JTextArea(20,30);
        JScrollPane scrollPane = new JScrollPane(results);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 10;  // 10 rows

        // loc insets (top, left, bottom, right)
        loc.insets = new Insets (0, 20, 20, 20);
        add(scrollPane, loc);

        // create Results label
        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;

        // defining individual insets
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        add(new JLabel("Results"), loc);

        // create Searches label
        loc.gridwidth = 2;

        loc.gridx++;
        add(new JLabel("Choices"), loc);

        // adding labels and buttons
        // to reset the default values
        loc = new GridBagConstraints();

        // aligns components to the left
        loc.anchor = GridBagConstraints.LINE_START;

        loc.insets = new Insets(5,5,5,5);
        loc.gridx = 1;
        loc.gridy = 1;
        add(new JLabel ("Zip 1"), loc);
        loc.gridx++;
        add(zip1field, loc);

        loc.gridy++;
        loc.gridx--;
        add(new JLabel("Zip 2"), loc);
        loc.gridx++;
        add(zip2field, loc);

        loc.gridy++;
        loc.gridx--;
        add(new JLabel("radius"), loc);
        loc.gridx++;
        add(radiusfield, loc);

        loc.gridy++;
        loc.gridx--;
        add(new JLabel("name"), loc);
        loc.gridx++;
        add(namefield, loc);

        // adding the JButtons
        // to restart the default constraints - centered
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 5;
        loc.gridwidth = 2;
        loc.insets = new Insets(5,5,5,5);
        add(distance, loc);

        loc.gridy++;
        add(findZip, loc);

        loc.gridy++;
        add(withinRadius, loc);

        loc.gridy++;
        add(searchName, loc);

        loc.gridy++;
        add(furthest, loc);

        // register the action listeners
        distance.addActionListener(this);
        findZip.addActionListener(this);
        withinRadius.addActionListener(this);
        searchName.addActionListener(this);
        furthest.addActionListener(this);

        results.setAutoscrolls(true);
        dataBase = new DatabaseStub();
    }

    /**
     * Helper method to setup and add the menu
     */
    private void setupMenus(){
        JMenu fileMenu = new JMenu("File");
        quit = new JMenuItem("Quit");
        open = new JMenuItem("Open");
        count = new JMenuItem("Counts");
        togglefcn = new JMenuItem("Toggle functionality: Off");
        about = new JMenuItem("About");

        fileMenu.add(count);
        fileMenu.add(open);
        fileMenu.add(togglefcn);
        fileMenu.add(about);
        fileMenu.add(quit);

        JMenuBar menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        count.addActionListener(this);
        open.addActionListener(this);
        quit.addActionListener(this);
        about.addActionListener(this);
        togglefcn.addActionListener(this);
    }
    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     *
     * @param e the event that was fired
     ****************************************************************/
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        if (buttonPressed == open)
            openFile();
        if (buttonPressed == quit)
            System.exit(1);
        if (buttonPressed == about)
            JOptionPane.showMessageDialog(this,
                    "created by Ethan Carter for Dr. Grissom and Dr. Posada.");
        if (buttonPressed == count){
            showCount();
        }
        if (buttonPressed == togglefcn){
            dataBase.toggleStub();
            if(togglefcn.getText().contains("Off"))
                togglefcn.setText("Toggle Functionality: On");
            else
                togglefcn.setText("Toggle Functionality: Off");
        }
        if (buttonPressed == distance){
            distanceSearch();
        }
        if (buttonPressed == findZip){
            zipSearch();
        }
        if (buttonPressed == withinRadius){
            radiusSearch();
        }
        if (buttonPressed == searchName){
            nameSearch();
        }
        if (buttonPressed == furthest){
            furthestSearch();
        }
    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            dataBase.readZipCodeData(filename);
        }
    }

    /*****************************************************************
     * Check if the String contains a valid integer.  Display
     * an appropriate warning if it is not valid.
     *
     * @param numStr - the String to be checked
     * @param label - the textfield name that contains the String
     * @return true if valid
     ****************************************************************/
    private boolean checkValidInteger(String numStr, String label){
        boolean isValid = true;
        try{
            int val = Integer.parseInt(numStr);

            // display error message if not a valid integer
        }catch(NumberFormatException e){
            isValid = false;
            JOptionPane.showMessageDialog(this, "Enter an integer in " + label);
        }
        return isValid;
    }

    /**
     * Helper method that utilizes database variable to display
     * the amount of items in the databse
     */
    private void showCount(){
        int c = dataBase.getCount();
        results.setText("Zipcodes available: " + c);
    }

    /**
     * Helper method that utilizes database variable to display
     * the results of a distance search
     */
    private void distanceSearch(){

        if (!checkValidInteger(zip1field.getText(), "Zip 1")){

        }
        else if (!checkValidInteger(zip2field.getText(), "Zip 2")){

        }
        else {
            int z1 = Integer.parseInt(zip1field.getText());
            int z2 = Integer.parseInt(zip2field.getText());
            String result = "" + dataBase.distance(z1, z2);
            results.setText(result + "miles");
        }
    }
    /**
     * Helper method that utilized database variable to display
     * the results of a zip search
     */
    private void zipSearch(){
        if (!checkValidInteger(zip1field.getText(), "Zip 1")){

        }
        else{
            String result = "" + dataBase.findZip(
                    Integer.parseInt(zip1field.getText()));
            if (result.equals("null")){ //terrible way to handle this
                results.setText("Could not find zipcode. ");
            }
            else results.setText(result);
        }
    }
    /**
     * Helper method that utilized database variable to display
     * the results of a radius search
     */
    private void radiusSearch(){

        if (!checkValidInteger(zip1field.getText(), "Zip 1")){

        }
        else if(!checkValidInteger(radiusfield.getText(), "radius")){

        }
        else{
            int z1 = Integer.parseInt(zip1field.getText());
            int mi = Integer.parseInt(radiusfield.getText());
            String result = dataBase.withinRadius(z1, mi);
            results.setText(result);
        }
    }
    /**
     * Helper method that utilized database variable to display
     * the results of a name search
     */
    private void nameSearch(){

        if(namefield.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter a name");
        }
        else {
            String name = namefield.getText();
            String result = dataBase.search(name);
            results.setText(result);
        }

    }
    /**
     * Helper method that utilized database variable to display
     * the results of a furthest search
     */
    private void furthestSearch(){
        if(!checkValidInteger(zip1field.getText(), "Zip 1")){

        }
        else {
            int z1 = Integer.parseInt(zip1field.getText());
            String result = dataBase.findFurthest(z1).toString();
            results.setText(result);
        }
    }
}