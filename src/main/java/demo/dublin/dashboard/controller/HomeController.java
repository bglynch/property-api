package demo.dublin.dashboard.controller;

import demo.dublin.dashboard.functions.ControllerFunctions;
import demo.dublin.dashboard.functions.HouseFunctions;
import demo.dublin.dashboard.models.Home;
import demo.dublin.dashboard.models.Location;
import demo.dublin.dashboard.models.dto.HomeDTO;
import demo.dublin.dashboard.repository.HomeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/properties")
public class HomeController {

  @Autowired
  private HomeRepository homeRepository;

  @Autowired
  private ControllerFunctions hf;

  @GetMapping("/localities")
  public List getLocalities() {
    List localities = homeRepository.getAllLocalitys();
    Collections.sort(localities);
    return localities;
  }

  @GetMapping("/postcodes")
  public List getPostcodes() {
    List postcodes = homeRepository.getAllPostcodes();
    Collections.sort(homeRepository.getAllPostcodes());
    return postcodes;
  }

  // TODO: fix this endpoint
  @GetMapping("/v3")
  public List getThings() {
    return homeRepository.fetchThings();
  }

  @GetMapping("")
  public Iterable<Home> getAll() {
    return homeRepository.findAll();
  }

  @GetMapping("/lite")
  public List<HomeDTO> getAllMin() {

    List<Home> list = (List<Home>) homeRepository.findAll();
    List<HomeDTO> homeList = new ArrayList<>();

    // ============ Create List of All Median £/m2 and Map of Postcode £/m2
    List<Integer> allMedianPricePerSqMetre = new ArrayList<>();
    Map<String, List<Integer>> postcodeMedianPricePerSqMetre = new HashMap<>();
    for (Home h : list) {
      if (h.getFloorArea() > 30 && h.getPrice() > 20000) {
        Integer ppsm = (int) Math.round(h.getPrice() / h.getFloorArea());
        // all houses
        allMedianPricePerSqMetre.add(ppsm);
        // postcodes
        if (!postcodeMedianPricePerSqMetre.containsKey(h.getPostcode())) {
          postcodeMedianPricePerSqMetre.put(h.getPostcode(), new ArrayList<>());
        }
        postcodeMedianPricePerSqMetre.get(h.getPostcode()).add(ppsm);
      }
    }

//        Collections.sort(allMedianPricePerSqMetre);
//        Long[] numArray = allMedianPricePerSqMetre.toArray(new Long[allMedianPricePerSqMetre.size()]);
//        double median;
//        if (allMedianPricePerSqMetre.size() % 2 == 0)
//            median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
//        else
//            median = (double) numArray[numArray.length/2];
    // ============

    Integer median = HouseFunctions.calculateMedian(allMedianPricePerSqMetre);

    //============= price per sq metre of postcode
    for (Map.Entry<String, List<Integer>> entry : postcodeMedianPricePerSqMetre.entrySet()) {
      Collections.sort(entry.getValue());
    }


    for (Home h : list) {
      HomeDTO home = hf.createHome(h, median, postcodeMedianPricePerSqMetre);

      if (home.getPrice() > 0) {
        homeList.add(home);
      }
    }

//        homeList.sort(Comparator.comparingInt(homeDTO -> homeDTO.getPrice()*-1));
    homeList.sort(Comparator.comparing(homeDTO -> homeDTO.getPublishedDate()));

    return homeList;
  }

  @GetMapping("/locations")
  public List<List> getLocations() {
    List<List> list = (List<List>) homeRepository.fetchLocations();
    System.out.println(list);
    ArrayList locationList = new ArrayList();
    for (List l : list) {
      Location location = new Location();
      location.setAdId((String) l.get(0));
      location.setLongitude((String) l.get(1));
      location.setLatitude((String) l.get(2));
      location.setIsApartment((String) l.get(3));
      locationList.add(location);

    }
    return locationList;
  }



}
