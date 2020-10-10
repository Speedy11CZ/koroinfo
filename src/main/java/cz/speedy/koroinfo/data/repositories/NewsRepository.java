package cz.speedy.koroinfo.data.repositories;

import cz.speedy.koroinfo.data.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
