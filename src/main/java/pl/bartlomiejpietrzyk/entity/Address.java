package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String street;

    @NotEmpty
    private Integer building;

    @NotEmpty
    private Integer homeNumber;

    @NotEmpty
    private String city;

    @NotEmpty
    private String postCode;
}
