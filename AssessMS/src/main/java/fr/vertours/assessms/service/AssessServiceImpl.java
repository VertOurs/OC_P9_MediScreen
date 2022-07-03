package fr.vertours.assessms.service;

import fr.vertours.assessms.Bean.NoteBean;
import fr.vertours.assessms.Bean.PatientBean;
import fr.vertours.assessms.constants.Triggers;
import fr.vertours.assessms.model.Assessment;
import fr.vertours.assessms.model.enums.RiskLevels;
import fr.vertours.assessms.proxy.NoteProxy;
import fr.vertours.assessms.proxy.PatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Slf4j
public class AssessServiceImpl implements AssessService {


    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;


    public AssessServiceImpl(PatientProxy patientProxy, NoteProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }

    @Override
    public Assessment calculateRiskAssessment(long id) {
        PatientBean patient = patientProxy.getPatientById(id);
        List<NoteBean> notes = noteProxy.allNotes(id);
        int age = calculateAge(patient.getDateOfBirth());
        int triggersNb = getTriggersOccurrences(notes);

        if (age >= 30) {
            thirtyPlusRisk(triggersNb);
        }
        if (age < 30) {
            return patient.getGender() == 'M'
                    ? maleThirtyMinusRisk(triggersNb)
                    : femaleThirtyMinusRisk(triggersNb);
        }
        return new Assessment(RiskLevels.NONE);
    }

    private int getTriggersOccurrences(List<NoteBean> notes) {
        int nb = 0;
        for(NoteBean note : notes) {
            for(String trigger : Triggers.TRIGGERS) {
                if (note.getRecommendation().contains(trigger)) {
                    nb++;
                }
            }
        }
        return nb;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private Assessment thirtyPlusRisk(int triggersNb) {
        if (triggersNb >= 2 && triggersNb < 6) {
            return new Assessment(RiskLevels.BORDERLINE);
        }
        if (triggersNb >= 6 && triggersNb < 8) {
            return new Assessment(RiskLevels.IN_DANGER);
        }
        if (triggersNb >= 8) {
            return new Assessment(RiskLevels.EARLY_ONSET);
        }
        return new Assessment(RiskLevels.NONE);
    }

    private Assessment maleThirtyMinusRisk(int triggersNb) {
        if (triggersNb >= 3 && triggersNb < 5 ) {
            return new Assessment(RiskLevels.IN_DANGER);
        } else if (triggersNb >= 5) {
            return new Assessment(RiskLevels.EARLY_ONSET);
        }
        return new Assessment(RiskLevels.NONE);
    }

    private Assessment femaleThirtyMinusRisk(int triggersNb) {
        if (triggersNb >= 4 && triggersNb < 7 ) {
            return new Assessment(RiskLevels.IN_DANGER);
        } else if (triggersNb >= 7) {
            return new Assessment(RiskLevels.EARLY_ONSET);
        }
        return new Assessment(RiskLevels.NONE);
    }
}



// 30+  + 2 déc             ***BorderLIne*
// 30+  + 6 dec             ***InDanger*
//30+  -> 8 dec             ***EarlyOnset*
//Male -> 30- + 3 déc       ***InDanger
// Male -> 30- + 5 dec       ***EarlyOnset

//Fem  -> 30- + 4 dec       ***InDanger
//Fem  -> 30- + 7 dec       ***EarlyOnSet
// pas de notes             ***NONE