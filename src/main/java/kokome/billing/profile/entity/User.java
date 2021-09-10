package kokome.billing.profile.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import kokome.billing.role.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "user_username_bky", columnNames = "username")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String emailAddress;

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne
    private Role role;

    private LocalDate joinDate;

    public User(String firstName, String lastName, String username, LocalDate joinDate, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.joinDate = joinDate;
        this.role = role;
    }
}
