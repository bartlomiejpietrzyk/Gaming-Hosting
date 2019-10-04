package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends UUIDgenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 4)
    private String username;

    @Length(min = 4)
    private String firstName;

    @Length(min = 1)
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private Boolean enabled;

    private Boolean locked;

    private String created;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Address addresses;
}
