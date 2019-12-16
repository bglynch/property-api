package demo.dublin.dashboard.controller;

import demo.dublin.dashboard.models.Home;
import demo.dublin.dashboard.models.dto.HomeDTO;
import demo.dublin.dashboard.repository.HomeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/properties")
public class HomeController {

    @Autowired
    private HomeRepository homeRepository;

    @GetMapping("/v2")
    public Collection getLocalities() {
        return homeRepository.getAllLocalitys();
    }

    @GetMapping("")
    public Iterable<Home> getAll() {
        return homeRepository.findAll();
    }

    @GetMapping("/lite")
    public List<HomeDTO> getAllMin() {
        List<Home> list = (List<Home>) homeRepository.findAll();
        List<HomeDTO> homeList = new ArrayList<>();
        for (Home h : list) {
            HomeDTO home = new HomeDTO();
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
            home.setHasParking(h.isHasParking());
            home.setHasGarden(h.isHasGarden());
            home.setHasSouthFacingRear(h.isHasSouthFacingRear());
            home.setHasEnSuite(h.isHasEnSuite());
            home.setHasWalkInWardrobe(h.isHasWalkInWardrobe());
            home.setHasUnderfloorHeating(h.isHasUnderfloorHeating());
            home.setHasStarterHome(h.isHasStarterHome());
            homeList.add(home);
        }

        return homeList;

    }



}
