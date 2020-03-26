package demo.dublin.dashboard.repository;

import demo.dublin.dashboard.models.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer> {
  @Query(value = "SELECT * from school WHERE type=\"post primary\" AND county = \"Dublin\"", nativeQuery = true)
  List<School> getDublinSecondarySchools();

  @Query(value = "SELECT * from school WHERE type=\"primary\" AND county = \"Dublin\"", nativeQuery = true)
  List<School> getDublinPrimarySchools();
}
