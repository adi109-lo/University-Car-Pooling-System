
package PassengerRideManagementModule;

public class Location {


    private float longitude;
    private float latitude;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
    public Location(){
        
    }
    public Location(String location){
        String[] longlat = location.split(",");
        this.latitude = Float.parseFloat(longlat[0]);
        this.longitude = Float.parseFloat(longlat[1]);
    }
    public Location(float latitude, float longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "latitude=" + latitude + ", longitude=" + longitude;
    }
    
    public String toDbString() {
        return latitude + "," + longitude;
    }
    
}
