package demo.dublin.dashboard.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HomeDTO {
    private String adId;
    private int price;
    private String publishedDate;
    private String url;

    private String address;
    private String locality;
    private String postcode;

    private String longitude;
    private String latitude;

    private String primaryPhoto;

    private String propertyType;
    private int bedrooms;
    private double floorArea = 0.0;
    private double pricePerSqMetre;

    private String berRating;

    boolean hasParking;
    boolean hasGarden;
    boolean hasSouthFacingRear;
    boolean hasEnSuite;
    boolean hasWalkInWardrobe;
    boolean hasUnderfloorHeating;
    boolean hasStarterHome;

}
