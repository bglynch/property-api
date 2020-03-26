package demo.dublin.dashboard.controller;

import demo.dublin.dashboard.models.School;
import demo.dublin.dashboard.models.Social;
import demo.dublin.dashboard.repository.SchoolRepository;
import demo.dublin.dashboard.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LocalInfoController {

  @Autowired
  SocialRepository socialRepository;

  @Autowired
  SchoolRepository schoolRepository;

  @GetMapping("/social/coffee")
  public List<Social> getCoffee() {
    return socialRepository.getCoffeeShops();
  }

  @GetMapping("/social/brunch")
  public List<Social> getBrunch() {
    return socialRepository.getBrunch();
  }
  @GetMapping("/social/bars")
  public List<Social> getBars() {
    return socialRepository.getBars();
  }
  @GetMapping("/social/restaurants")
  public List<Social> getRestaurants() {
    return socialRepository.getRestaurants();
  }
  @GetMapping("/schools/dublin/primary")
  public List<School> getDublinPrimarySchools() {
    return schoolRepository.getDublinPrimarySchools();
  }
   @GetMapping("/schools/dublin/post-primary")
  public List<School> getDublinSecondarySchools() {
    return schoolRepository.getDublinSecondarySchools();
  }

}
