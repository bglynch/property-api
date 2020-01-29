package demo.dublin.dashboard.repository;

import demo.dublin.dashboard.models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.reflect.Array;
import java.util.List;

// *********************************************************
// This controller is deprecated - please use HomeRepository
// *********************************************************

@CrossOrigin
@Repository
public interface HouseRepository extends JpaRepository<House, String> {

    @Query("SELECT h FROM House h WHERE h.price < :price")
    List<House> fetchHousesByMaxPrice(@Param("price") int price);

    @Query("SELECT h FROM House h WHERE h.price > :price")
    List<House> fetchHousesByMinPrice(@Param("price") String price);

    @Query("SELECT h FROM House h WHERE h.price > :minPrice AND h.price < :maxPrice")
    List<House> fetchHousesByPriceRange(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

    @Query("SELECT h.price, h.area FROM House h")
    List<Object> fetchArticles();

    @Query("SELECT h.adId, h.longitude, h.latitude, h.propertyType FROM House h")
    List<List> fetchLocations();

    @Query("SELECT DISTINCT h.postcode FROM House h")
    List<Object> fetchPostcodes();





}
