package demo.dublin.dashboard.repository;

import demo.dublin.dashboard.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@Repository
public interface HomeRepository extends JpaRepository<Home, String> {

    @Query("select DISTINCT h.locality from Home h")
    List<String> getAllLocalitys();

    @Query("SELECT h.adId, h.longitude, h.latitude, h.propertyType FROM Home h")
    List<List> fetchLocations();
}
