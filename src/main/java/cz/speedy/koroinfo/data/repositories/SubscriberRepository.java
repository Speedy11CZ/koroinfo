package cz.speedy.koroinfo.data.repositories;

import cz.speedy.koroinfo.data.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

    boolean existsByEmail(String email);
}
