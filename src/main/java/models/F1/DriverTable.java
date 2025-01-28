package models.F1;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DriverTable{

    @JsonProperty("Drivers")
    private List<DriversItem> drivers;

    @JsonProperty("season")
    private String season;

    public void setDrivers(List<DriversItem> drivers){
        this.drivers = drivers;
    }

    public List<DriversItem> getDrivers(){
        return drivers;
    }

    public void setSeason(String season){
        this.season = season;
    }

    public String getSeason(){
        return season;
    }

    @Override
    public String toString() {
        return "DriverTable{" +
                "drivers=" + drivers +
                ", season='" + season + '\'' +
                '}';
    }

}