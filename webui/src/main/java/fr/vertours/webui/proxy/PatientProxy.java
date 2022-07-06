package fr.vertours.webui.proxy;

import fr.vertours.webui.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient( name="PatientMS", url="${proxy.patient}")
public interface PatientProxy {

    @GetMapping("/api/patient/{id}")
    PatientBean getPatientById(@PathVariable long id);

    @GetMapping("/api/patient/all")
    List<PatientBean> allPatients();
    @PostMapping("/api/patient/create")
    PatientBean savePatient(@RequestBody @Valid PatientBean patient);

    @PutMapping("api/patient/update/{id}")
    PatientBean updatePatient(@PathVariable long id, @RequestBody @Valid PatientBean patient);
}
