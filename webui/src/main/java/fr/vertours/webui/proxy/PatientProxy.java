package fr.vertours.webui.proxy;

import fr.vertours.patientms.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( name="PatientMS", url="http://localhost:8081")
public interface PatientProxy {

    @GetMapping("/api/patient/{id}")
    public Patient getPatientById(@PathVariable long id);

    @GetMapping("/api/patient/all")
    public List<Patient> allPatients();
}
