package demo.dublin.dashboard.repository;

import demo.dublin.dashboard.models.Amenity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AmenityRepository extends CrudRepository<Amenity, Integer> {

    List<Amenity> findByType(String type);


}
