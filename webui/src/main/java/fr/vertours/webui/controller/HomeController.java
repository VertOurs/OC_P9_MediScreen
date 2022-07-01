package fr.vertours.webui.controller;

import fr.vertours.webui.bean.AssessBean;
import fr.vertours.webui.dto.HomeDTO;
import fr.vertours.webui.proxy.AssessProxy;
import fr.vertours.webui.proxy.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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
        System.out.println(dto);
        return "home/home";
    }

    @GetMapping("/result/{id}")
    public String result(Model model) {
        return "home/result";
    }

}
