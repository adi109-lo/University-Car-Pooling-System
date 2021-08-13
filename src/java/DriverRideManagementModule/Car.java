
package DriverRideManagementModule;

public class Car {
    public Car(){
        
    }
    public Car(String carModel, Integer carCapacity) {
        this.carModel = carModel;
        this.carCapacity = carCapacity;
    }
    private String carModel;
    private Integer carCapacity;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(Integer carCapacity) {
        this.carCapacity = carCapacity;
    }

    @Override
    public String toString() {
        return "Car{" + "carModel=" + carModel + ", carCapacity=" + carCapacity + '}';
    }
}
