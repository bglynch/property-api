package demo.dublin.dashboard.controller;

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

    @GetMapping("/localities")
    public List<String> getLocalities() {
        List localities = homeRepository.getAllLocalitys();
        Collections.sort(localities);
        return localities;
    }

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

        // ============ hacky price per sq metre for all properties
        List<Long> medianPricePerSqMetre = new ArrayList<>();
        for (Home h : list) {
            if (h.getFloorArea()>30)
                medianPricePerSqMetre.add(Math.round(h.getPrice() / h.getFloorArea()));
        }
        Collections.sort(medianPricePerSqMetre);
        Long[] numArray = medianPricePerSqMetre.toArray(new Long[medianPricePerSqMetre.size()]);
        double median;
        if (medianPricePerSqMetre.size() % 2 == 0)
            median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
        else
            median = (double) numArray[numArray.length/2];
        // ============



        for (Home h : list) {
            HomeDTO home = new HomeDTO();
            Map<String, Boolean> keywords = new HashMap<>();
            keywords.put("parking", h.isHasParking());
            keywords.put("garden", h.isHasGarden());
            keywords.put("southFacingRear", h.isHasSouthFacingRear());
            keywords.put("enSuite", h.isHasEnSuite());
            keywords.put("underFloorHeating", h.isHasUnderfloorHeating());
            keywords.put("walkInWardrobe", h.isHasWalkInWardrobe());
            keywords.put("starterHome", h.isHasStarterHome());

            home.setAdId(h.getAdId());
            home.setPrice(h.getPrice());
            home.setPublishedDate(h.getPublishedDate());
            home.setUrl(h.getUrl());
            home.setAddress(h.getAddress());
            home.setLocality(h.getLocality());
            home.setPostcode(h.getPostcode());
            home.setLongitude(h.getLongitude());
            home.setLatitude(h.getLatitude());
            home.setPrimaryPhoto(h.getPrimaryPhoto());
            home.setPropertyType(h.getPropertyType());
            home.setBedrooms(h.getBedrooms());
            home.setFloorArea(h.getFloorArea());
            home.setBerRating(h.getBerRating());
//            home.setHasParking(h.isHasParking());
//            home.setHasGarden(h.isHasGarden());

            if (home.getFloorArea()>0)
                home.setPricePerSqMetre(Math.round(home.getPrice() / home.getFloorArea()));
            home.setLocalityPricePerSqMetre(median);
            home.setKeywords(keywords);
            homeList.add(home);
        }

        homeList.sort(Comparator.comparingInt(HomeDTO::getPrice));

        return homeList;
    }

    @GetMapping("/locations")
    public List<List> getLocations() {
        List<List> list = (List<List>) homeRepository.fetchLocations();

        ArrayList locationList = new ArrayList();
        for (List l : list) {
            Location location = new Location();
            location.setAdId((String)l.get(0));
            location.setLongitude((String)l.get(1));
            location.setLatitude((String)l.get(2));
            location.setIsApartment((String)l.get(3));
            locationList.add(location);

        }
        return locationList;
    }

}
