package cz.speedy.koroinfo.services;

import cz.speedy.koroinfo.data.entities.Subscriber;
import cz.speedy.koroinfo.data.entities.SubscriberVerification;
import cz.speedy.koroinfo.data.repositories.SubscriberRepository;
import cz.speedy.koroinfo.data.repositories.SubscriberVerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//import javax.mail.MessagingException;
//import javax.transaction.Transactional;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Collections;
//import java.util.UUID;

//@Service
//@Slf4j
//@Transactional
public class SubscriptionService {

    /*
    private final SubscriberRepository subscriberRepository;
    private final SubscriberVerificationRepository subscriberVerificationRepository;
    private final EmailService emailService;

    public SubscriptionService(SubscriberRepository subscriberRepository, SubscriberVerificationRepository subscriberVerificationRepository, EmailService emailService) {
        this.subscriberRepository = subscriberRepository;
        this.subscriberVerificationRepository = subscriberVerificationRepository;
        this.emailService = emailService;
    }

    public boolean isSubscribed(String email) {
        return subscriberRepository.existsByEmail(email);
    }

    public boolean verify(String verifyKey) {
        SubscriberVerification subscriberVerification = subscriberVerificationRepository.findByVerifyKey(verifyKey);
        if(subscriberVerification != null) {
            if(subscriberVerification.getValidTo().after(Timestamp.from(Instant.now()))) {
                Subscriber subscriber = new Subscriber();
                subscriber.setEmail(subscriberVerification.getEmail());
                subscriber.setValidFrom(Date.from(Instant.now()));
                subscriberRepository.save(subscriber);
                subscriberVerificationRepository.delete(subscriberVerification);
                return true;
            }
        }
        return false;
    }

    public void sendVerificationCode(String email) {
        UUID verifyKey = UUID.randomUUID();
        Timestamp validTo = Timestamp.from(Instant.now().plus(10, ChronoUnit.MINUTES));
        SubscriberVerification subscriberVerification = new SubscriberVerification();
        subscriberVerification.setEmail(email);
        subscriberVerification.setValidTo(validTo);
        subscriberVerification.setVerifyKey(verifyKey.toString());
        subscriberVerificationRepository.save(subscriberVerification);
        try {
            emailService.sendMessageUsingThymeleafTemplate(email, "Koro-Info - Verifikace o odběru novinek", "email/verification.html", Collections.singletonMap("verify", "https://koroinfo.nejrychlejsi.eu/sub/verification/" + verifyKey.toString()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 600000L)
    public void removeInvalidVerifications() {
        log.info("Removing invalid verification tokens");
        subscriberVerificationRepository.deleteByValidToBefore(Timestamp.from(Instant.now()));
    }


     */
}
