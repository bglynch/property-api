package demo.dublin.dashboard.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Home {

    // == fields ==
    @Id
    private String adId;
    private int price;
    private String priceType;
    private String publishedDate;
    private String url;

    private String address;
    private String locality;
    private String postcode;
    private String county;
    private String eircode;

    private String longitude;
    private String latitude;

    private String primaryPhoto;
    @ElementCollection @CollectionTable(name="listOfImages")
    private List<String> listOfImages;

    private String propertyType;
    private int bedrooms;
    private int bathrooms;
    private double floorArea;
    @Column(columnDefinition = "double default null")
    private double siteArea;

    private String berRating;
    private String berNumber;

    private String description;
    @ElementCollection @CollectionTable(name="listOfFeatures")
    private List<String> listOfFeatures = new ArrayList<>();;
    @ElementCollection @CollectionTable(name="listOfFacilities")
    private List<String> listOfFacilities;

    private String seller;
    private String sellerType;
    private String sellingType;
    private String propertyCategory;

    @Column(columnDefinition = "boolean default false")
    boolean hasParking;
    @Column(columnDefinition = "boolean default false")
    boolean hasGarden;
    @Column(columnDefinition = "boolean default false")
    boolean hasSouthFacingRear;
    @Column(columnDefinition = "boolean default false")
    boolean hasEnSuite;
    @Column(columnDefinition = "boolean default false")
    boolean hasWalkInWardrobe;
    @Column(columnDefinition = "boolean default false")
    boolean hasUnderfloorHeating;
    @Column(columnDefinition = "boolean default false")
    boolean hasStarterHome;


    @Column(columnDefinition = "boolean default false")
    boolean manuallyUpdated;


}
