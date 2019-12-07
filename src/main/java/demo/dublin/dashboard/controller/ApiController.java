package demo.dublin.dashboard.controller;

import demo.dublin.dashboard.models.House;
import demo.dublin.dashboard.models.Location;
import demo.dublin.dashboard.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/propertiess")
public class ApiController {

    @Autowired
    private HouseRepository houseRepository;

    @CrossOrigin
    @GetMapping("")
    public List<House> getAllUsers() {
        return (List<House>) houseRepository.findAll();
    }

    @GetMapping("/test")
    public List<Object> getAd_Ids() {
        return (List<Object>) houseRepository.fetchArticles();
    }

    @GetMapping("search/locations")
    public List<List> getLocations() {
        List<List> list = (List<List>) houseRepository.fetchLocations();

        ArrayList locationList = new ArrayList();
        for (List l : list) {
//            Map<String, String> location = new HashMap<String, String>();
//            location.put("id", (String)l.get(0));
//            location.put("longitude", (String)l.get(1));
//            location.put("latitude", (String)l.get(2));
//
//            locationList.add(location);
            Location location = new Location();
            location.setAdId((String)l.get(0));
            location.setLongitude((String)l.get(1));
            location.setLatitude((String)l.get(2));
            location.setIsApartment((String)l.get(3));
            locationList.add(location);

        }
        return locationList;
    }

    @GetMapping("search/postcodes")
    public List<Object> fetchPostcodes() {
        return (List<Object>) houseRepository.fetchPostcodes();
    }

    @GetMapping("/maxPrice={price}")
    public List<House> getByMaxPrice(@PathVariable("price") String price) {
        int maxPriceInt = Integer.parseInt(price);
        return (List<House>) houseRepository.fetchHousesByMaxPrice(maxPriceInt);
    }

    @GetMapping("/minPrice={price}")
    public List<House> getByMinPrice(@PathVariable("price") String price) {
        return (List<House>) houseRepository.fetchHousesByMinPrice(price);
    }

    @GetMapping("/min={minPrice}&max={maxPrice}")
    public List<House> getByMinPrice(@PathVariable("minPrice") String minPrice, @PathVariable("maxPrice") String maxPrice) {
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);

        return (List<House>) houseRepository.fetchHousesByPriceRange(minPriceInt, maxPriceInt);
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
