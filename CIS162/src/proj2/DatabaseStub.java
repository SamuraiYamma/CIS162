package proj2;

/********************************************************
 * This class serves as a temporary stub for a GUI
 * database of U.S. zipcodes.
 *
 * Relies on a ZipCode class.
 *
 * Class has been modified by Ethan Carter to enable
 * actual functionality
 *
 * @author Scott Grissom
 * @version May 3, 2019
 *******************************************************/
public class DatabaseStub{
    private boolean useStub = true;
    private DataBase zipData = new DataBase();

    public DatabaseStub(){
        zipData = new DataBase();
    }

    public int getCount(){
        if (useStub)
            return 42;
        else{
            return zipData.size();
        }
    }

    public void readZipCodeData(String filename){
        // does nothing at this time
    }

    public ZipCode findZip(int z){
        if(useStub) {
            if (z >= 10000) {
                return new ZipCode(49401, "Allendale", "MI", 0.0, 0.0);
            } else {
                return null;
            }
        }
        else{
            return zipData.searchByZip(z);
        }
    }

    public ZipCode findFurthest(int z){
        if(useStub) {
            if (z >= 10000) {
                return new ZipCode(96769, "Makaweli", "HI", 0.0, 0.0);
            } else {
                return null;
            }
        }
        else{
            return zipData.farthestFromOrigin(zipData.searchByZip(z));
        }
    }

    public String search(String str){
        if(useStub)
            return "Provo, UT\nDallas, TX\nAllendale, MI\nNew York City, NY";
        else{
            return zipData.searchByName(str);
        }
    }

    public String withinRadius(int zip, int miles){
        if(useStub)
            return "Dallas, TX\nGrand Rapids, MI\nLos Angeles, CA";
        else {
            ZipCode origin = zipData.searchByZip(zip);
            return zipData.zipsWithinRadius(origin, miles);
        }
    }

    public int distance(int zip1, int zip2){
        if(useStub) {
            int dist = zip2 - zip1;
            if (dist > 0) {
                return dist;
            } else {
                return -1;
            }
        }
        else {
            ZipCode zipcode1 = findZip(zip1);
            ZipCode zipcode2 = findZip(zip2);

            double lat1, long1, lat2, long2;
            lat1 = zipcode1.getLatitude();
            lat2 = zipcode2.getLatitude();
            long1 = zipcode1.getLongitude();
            long2 = zipcode2.getLongitude();

            double distanceInKM = zipData.getDistance(lat1, lat2, long1, long2);

            return (int) distanceInKM;
        }
    }

    public void toggleStub(){
        if (useStub)
            useStub = false;
        else useStub = true;
    }
}