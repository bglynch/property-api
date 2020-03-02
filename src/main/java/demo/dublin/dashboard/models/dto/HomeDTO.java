package demo.dublin.dashboard.models.dto;

import lombok.Data;

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
    private int bathrooms;
    private double floorArea = 0.0;
    private double pricePerSqMetre;
    private int allPricePerSqMetre;
    private double postcodePricePerSqMetre;
    private int priceDifference;

    private String berRating;
    private String streetView;
    private String propertyPriceRegister;

    Map<String, Boolean> keywords;

}
