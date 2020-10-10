package cz.speedy.koroinfo.data.repositories;

import cz.speedy.koroinfo.data.entities.SubscriberVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface SubscriberVerificationRepository extends JpaRepository<SubscriberVerification, Integer> {

    SubscriberVerification findByVerifyKey(String verifyKey);

    void deleteByValidToBefore(Timestamp timestamp);
}
