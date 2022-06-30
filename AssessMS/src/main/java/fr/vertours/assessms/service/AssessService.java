package fr.vertours.assessms.service;

import fr.vertours.assessms.model.Assessment;

public interface AssessService {
    Assessment calculateRiskAssessment(long id);
}
