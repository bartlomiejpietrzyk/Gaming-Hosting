package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private Integer building;

    private Integer homeNumber;

    private String city;

    private String postCode;
}
