package demo.dublin.dashboard.controller;

import demo.dublin.dashboard.models.Social;
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
}
