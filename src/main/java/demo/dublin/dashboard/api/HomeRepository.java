package demo.dublin.dashboard.api;

import demo.dublin.dashboard.models.Home;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@CrossOrigin
@Repository
public interface HomeRepository extends CrudRepository<Home, String> {

    @Query("select h.locality from Home h")
    Collection<Home> getAllLocalitys();
}
