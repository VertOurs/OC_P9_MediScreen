package fr.vertours.patientms.service;

import fr.vertours.patientms.model.Patient;
import fr.vertours.patientms.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PatientServiceImplTest {


    PatientRepository repository = mock(PatientRepository.class);

    PatientServiceImpl classUnderTest = new PatientServiceImpl(repository);

    @Test
    void getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        when(repository.findAll()).thenReturn(patients);

        classUnderTest.getAllPatients();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getPatientById() {
        Patient expectedPatient = new Patient();
        expectedPatient.setId(5);
        when(repository.findById(5l)).thenReturn(java.util.Optional.of(expectedPatient));

        Patient actualPatient = classUnderTest.getPatientById(5l);

        assertEquals(expectedPatient.getId(), actualPatient.getId());


    }

    @Test
    void savePatient() {
        Patient expectedPatient = new Patient();
        expectedPatient.setId(6l);
        expectedPatient.setFirstName("Jean");
        expectedPatient.setLastName("Moulin");
        when(repository.findOneByFirstNameAndLastName("Jean", "Moulin"))
                .thenReturn(Optional.empty());
        when(repository.save(expectedPatient)).thenReturn(expectedPatient);

        Patient actualPatient = classUnderTest.savePatient(expectedPatient);
        System.out.println(actualPatient.toString());
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    void updatePatient() {
        Patient existingPatient = new Patient();
        existingPatient.setId(61l);
        existingPatient.setFirstName("yo");
        when(repository.findById(61l)).thenReturn(java.util.Optional.of(existingPatient));
        Patient expectedPatient = new Patient();
        expectedPatient.setId(61l);
        expectedPatient.setFirstName("léa");
        when(repository.save(expectedPatient)).thenReturn(expectedPatient);

        Patient actualPatient = classUnderTest.updatePatient(61l, expectedPatient);

        assertEquals(actualPatient.getFirstName(),"léa");

    }

    @Test
    void deletePatient() {

    }
}