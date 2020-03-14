package demo.dublin.dashboard.functions;

import demo.dublin.dashboard.models.Home;
import demo.dublin.dashboard.models.dto.HomeDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ControllerFunctions {

  private Map<String, Boolean> createKeywordsMap(Home h) {
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

  public HomeDTO createHome(Home h, Map<String, Integer> medianPricesPerM2) {
    HomeDTO home = new HomeDTO();
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
    home.setPostcodePricePerSqMetre(medianPricesPerM2.get(h.getPostcode()));
    home.setAllPricePerSqMetre(medianPricesPerM2.get("all"));
    home.setStreetView("https://www.google.com/maps/@?api=1&map_action=pano&viewpoint=" + h.getLatitude() + "," + h.getLongitude());
    home.setPropertyPriceRegister("https://propertypriceregisterireland.com/?action=search&county=6&address=" + ExtractStreet(h.getAddress(), h.getLocality()));
    if (home.getFloorArea() > 0) {
      home.setPricePerSqMetre(Math.round(home.getPrice() / home.getFloorArea()));
      home.setPriceDifference(
          (int) (((home.getPricePerSqMetre() / home.getPostcodePricePerSqMetre()) * 100) - 100) * -1
      );
    }
    home.setKeywords(createKeywordsMap(h));

    return home;
  }

  public Integer calculateMedian(List<Integer> list) {
    Collections.sort(list);
    Integer[] numArray = list.toArray(new Integer[list.size()]);
    double median;
    if (list.size() % 2 == 0)
      median = ((double) numArray[numArray.length / 2] + (double) numArray[numArray.length / 2 - 1]) / 2;
    else
      median = (double) numArray[numArray.length / 2];

    return (int) median;
  }

  public Map<String, Integer> calculateMedianOfGroups(List<Home> list) {
    Map<String, List<Integer>> allMedianPricesPerM2 = new HashMap<>();

    for (Home h : list) {
      if (h.getFloorArea() > 30 && h.getPrice() > 20000) {
        Integer ppsm = (int) Math.round(h.getPrice() / h.getFloorArea());

        if (!allMedianPricesPerM2.containsKey("all")) {
          allMedianPricesPerM2.put("all", new ArrayList<>());
        }
        if (!allMedianPricesPerM2.containsKey(h.getPostcode())) {
          allMedianPricesPerM2.put(h.getPostcode(), new ArrayList<>());
        }
        allMedianPricesPerM2.get("all").add(ppsm);
        allMedianPricesPerM2.get(h.getPostcode()).add(ppsm);
      }
    }
    for (Map.Entry<String, List<Integer>> entry : allMedianPricesPerM2.entrySet()) {
      Collections.sort(entry.getValue());
    }

    Map<String, Integer> medianPricesPerM2 = new HashMap<>();
    for (Map.Entry<String, List<Integer>> entry : allMedianPricesPerM2.entrySet()) {
      medianPricesPerM2.put(entry.getKey(), calculateMedian(entry.getValue()));
    }

    return medianPricesPerM2;
  }

  public String ExtractStreet(String address, String locality) {
    address = address.replaceAll("\\s{2,}", " ");
    address = address.split("[, ]+" + locality + "(?! (Road|Avenue))")[0];
    address = address.replaceAll("[, ]+$", "");
    if (address.contains(",")) {
      address = address.split(",")[address.split(",").length - 1].trim();    // get last item in address
    }
    address = address.replaceAll("'", "");
    return ExtractStreet(address);
  }

  public String ExtractStreet(String address) {
    address = address.replaceAll("^\\d{1,3}(-\\d{1,3})? ", ""); // remove number from start of string
    address = address.replaceAll("Apartments", "").trim();      // remove number from start of string
    address = address.replaceAll("\\\\", "").trim();            // remove number from start of string
    address = address.replaceAll("(\\(.+\\))", "").trim();      // remove number from start of string
    return address;
  }
}
