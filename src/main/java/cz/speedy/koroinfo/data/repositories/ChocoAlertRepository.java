package cz.speedy.koroinfo.data.repositories;

import cz.speedy.koroinfo.data.entities.ChocoAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChocoAlertRepository extends JpaRepository<ChocoAlert, Integer> {

    ChocoAlert findTopByOrderByIdDesc();
}
