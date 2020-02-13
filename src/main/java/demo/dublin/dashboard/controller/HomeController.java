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
        List<Long> allMedianPricePerSqMetre = new ArrayList<>();
        Map<String, List<Long>> postcodeMedianPricePerSqMetre = new HashMap<>();
        for (Home h : list) {
            if (h.getFloorArea()>30) {
                Long ppsm = Math.round(h.getPrice() / h.getFloorArea());
                // all houses
                allMedianPricePerSqMetre.add(ppsm);
                // postcodes
                if (!postcodeMedianPricePerSqMetre.containsKey(h.getPostcode())) {
                    postcodeMedianPricePerSqMetre.put(h.getPostcode(), new ArrayList<Long>());
                }
                postcodeMedianPricePerSqMetre.get(h.getPostcode()).add(ppsm);
            }
        }

        Collections.sort(allMedianPricePerSqMetre);
        Long[] numArray = allMedianPricePerSqMetre.toArray(new Long[allMedianPricePerSqMetre.size()]);
        double median;
        if (allMedianPricePerSqMetre.size() % 2 == 0)
            median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
        else
            median = (double) numArray[numArray.length/2];
        // ============

        //============= price per sq metre of postcode
        for (Map.Entry<String, List<Long>> entry : postcodeMedianPricePerSqMetre.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            Collections.sort(entry.getValue());

        }


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

            // get ppsm for postcode
            Long[] numArray2 = postcodeMedianPricePerSqMetre.get(h.getPostcode()).toArray(new Long[postcodeMedianPricePerSqMetre.get(h.getPostcode()).size()]);
            Double median2;
            if (postcodeMedianPricePerSqMetre.get(h.getPostcode()).size() % 2 == 0)
                median2 = ((double)numArray2[numArray2.length/2] + (double)numArray2[numArray2.length/2 - 1])/2;
            else
                median2 = (double) numArray2[numArray2.length/2];

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
            home.setPostcodePricePerSqMetre(median2);

            if (home.getFloorArea()>0)
                home.setPricePerSqMetre(Math.round(home.getPrice() / home.getFloorArea()));
            home.setAllPricePerSqMetre(median);
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
