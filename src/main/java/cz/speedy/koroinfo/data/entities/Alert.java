package cz.speedy.koroinfo.data.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "koroinfo_alerts")
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 32)
    private String title;

    @Column(name = "description", nullable = false, length = 128)
    private String description;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
}
