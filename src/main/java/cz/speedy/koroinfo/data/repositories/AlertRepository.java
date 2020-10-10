package cz.speedy.koroinfo.data.repositories;

import cz.speedy.koroinfo.data.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

    List<Alert> findAllByEnabled(Boolean enabled);
}
