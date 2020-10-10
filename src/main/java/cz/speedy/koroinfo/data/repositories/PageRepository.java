package cz.speedy.koroinfo.data.repositories;

import cz.speedy.koroinfo.data.entities.News;
import cz.speedy.koroinfo.data.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    Page findByEndpoint(String endpoint);
}
