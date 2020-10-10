package cz.speedy.koroinfo.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "koroinfo_subverify")
@Data
public class SubscriberVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true, length = 64)
    private String email;

    @Column(name = "verify_key", nullable = false, length = 64)
    private String verifyKey;

    @Column(name = "valid_to", nullable = false)
    private Timestamp validTo;
}
