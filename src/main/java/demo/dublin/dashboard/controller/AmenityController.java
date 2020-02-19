package demo.dublin.dashboard.controller;

import demo.dublin.dashboard.models.Amenity;
import demo.dublin.dashboard.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/amenities")
public class AmenityController {

    @Autowired
    private AmenityRepository amenityRepository;

    @GetMapping("/supermarket")
    public List<Amenity> getSupermarkets() {
        List<Amenity> list = (List<Amenity>) amenityRepository.findAll();
        Amenity amenity = new Amenity();
        List<Amenity> trimmedList = new ArrayList<>();
        for (Amenity a : list) {
            if (a.getType().equals("supermarket")) {
                trimmedList.add(a);
            }
        }
        return trimmedList;
    }

    @GetMapping("/yoga")
    public List<Amenity> getYoga() {
        return amenityRepository.findByType("yoga");
    }

    @GetMapping("/gym")
    public List<Amenity> getGyms() {
        return amenityRepository.findByType("gym");
    }

    @GetMapping("/playground")
    public List<Amenity> getPlayground() {
        return amenityRepository.findByType("playground");
    }

    @GetMapping("/creche")
    public List<Amenity> getCreche() { return amenityRepository.findByType("creche"); }

    @GetMapping("/swimming-pool")
    public List<Amenity> getPool() {
        return amenityRepository.findByType("swimming pool");
    }

    @GetMapping("/luas")
    public List<Amenity> getLuas() { return amenityRepository.findByType("luas"); }

    @GetMapping("/train")
    public List<Amenity> getTrain() { return amenityRepository.findByType("train"); }

    @GetMapping("/dublin-bikes")
    public List<Amenity> getDublinBikes() { return amenityRepository.findByType("dublin-bikes"); }

}
