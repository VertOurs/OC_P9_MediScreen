package fr.vertours.assessms.proxy;

import fr.vertours.assessms.Bean.PatientBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PatientMS", url="${proxy.patient}")
public interface PatientProxy {

    @GetMapping("/api/patient/{id}")
    PatientBean getPatientById(@PathVariable long id);
}
