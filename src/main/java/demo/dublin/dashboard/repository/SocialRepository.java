package demo.dublin.dashboard.repository;

import demo.dublin.dashboard.models.Social;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialRepository extends CrudRepository<Social, Integer> {
  @Query(value = "SELECT * from social where is_coffee = 1", nativeQuery = true)
  List<Social> getCoffeeShops();

  @Query(value = "SELECT * from social where is_brunch = 1", nativeQuery = true)
  List<Social> getBrunch();

  @Query(value = "SELECT * from social where is_drinks = 1", nativeQuery = true)
  List<Social> getBars();

  @Query(value = "SELECT * from social where is_restaurant = 1", nativeQuery = true)
  List<Social> getRestaurants();


}
