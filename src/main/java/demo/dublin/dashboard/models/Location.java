package demo.dublin.dashboard.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
//@Entity
public class Location {

    // == fields ==
    @Id
    private String adId;
    private String longitude;
    private String latitude;
    private Boolean isApartment;

    // == constructors ==
    public Location() {
    }

    public void setIsApartment(String isApartment) {
        if (isApartment.equals("apartment")) {
            this.isApartment = true;
        } else {
            this.isApartment = false;
        }
    }


}