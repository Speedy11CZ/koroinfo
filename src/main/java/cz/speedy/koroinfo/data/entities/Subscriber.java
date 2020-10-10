package cz.speedy.koroinfo.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "koroinfo_subscribers")
@Data
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true, length = 64)
    private String email;

    @Column(name = "valid_from", nullable = false)
    private Date validFrom;
}
