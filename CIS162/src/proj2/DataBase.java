package proj2;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class DataBase {
    final private String zipcodes = "zipcodes.csv";
    final private String latsLongs = "latsAndLongs.csv";

    final private String zipData = "zipData";

    /**
     * We will use this array as our "database". Not very efficient, considering
     * it will contain about 40000 items.
     */
    private ArrayList<ZipCode> dataList;

    //Public Methods

    /**
     * Constructor. It will initialize and load the current zipData, or else attempt to generate it
     */
    public DataBase(){
        //attempt to find data
        try{
            loadData();
        }
        catch (Exception e){

            //could not load any data.

            try {
                dataList  = new ArrayList<>();
                readZipCodeData(zipcodes);
                readLatsLongsData(latsLongs);
            }
            catch (IOException io){
                //could not generate any data.
            }
        }

        try{
            //save data for next time
            saveData();
        }
        catch (IOException io){
            //could not save data
        }

        cleanData();
    }

    /**
     * This is a really crappy search method.
     * @param zipcode the zipcode you are searching for
     * @return null or the ZipCode object you are looking for.
     */
    public ZipCode searchByZip(int zipcode){
        //case1: null
        //case2: improper object
        //case3: zipcode
        if(zipcode > 0 && zipcode < 100000){
            //linear search. should be fine since there is a known number of items, and they are sorted
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getZipCode() == zipcode){
                    return dataList.get(i);
                }
            }
        }
        //case7: not found
        else{
            //not the best way to handle this
            return null;
        }
        return null;
    }

    /**
     * This is a really crappy search method that searches by name.
     * @return
     */
    public String searchByName(String input){
        StringBuilder list = new StringBuilder();
        ZipCode currentZip;
        for (int i = 0; i < dataList.size(); i++) {
            currentZip = dataList.get(i);

            if(currentZip.getCity().equalsIgnoreCase(input)){
                list.append(String.format("%d, %s, %s, %f, %f\n", currentZip.getZipCode(), currentZip.getCity(),
                        currentZip.getState(), currentZip.getLatitude(), currentZip.getLongitude()));
            }
        }
        return list.toString();
    }

    /**
     *
     * @return size of the data
     */
    public int size(){
        return dataList.size();
    }


    //Private methods

    /**
     * This method reads the data from a file with an expected layout of
     * zipcode,"City, State",City,State,County

     * @param fileName name of the file being processed
     * @throws IOException happens when it cant read our load the file
     */
    public void readZipCodeData(String fileName) throws IOException{
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] info;
        int zip;
        String city, state;

        String line;
        try {
            while ((line = br.readLine()) != null) {
                //this splits the next line into 5 segments
                ZipCode newZipCode = new ZipCode();
                info = line.split(",");

                zip = Integer.parseInt(info[0]);
                city = info[3];
                state = info[4];

                newZipCode.setZipCode(zip);
                newZipCode.setCity(city);
                newZipCode.setState(state);

                dataList.add(newZipCode);
            }
        }
        catch (IOException io){
            //something bad happened
        }
    }

    /**
     * This method is only called if zipData/previous database is not found.
     * It uses a file of lats and longs associated with a zipcode and will
     * insert it into the previous database made readZipCodeData()
     *
     * File is expected to be formated as:
     * zipcode, lat, long
     *
     * @param fileName the name of the lats and longs
     * @throws IOException happens when it can't read or load the file
     */
    public void readLatsLongsData(String fileName) throws IOException{
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] info;
        int zip;
        double latitude, longitude;

        String line;
        try {
            while ((line = br.readLine()) != null) {
                info = line.split(",");

                zip = Integer.parseInt(info[0]);
                latitude = Double.parseDouble(info[1]);
                longitude = Double.parseDouble(info[2]);

                //make sure we add to the right objects
                try {
                    if (searchByZip(zip).equals(null)) { //make sure it exists first

                    } else {
                        searchByZip(zip).setLatitude(latitude);
                        searchByZip(zip).setLongitude(longitude);
                    }
                }
                catch (NullPointerException npe){
                    //why tho
                }
            }
        }
        catch (IOException io){
            //something bad happened
        }
    }

    /**
     * This method will return the entire database formatted as
     * zipcode, city, state, latitude, longitude
     * @return the entire database
     */
    public String toString(){
        String str = "";
        for (int i = 0; i < dataList.size(); i++) {
            ZipCode temp = dataList.get(i);

            str += String.format("%d, %s, %s, %f, %f\n", temp.getZipCode(), temp.getCity(), temp.getState(),
                    temp.getLatitude(), temp.getLongitude());
        }

        return str;
    }

    /**
     * Saves/serialized the data
     * @throws IOException if it can't find the data to save
     */
    private void saveData() throws IOException {
        FileOutputStream fileOutput = new FileOutputStream(zipData);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(dataList);
        objectOutput.close();
    }

    /**
     * Loads the data
     * @throws IOException if it cannot find the file
     * @throws ClassNotFoundException if the object is not a zipcode object
     */
    private void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(zipData);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);

        dataList = (ArrayList<ZipCode>) objectInput.readObject();
        objectInput.close();
    }

    /**
     * This method takes the data and cleans it. There are a lot of zipcodes
     * without an assigned lat and long, meaning it was auto-generated by
     * position and is unimportant. We only want full data.
     */
    private void cleanData() {
        ZipCode zipCode;
        ArrayList<Integer> removeList = new ArrayList<>();
        for (int i = 0; i <dataList.size() ; i++) {
            zipCode = dataList.get(i);

            if (zipCode.getLatitude() == 0.0 && zipCode.getLongitude() == 0.0){
                removeList.add(i);
            }
        }

        for (int i = 0; i < removeList.size(); i++) {
            dataList.remove(removeList.get(i));
        }
    }

    /**
     * This method takes lat and long coordinates from two points and calculates the distance
     * between them. This method was sourced from:
     *
     * https://stackoverflow.com/questions/27928/
     * calculate-distance-between-two-latitude-longitude-points-haversine-formula#27943
     *
     * @param lat1 Latitude of your first Zip Code
     * @param lat2 Latitude of your second Zip Code
     * @param long1 Longitude of your first Zip Code
     * @param long2 Longitude of your second Zip Code
     * @return the distance between those coordinates
     */
    public double getDistance(double lat1, double lat2, double long1, double long2){
        int earthRadius = 3959; //rough estimate of earth in miles

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);

        double p1, p2, p3;

        p1 = cos(lat1) * cos(long1) * cos(lat2) * cos(long2);
        p2 = cos(lat1) * sin(long1) * cos(lat2) * sin(long2);
        p3 = sin(lat1) * sin(lat2);

        double distance = acos(p1 + p2 + p3) * earthRadius;

        return distance;
    }

    /**
     * This method returns all the zipcodes within the given radius
     *
     * @param origin the ZipCode object of origin
     * @param radius in miles
     * @return a string with the data within that radius
     */
    public String zipsWithinRadius(ZipCode origin, int radius){
        String data = "";
        ZipCode current;
        int distance;

        for (int i = 0; i < dataList.size(); i++) {
            current = dataList.get(i);
            distance = (int) getDistance(origin.getLatitude(), current.getLatitude(),
                    origin.getLongitude(), current.getLongitude());

            if (distance <= radius){
                data += current.toStringFull();
            }
        }
        return data;
    }

    /**
     * This method returns the zipcode that is farthest away from the origin
     * @param origin the origin
     * @return a ZipCode object that is the greatest distance from the origin
     */
    public ZipCode farthestFromOrigin(ZipCode origin){
        int distance = 0;
        int farthestDistance = 0;
        ZipCode current;
        ZipCode farthest = new ZipCode();

        for (int i = 0; i < dataList.size(); i++) {
            current = dataList.get(i);
            distance = (int) getDistance(origin.getLatitude(), current.getLatitude(),
                    origin.getLongitude(), current.getLongitude());
            if(distance > farthestDistance){
                farthestDistance = distance;
                farthest = current;
            }
        }

        return farthest;
    }
}
