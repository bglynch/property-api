package demo.dublin.dashboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class School {
    @Id @JsonIgnore
    int id;
    String name;
    @JsonIgnore
    String type;
    @JsonIgnore
    String address;
    @JsonIgnore
    String county;
    String denomination;
    String url;
    String gender;
    boolean feePaying;
    String longitude;
    String latitude;

}
