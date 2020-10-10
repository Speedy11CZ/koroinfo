package cz.speedy.koroinfo.controllers;

import cz.speedy.koroinfo.data.entities.Alert;
import cz.speedy.koroinfo.data.repositories.AlertRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class MainAdvice {

    private final AlertRepository alertRepository;

    public MainAdvice(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @ModelAttribute("enabledAlerts")
    public List<Alert> getEnabledAlerts() {
        return alertRepository.findAllByEnabled(true);
    }
}
