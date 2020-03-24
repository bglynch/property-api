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
    List<Home> list = homeRepository.findAll();
    List<HomeDTO> homeList = new ArrayList<>();

    Map<String, Integer> medianPricesPerM2 = hf.calculateMedianOfGroups(list);

    for (Home h : list) {
      HomeDTO home = hf.createHome(h, medianPricesPerM2);

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
