package demo.dublin.dashboard.functions;

import demo.dublin.dashboard.models.Home;
import demo.dublin.dashboard.models.dto.HomeDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ControllerFunctions {

  public Map<String, Boolean> createKeywordsMap(Home h) {
    Map<String, Boolean> keywords = new HashMap<>();
    keywords.put("parking", h.isHasParking());
    keywords.put("garden", h.isHasGarden());
    keywords.put("southFacingRear", h.isHasSouthFacingRear());
    keywords.put("enSuite", h.isHasEnSuite());
    keywords.put("underFloorHeating", h.isHasUnderfloorHeating());
    keywords.put("walkInWardrobe", h.isHasWalkInWardrobe());
    keywords.put("starterHome", h.isHasStarterHome());
    return keywords;
  }

  public HomeDTO createHome(Home h, Integer median, Map<String, List<Integer>> postcodeMedianPricePerSqMetre) {
    HomeDTO home = new HomeDTO();
    List<Integer> postcodeMedPricePerM2 = postcodeMedianPricePerSqMetre.get(h.getPostcode());
    home.setAdId(h.getAdId());
    home.setPrice(h.getPrice());
    home.setPublishedDate(h.getPublishedDate());
    home.setUrl(h.getUrl());
    home.setAddress(h.getAddress());
    home.setLocality(h.getLocality());
    home.setPostcode(h.getPostcode());
    home.setEircode_rk(h.getEircode_rk());
    home.setLongitude(h.getLongitude());
    home.setLatitude(h.getLatitude());
    home.setPrimaryPhoto(h.getPrimaryPhoto());
    home.setPropertyType(h.getPropertyType());
    home.setBathrooms(h.getBathrooms());
    home.setBedrooms(h.getBedrooms());
    home.setFloorArea(h.getFloorArea());
    home.setBerRating(h.getBerRating());
    home.setPostcodePricePerSqMetre(calculateMedian(postcodeMedPricePerM2));
    home.setAllPricePerSqMetre(median);
    home.setStreetView("https://www.google.com/maps/@?api=1&map_action=pano&viewpoint=" + h.getLatitude() + "," + h.getLongitude());
    home.setPropertyPriceRegister("https://propertypriceregisterireland.com/?action=search&county=6&address=" + HouseFunctions.ExtractStreet(h.getAddress(), h.getLocality()));
    if (home.getFloorArea() > 0) {
      home.setPricePerSqMetre(Math.round(home.getPrice() / home.getFloorArea()));
      home.setPriceDifference(
          (int) (((home.getPricePerSqMetre() / home.getPostcodePricePerSqMetre()) * 100) - 100) * -1
      );
    }
    home.setKeywords(createKeywordsMap(h));

    return home;
  }


  private Integer calculateMedian(List<Integer> list) {
    Collections.sort(list);
    Integer[] numArray = list.toArray(new Integer[list.size()]);
    double median;
    if (list.size() % 2 == 0)
      median = ((double) numArray[numArray.length / 2] + (double) numArray[numArray.length / 2 - 1]) / 2;
    else
      median = (double) numArray[numArray.length / 2];

    return (int) median;
  }


}
