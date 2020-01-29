package demo.dublin.dashboard.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

// *********************************************************
// This controller is deprecated - please use Home model
// *********************************************************

@Data
@Entity
public class House {

    // == fields ==
    @Id
    private String adId;
    private int price;
    private String priceType;
    private String url;

    private String address;
    private String area;
    private String postcode;
    private String county;

    private String longitude;
    private String latitude;
    private String berRating;
    private String berNumber;

    private String propertyType;
    private String bedrooms;
    private String bathrooms;
    private String floorArea;

    private String seller;
    private String sellerType;
    private String sellingType;
    private String propertyCategory;
    private String publishedDate;

    private String photoLink;
    private String hasGarden;
    private String hasParking;

    // == constructors ==
    public House() {
    }

    public House(
            String adId,
            String url,
            String area,
            String county,
            String address,
            String postcode,
            String propertyType,
            String bedrooms,
            String bathrooms,
            String floorArea,
            String priceType,
            int price,
            String seller,
            String sellerType,
            String sellingType,
            String propertyCategory,
            String publishedDate,
            String longitude,
            String latitude,
            String berRating,
            String berNumber,
            String photoLink,
            String hasGarden,
            String hasParking
    ) {
        this.adId = adId;
        this.url = url;
        this.area = area;
        this.county = county;
        this.address = address;
        this.postcode = postcode;
        this.propertyType = propertyType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floorArea = floorArea;
        this.priceType = priceType;
        this.price = price;
        this.seller = seller;
        this.sellerType = sellerType;
        this.sellingType = sellingType;
        this.propertyCategory = propertyCategory;
        this.publishedDate = publishedDate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.berRating = berRating;
        this.berNumber = berNumber;
        this.photoLink = photoLink;
        this.hasGarden = hasGarden;
        this.hasParking = hasParking;
    }

}