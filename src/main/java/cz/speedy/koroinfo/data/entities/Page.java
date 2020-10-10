package cz.speedy.koroinfo.data.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "koroinfo_pages")
@Data
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Column(name = "endpoint", nullable = false, unique = true, length = 32)
    private String endpoint;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;
}
