package cz.speedy.koroinfo.data.entities;

import cz.speedy.koroinfo.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "koroinfo_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", unique = true, length = 32)
    private String username;

    @Column(name = "email", unique = true, length = 64)
    private String email;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "surname", nullable = false, length = 32)
    private String surname;

    @Column(name = "password", length = 256)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 32)
    private Role role;

    @Column(name = "shadow", nullable = false)
    private boolean shadow;

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }
}
