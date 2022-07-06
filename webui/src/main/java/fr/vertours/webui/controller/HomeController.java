package fr.vertours.webui.controller;

import fr.vertours.webui.bean.AssessBean;
import fr.vertours.webui.bean.PatientBean;
import fr.vertours.webui.dto.HomeDTO;
import fr.vertours.webui.dto.ResultDTO;
import fr.vertours.webui.proxy.AssessProxy;
import fr.vertours.webui.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HomeController {

    private final PatientProxy  patientProxy;
    private final AssessProxy assessProxy;

    public HomeController(PatientProxy patientProxy, AssessProxy assessProxy) {
        this.patientProxy = patientProxy;
        this.assessProxy = assessProxy;
    }

    @GetMapping("/home")
    public String home(Model model) {
        HomeDTO dto = new HomeDTO();
        dto.setPatients(patientProxy.allPatients());
        model.addAttribute("dto", dto);
        return "home/home";
    }

    @GetMapping("/result/{id}")
    public String result(@PathVariable long id, Model model) {
        PatientBean patient = patientProxy.getPatientById(id);
        AssessBean assess = assessProxy.getAssessById(id);
        ResultDTO dto = new ResultDTO(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                assess.getAssessment()
        );

        model.addAttribute("dto", dto);

        return "home/result";
    }

}
