package fr.vertours.assessms.service;

import fr.vertours.assessms.Bean.NoteBean;
import fr.vertours.assessms.Bean.PatientBean;
import fr.vertours.assessms.constants.Triggers;
import fr.vertours.assessms.model.Assessment;
import fr.vertours.assessms.proxy.NoteProxy;
import fr.vertours.assessms.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssessServiceImplTest {

    PatientProxy patientProxy = mock(PatientProxy.class);
    NoteProxy noteProxy = mock(NoteProxy.class);

    AssessServiceImpl classUnderTest =
            new AssessServiceImpl(patientProxy,noteProxy);

    static List<NoteBean> getTriggers(int nb) {
        List<NoteBean> notes = new ArrayList<>();

        for (int i = 0; i < nb ; i++) {
            NoteBean bean = new NoteBean();
            bean.setRecommendation(Triggers.TRIGGERS.get(0));
            notes.add(bean);
        }
        System.out.println(notes);
        return notes;
    }
    @Test
    void thirtyPlusRisk() {
        PatientBean patient = new PatientBean();
        patient.setId(500);
        patient.setDateOfBirth(LocalDate.of(1960, 01, 01));
        when(patientProxy.getPatientById(patient.getId())).thenReturn(patient);

        //BorderLine
        List<NoteBean> notes = getTriggers(4);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        Assessment assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "BORDERLINE");

        //In Danger

        notes = getTriggers(7);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
         assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "IN_DANGER");

        //Early Onset
        notes = getTriggers(10);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "EARLY_ONSET");

        //None
        notes = getTriggers(0);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "NONE");


    }

    @Test
    void maleMinus30Risk() {
        PatientBean patient = new PatientBean();
        patient.setId(500);
        patient.setGender('M');
        patient.setDateOfBirth(LocalDate.of(2010, 01, 01));
        when(patientProxy.getPatientById(patient.getId())).thenReturn(patient);

        //In Danger
        List<NoteBean> notes = getTriggers(4);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        Assessment assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "IN_DANGER");

        //Early Onset
        notes = getTriggers(6);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "EARLY_ONSET");

        //None
        notes = getTriggers(0);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "NONE");
    }

    @Test
    void femaleMinus30Risk() {
        PatientBean patient = new PatientBean();
        patient.setId(500);
        patient.setGender('F');
        patient.setDateOfBirth(LocalDate.of(2010, 01, 01));
        when(patientProxy.getPatientById(patient.getId())).thenReturn(patient);

        //In Danger
        List<NoteBean> notes = getTriggers(5);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        Assessment assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "IN_DANGER");

        //Early Onset
        notes = getTriggers(8);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "EARLY_ONSET");

        //None
        notes = getTriggers(0);
        when(noteProxy.allNotes(patient.getId())).thenReturn(notes);
        assessment = classUnderTest.calculateRiskAssessment(500);
        assertEquals(assessment.getAssessment().toString(), "NONE");
    }
}