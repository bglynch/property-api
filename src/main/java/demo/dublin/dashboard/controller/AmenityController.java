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

//    @GetMapping("/supermarkets")
//    public List<Test> getAllUsers() {
//        List<Supermarket> list = (List<Supermarket>) supermarketRepository.findAll();
//        System.out.println(list);
//
//        Test testes = new Test();
//        List<Test> lts = new ArrayList<>();
//        for (Supermarket s : list) {
//            testes.setId(s.getId());
//            testes.setName(s.getName());
//            lts.add(testes);
//        }
//        return lts;
//    }

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
        List<Amenity> list = (List<Amenity>) amenityRepository.findAll();
        Amenity amenity = new Amenity();
        List<Amenity> trimmedList = new ArrayList<>();
        for (Amenity a : list) {
            if (a.getType().equals("yoga")) {
                trimmedList.add(a);
            }
        }
        return trimmedList;
    }

    @GetMapping("/gym")
    public List<Amenity> getGyms() {
        List<Amenity> list = (List<Amenity>) amenityRepository.findAll();
        Amenity amenity = new Amenity();
        List<Amenity> trimmedList = new ArrayList<>();
        for (Amenity a : list) {
            if (a.getType().equals("gym")) {
                trimmedList.add(a);
            }
        }
        return trimmedList;
    }

    @GetMapping("/playground")
    public List<Amenity> getPlayground() {
        List<Amenity> list = (List<Amenity>) amenityRepository.findAll();
        Amenity amenity = new Amenity();
        List<Amenity> trimmedList = new ArrayList<>();
        for (Amenity a : list) {
            if (a.getType().equals("playground")) {
                trimmedList.add(a);
            }
        }
        return trimmedList;
    }

    @GetMapping("/creche")
    public List<Amenity> getCreche() {
        List<Amenity> list = (List<Amenity>) amenityRepository.findAll();
        Amenity amenity = new Amenity();
        List<Amenity> trimmedList = new ArrayList<>();
        for (Amenity a : list) {
            if (a.getType().equals("creche")) {
                trimmedList.add(a);
            }
        }
        return trimmedList;
    }

    @GetMapping("/swimming-pool")
    public List<Amenity> getPool() {
        List<Amenity> list = (List<Amenity>) amenityRepository.findAll();
        Amenity amenity = new Amenity();
        List<Amenity> trimmedList = new ArrayList<>();
        for (Amenity a : list) {
            if (a.getType().equals("swimming pool")) {
                trimmedList.add(a);
            }
        }
        return trimmedList;
    }

}
