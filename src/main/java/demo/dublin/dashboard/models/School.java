package demo.dublin.dashboard.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class School {
    @Id
    int id;
    String name;
    String type;
    String address;
    String county;
    String icon;
    String denomination;
    String url;
    String longitude;
    String latitude;

}
