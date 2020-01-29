package demo.dublin.dashboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Amenity {
    @Id @JsonIgnore
    int id;
    @JsonIgnore
    String type;
    String name;
    @JsonIgnore
    String description;
    String longitude;
    String latitude;
}
