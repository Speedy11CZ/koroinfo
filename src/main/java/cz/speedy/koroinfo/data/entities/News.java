package cz.speedy.koroinfo.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "koroinfo_news")
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "text", nullable = false, columnDefinition = "text")
    private String text;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    public String getFormattedTime() {
        String prefix;
        if(time.toLocalDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
            prefix = "Dnes";
        } else if(time.toLocalDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() - 1) {
            prefix = "Včera";
        } else if(time.toLocalDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() - 2) {
            prefix = "Předevčírem";
        } else {
            prefix = DateTimeFormatter.ofPattern("dd. MM. yyyy").format(time.toLocalDateTime());
        }
        return String.format("%s %s", prefix, DateTimeFormatter.ofPattern("HH:mm").format(time.toLocalDateTime()));
    }
}
