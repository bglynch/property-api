package demo.dublin.dashboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Social {
  @Id @JsonIgnore
  int id;
  String name;
  String longitude;
  String latitude;
  String link;
  @JsonIgnore
  float rating;
  @JsonIgnore
  boolean isBrunch;
  @JsonIgnore
  boolean isRestaurant;
  @JsonIgnore
  boolean isCoffee;
  @JsonIgnore
  boolean isDrinks;
  @JsonIgnore
  String place_id;

}
