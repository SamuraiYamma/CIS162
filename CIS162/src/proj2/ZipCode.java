package proj2;

public class ZipCode {
    private int zipCode;
    private String city, state;
    private double latitude, longitude;

    public ZipCode(){

    }

    public ZipCode (int pZip){
        zipCode = pZip;
        city = "UNKNOWN";
        state = "ST";
        latitude = 0.0;
        longitude = 0.0;
    }

    public ZipCode(int pZip, String pCity, String pState, double pLat, double pLon){
        zipCode = pZip;
        city = pCity;
        state = pState;
        latitude = pLat;
        longitude = pLon;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString(){
        String s = String.format("%s, %s %d", city, state, zipCode);
        return s;
    }

    public String toStringFull(){
        String s = String.format("%s, %s %d, %f, %f\n", city, state, zipCode, latitude, longitude);
        return s;
    }
}
