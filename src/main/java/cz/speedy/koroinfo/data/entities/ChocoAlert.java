package cz.speedy.koroinfo.data.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "koroinfo_chocoalert")
@Data
public class ChocoAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "image_id", nullable = false, unique = true, length = 16)
    private String imageId;
}
