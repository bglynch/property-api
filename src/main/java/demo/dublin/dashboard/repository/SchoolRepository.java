package demo.dublin.dashboard.repository;

import demo.dublin.dashboard.models.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer> {
}
