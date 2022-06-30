package fr.vertours.assessms.proxy;

import fr.vertours.assessms.Bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PatientMS", url="http://localhost:8081")
public interface PatientProxy {

    @GetMapping("/api/patient/{id}")
    PatientBean getPatientById(@PathVariable long id);
}
