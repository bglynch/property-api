package demo.dublin.dashboard.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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
    private double allPricePerSqMetre;
    private double postcodePricePerSqMetre;

    private String berRating;

    Map<String, Boolean> keywords;
//    boolean hasParking;
//    boolean hasGarden;
//    boolean hasSouthFacingRear;
//    boolean hasEnSuite;
//    boolean hasWalkInWardrobe;
//    boolean hasUnderfloorHeating;
//    boolean hasStarterHome;



}
